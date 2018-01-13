/*
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *

 */

/*

 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
 * http://creativecommons.org/publicdomain/zero/1.0/
 */

/**
 * 
 *  * 为锁和等待条件提供一个框架的接口和类，它不同于内置同步和监视器
 * 
 *  为锁和等待条件提供一个框架的接口和类，它不同于内置同步和监视器。该框架允许更灵活地使用锁和条件，但以更难用的语法为代价。 

	Lock 接口支持那些语义不同（重入、公平等）的锁规则，可以在非阻塞式结构的上下文（包括 hand-over-hand 和锁重排算法）中使用这些规则。主要的实现是 ReentrantLock。 
	
	ReadWriteLock 接口以类似方式定义了一些读取者可以共享而写入者独占的锁。此包只提供了一个实现，即 ReentrantReadWriteLock，因为它适用于大部分的标准用法上下文。但程序员可以创建自己的、适用于非标准要求的实现。 
	
	Condition 接口描述了可能会与锁有关联的条件变量。这些变量在用法上与使用 Object.wait 访问的隐式监视器类似，但提供了更强大的功能。需要特别指出的是，单个 Lock 可能与多个 Condition 对象关联。为了避免兼容性问题，Condition 方法的名称与对应的 Object 版本中的不同。 
	
	AbstractQueuedSynchronizer 类是一个非常有用的超类，可用来定义锁以及依赖于排队阻塞线程的其他同步器。 AbstractQueuedLongSynchronizer 类提供相同的功能但扩展了对同步状态的 64 位的支持。两者都扩展了类 AbstractOwnableSynchronizer（一个帮助记录当前保持独占同步的线程的简单类）。LockSupport 类提供了更低级别的阻塞和解除阻塞支持，这对那些实现自己的定制锁类的开发人员很有用。 
	
	
	
	从以下版本开始： 
 
 * Interfaces and classes providing a framework for locking and waiting
 * for conditions that is distinct from built-in synchronization and
 * monitors.  The framework permits much greater flexibility in the use of
 * locks and conditions, at the expense of more awkward syntax.
 *
 * <p>The {@link java.util.concurrent.locks.Lock} interface supports
 * locking disciplines that differ in semantics (reentrant, fair, etc),
 * and that can be used in non-block-structured contexts including
 * hand-over-hand and lock reordering algorithms.  The main implementation
 * is {@link java.util.concurrent.locks.ReentrantLock}.
 *
 * <p>The {@link java.util.concurrent.locks.ReadWriteLock} interface
 * similarly defines locks that may be shared among readers but are
 * exclusive to writers.  Only a single implementation, {@link
 * java.util.concurrent.locks.ReentrantReadWriteLock}, is provided, since
 * it covers most standard usage contexts.  But programmers may create
 * their own implementations to cover nonstandard requirements.
 *
 * <p>The {@link java.util.concurrent.locks.Condition} interface
 * describes condition variables that may be associated with Locks.
 * These are similar in usage to the implicit monitors accessed using
 * {@code Object.wait}, but offer extended capabilities.
 * In particular, multiple {@code Condition} objects may be associated
 * with a single {@code Lock}.  To avoid compatibility issues, the
 * names of {@code Condition} methods are different from the
 * corresponding {@code Object} versions.
 *
 * <p>The {@link java.util.concurrent.locks.AbstractQueuedSynchronizer}
 * class serves as a useful superclass for defining locks and other
 * synchronizers that rely on queuing blocked threads.  The {@link
 * java.util.concurrent.locks.AbstractQueuedLongSynchronizer} class
 * provides the same functionality but extends support to 64 bits of
 * synchronization state.  Both extend class {@link
 * java.util.concurrent.locks.AbstractOwnableSynchronizer}, a simple
 * class that helps record the thread currently holding exclusive
 * synchronization.  The {@link java.util.concurrent.locks.LockSupport}
 * class provides lower-level blocking and unblocking support that is
 * useful for those developers implementing their own customized lock
 * classes.
 * 
 * 
 *
 * @since 1.5
 */
package java.util.concurrent.locks;
