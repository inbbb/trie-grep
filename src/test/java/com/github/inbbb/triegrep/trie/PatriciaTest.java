package com.github.inbbb.triegrep.trie;

import java.io.*;
import java.util.stream.Stream;

import org.junit.Test;
import static org.junit.Assert.*;

public class PatriciaTest {
    @Test
    public void testContainsPos0() {
        Patricia trie = new Patricia();
        Stream<String> keywords = Stream.of("aaaa", "aabb", "bbcc", "bbdd", "ffffffffffff");
        trie.build(keywords);
        assertTrue(trie.contains("aaaa"));
        assertTrue(trie.contains("aabb"));
        assertTrue(trie.contains("bbcc"));
        assertTrue(trie.contains("bbdd"));
        assertTrue(!trie.contains("a"));
        assertTrue(!trie.contains("bb"));
        assertTrue(!trie.contains("dd"));
        assertTrue(!trie.contains("e"));
        trie.dump(0);
    }

    @Test
    public void testInPos0() {
        Patricia trie = new Patricia();
        Stream<String> keywords = Stream.of("aaaa", "aabb", "bbcc", "bbdd", "ffffffffffff");
        trie.build(keywords);
        assertTrue(trie.in("aaaa"));
        assertTrue(trie.in("aaaaa"));
        assertTrue(trie.in("bbaabb"));
        assertTrue(trie.in("      bbcc"));
        assertTrue(!trie.in(""));
        assertTrue(!trie.in("a"));
        assertTrue(!trie.in("bd"));
        assertTrue(!trie.in("      "));
        trie.dump(0);
    }
}
