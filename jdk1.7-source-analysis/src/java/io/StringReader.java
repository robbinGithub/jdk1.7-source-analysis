/*
 * Copyright (c) 1996, 2005, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package java.io;


/**
 * A character stream whose source is a string.
   *其源为一个字符串的字符流。 


 * @author      Mark Reinhold
 * @since       JDK1.1
 */

public class StringReader extends Reader {

    private String str;
    private int length;
    private int next = 0;
    private int mark = 0;

    /**
     * Creates a new string reader.
     *
     * @param s  String providing the character stream.
     */
    public StringReader(String s) {
        this.str = s;
        this.length = s.length();
    }

    /** Check to make sure that the stream has not been closed */
    private void ensureOpen() throws IOException {
        if (str == null)
            throw new IOException("Stream closed");
    }

    /**
     * Reads a single character.读取单个字符
     *
     * @return     The character read, or -1 if the end of the stream has been
     *             reached
     *
     * @exception  IOException  If an I/O error occurs
     */
    public int read() throws IOException {
        synchronized (lock) {
            ensureOpen();
            if (next >= length)
                return -1;
            return str.charAt(next++);
        }
    }

    /**
     * Reads characters into a portion of an array.读取多个字符
     *
     * @param      cbuf  Destination buffer
     * @param      off   Offset at which to start writing characters
     * @param      len   Maximum number of characters to read
     *
     * @return     The number of characters read, or -1 if the end of the
     *             stream has been reached
     *
     * @exception  IOException  If an I/O error occurs
     */
    public int read(char cbuf[], int off, int len) throws IOException {
        synchronized (lock) {
            ensureOpen();
            // 参数检查
            if ((off < 0) || (off > cbuf.length) || (len < 0) ||
                ((off + len) > cbuf.length) || ((off + len) < 0)) {
                throw new IndexOutOfBoundsException();
            } else if (len == 0) {
                return 0;
            }
            // 读取结束了
            if (next >= length)
                return -1;
            // 剩下的字符和len取最小
            int n = Math.min(length - next, len);
            // 调用String getChars方法,从next至next+n位置，放入缓冲区cbuf中(从off偏移处放入)
            str.getChars(next, next + n, cbuf, off);
            // 记录下一个将要获取的字符
            next += n;
            return n;
        }
    }

    /**
     * Skips the specified number of characters in the stream. Returns
     * the number of characters that were skipped.
     *
     * <p>The <code>ns</code> parameter may be negative, even though the
     * <code>skip</code> method of the {@link Reader} superclass throws
     * an exception in this case. Negative values of <code>ns</code> cause the
     * stream to skip backwards. Negative return values indicate a skip
     * backwards. It is not possible to skip backwards past the beginning of
     * the string.
     *
     * <p>If the entire string has been read or skipped, then this method has
     * no effect and always returns 0.
     *
     * @exception  IOException  If an I/O error occurs
     */
    public long skip(long ns) throws IOException {
        synchronized (lock) {
            ensureOpen();
            // 字符串结束位置
            if (next >= length)
                return 0;
            // Bound skip by beginning and end of the source
            // 比较剩余字符数和skip字符数，取最小值
            long n = Math.min(length - next, ns);
            n = Math.max(-next, n);
            next += n;
            return n;
        }
    }

    /**
     * Tells whether this stream is ready to be read.
     *
     * @return True if the next read() is guaranteed not to block for input
     *
     * @exception  IOException  If the stream is closed
     */
    public boolean ready() throws IOException {
        synchronized (lock) {
        ensureOpen();
        return true;
        }
    }

    /**
     * Tells whether this stream supports the mark() operation, which it does.
     */
    public boolean markSupported() {
        return true;
    }

    /**
     * Marks the present position in the stream.  Subsequent calls to reset()
     * will reposition the stream to this point.
     *
     * @param  readAheadLimit  Limit on the number of characters that may be
     *                         read while still preserving the mark.  Because
     *                         the stream's input comes from a string, there
     *                         is no actual limit, so this argument must not
     *                         be negative, but is otherwise ignored.
     *
     * @exception  IllegalArgumentException  If readAheadLimit is < 0
     * @exception  IOException  If an I/O error occurs
     */
    public void mark(int readAheadLimit) throws IOException {
        if (readAheadLimit < 0){
            throw new IllegalArgumentException("Read-ahead limit < 0");
        }
        synchronized (lock) {
            ensureOpen();
            mark = next;
        }
    }

    /**
     * Resets the stream to the most recent mark, or to the beginning of the
     * string if it has never been marked.
     *
     * @exception  IOException  If an I/O error occurs
     */
    public void reset() throws IOException {
        synchronized (lock) {
            ensureOpen();
            next = mark;
        }
    }

    /**
     * Closes the stream and releases any system resources associated with
     * it. Once the stream has been closed, further read(),
     * ready(), mark(), or reset() invocations will throw an IOException.
     * Closing a previously closed stream has no effect.
     */
    public void close() {
        str = null;
    }
}
