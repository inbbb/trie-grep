package com.github.inbbb.triegrep.utils;

import java.io.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringUtilTest {
    @Test
    public void testHeadPos0() {
        assertEquals("a", StringUtil.head("a"));
        assertEquals("a", StringUtil.head("ab"));
        assertEquals("あ", StringUtil.head("あ"));
        assertEquals("あ", StringUtil.head("あい"));
    }

    @Test
    public void testHeadNeg0() {
        try {
            StringUtil.head("");
            assertTrue(false);
        } catch(IndexOutOfBoundsException e) {
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }

    @Test
    public void testTailPos0() {
        assertEquals("", StringUtil.tail("a"));
        assertEquals("b", StringUtil.tail("ab"));
        assertEquals("bc", StringUtil.tail("abc"));
        assertEquals("", StringUtil.tail("あ"));
        assertEquals("い", StringUtil.tail("あい"));
        assertEquals("いう", StringUtil.tail("あいう"));
    }

    @Test
    public void testTailNeg0() {
        try {
            StringUtil.tail("");
            assertTrue(false);
        } catch(IndexOutOfBoundsException e) {
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }

    @Test
    public void testGetPos0() {
        assertEquals("a", StringUtil.get("ab", 0));
        assertEquals("b", StringUtil.get("ab", 1));
        assertEquals("あ", StringUtil.get("あい", 0));
        assertEquals("い", StringUtil.get("あい", 1));
    }

    @Test
    public void testGetNeg0() {
        try {
            StringUtil.get("ab", 3);
            assertTrue(false);
        } catch(IndexOutOfBoundsException e) {
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }

    @Test
    public void testGetCommonPrefixPos0() {
        assertEquals("", StringUtil.getCommonPrefix("a", "b"));
        assertEquals("a", StringUtil.getCommonPrefix("aa", "ab"));
        assertEquals("aa", StringUtil.getCommonPrefix("aaa", "aab"));
        assertEquals("", StringUtil.getCommonPrefix("", "aaa"));
        assertEquals("", StringUtil.getCommonPrefix("bbb", "aaa"));
        assertEquals("aaa", StringUtil.getCommonPrefix("aaa", "aaaaaa"));
        assertEquals("aa", StringUtil.getCommonPrefix("aab", "aaaaaa"));
        assertEquals("aaa", StringUtil.getCommonPrefix("aaab", "aaaaaa"));
        assertEquals("あ", StringUtil.getCommonPrefix("あい", "あう"));
    }
}
