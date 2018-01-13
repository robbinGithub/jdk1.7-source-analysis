/*
 * Copyright (c) 1997, 2002, Oracle and/or its affiliates. All rights reserved.
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

package java.text;

/**
* An Annotation object is used as a wrapper for a text attribute value if
* the attribute has annotation characteristics. These characteristics are:
* <ul>
* <li>The text range that the attribute is applied to is critical to the
* semantics of the range. That means, the attribute cannot be applied to subranges
* of the text range that it applies to, and, if two adjacent text ranges have
* the same value for this attribute, the attribute still cannot be applied to
* the combined range as a whole with this value.
* <li>The attribute or its value usually do no longer apply if the underlying text is
* changed.
* </ul>
*
* An example is grammatical information attached to a sentence:
* For the previous sentence, you can say that "an example"
* is the subject, but you cannot say the same about "an", "example", or "exam".
* When the text is changed, the grammatical information typically becomes invalid.
* Another example is Japanese reading information (yomi).
*
* <p>
* Wrapping the attribute value into an Annotation object guarantees that
* adjacent text runs don't get merged even if the attribute values are equal,
* and indicates to text containers that the attribute should be discarded if
* the underlying text is modified.
* 如果文本属性具有 annotation 特征，则 Annotation 对象用作文本属性值的包装器。这些特征是： 

属性所应用的文本范围对范围语义至关重要。这意味着，属性不能应用于其所应用的文本范围的子范围，并且，如果两个相邻的文本范围针对此属性具有相同的值，此属性也不能将此值应用于整体的复合范围。 
当底层文本改变时，属性或其值通常不再适用。 
一个例子是与句子相关联的语法信息：在上面的句子中，您可以说“一个例子”是主语，但您不能说“一个”、“例子”或者“一个例”也是。当文本改变时，语法信息通常变成无效的了。另一个例子是日语阅读信息 (yomi)。 
将属性值包装在一个 Annotation 对象中保证了既使属性值相等，相邻的文本不会合并，并且指示文本容器如果属性值下面的文本修改了，则应该丢弃属性。 


*
* @see AttributedCharacterIterator
* @since 1.2
*/

public class Annotation {

    /**
     * Constructs an annotation record with the given value, which
     * may be null.
     * @param value The value of the attribute
     */
    public Annotation(Object value) {
        this.value = value;
    }

    /**
     * Returns the value of the attribute, which may be null.
     */
    public Object getValue() {
        return value;
    }

    /**
     * Returns the String representation of this Annotation.
     */
    public String toString() {
        return getClass().getName() + "[value=" + value + "]";
    }

    private Object value;

};
