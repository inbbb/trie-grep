package com.github.inbbb.triegrep.trie;

import java.io.*;
import java.util.stream.Stream;
import java.util.concurrent.atomic.AtomicInteger;

import com.github.inbbb.triegrep.utils.SerdeUtil;

public class Patricia implements Serializable {
    private static final long serialVersionUID = 1L;

    private Node root;

    public Patricia() {
        this.root = new Node(-1);
    }

    public void build(Stream<String> keywords) {
        AtomicInteger id = new AtomicInteger(0); // hmmm...
        keywords.forEach(keyword -> {
            this.root.insert(keyword, id.getAndIncrement());
        });
    }

    public boolean contains(String keyword) {
        return this.root.exactMatch(keyword);
    }

    public boolean in(String text) {
        for (int i = 0; i < text.length() - 1; i++) {
            if (this.root.prefixMatch(text.substring(i, text.length()))) {
                return true;
            }
        }
        return false;
    }

    public void serialize(String fileName)
        throws FileNotFoundException, IOException {
        SerdeUtil.serialize(this, fileName);
    }

    public static Patricia deserialize(String fileName)
        throws FileNotFoundException, IOException, ClassNotFoundException {
        return (Patricia)SerdeUtil.deserialize(fileName);
    }

    public void dump(int a) {
        this.root.dump(a);
    }
}
