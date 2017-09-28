package gui;

import java.util.StringTokenizer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.rtf.RTFEditorKit;

/**
 * 在分析字符串的同时，记录每个token所在的位置
 *
 */
class MyStringTokenizer extends StringTokenizer {
    String sval = " ";
    String oldStr, str;
    int m_currPosition = 0, m_beginPosition = 0;
    MyStringTokenizer(String str) {
        super(str, " ");
        this.oldStr = str;
        this.str = str;
    }
    public String nextToken() {
        try {
            String s = super.nextToken();
            int pos = -1;
            if (oldStr.equals(s)) {
                return s;
            }
            pos = str.indexOf(s + sval);
            if (pos == -1) {
                pos = str.indexOf(sval + s);
                if (pos == -1)
                    return null;
                else
                    pos += 1;
            }
            int xBegin = pos + s.length();
            str = str.substring(xBegin);
            m_currPosition = m_beginPosition + pos;
            m_beginPosition = m_beginPosition + xBegin;
            return s;
        } catch (java.util.NoSuchElementException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    // 返回token在字符串中的位置
    public int getCurrPosition() {
        return m_currPosition;
    }
}