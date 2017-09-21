package com.github.inbbb.triegrep.trie;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.github.inbbb.triegrep.utils.StringUtil;

class Node implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<String, Node> children;
    private int id;

    Node(int id) {
        this.children = new HashMap<>();
        this.id = id;
    }

    void insert(String s, int id) {
        boolean done = false;
        Map<String, Node> newChildren = new HashMap<>(this.children.size());

        for (Map.Entry<String, Node> n : this.children.entrySet()) {
            String k = n.getKey();
            Node v = n.getValue();
            String commonPrefix = StringUtil.getCommonPrefix(s, n.getKey());

            if (commonPrefix.length() == 0) {
                newChildren.put(k, v);
                continue;
            } else if (commonPrefix.length() < k.length()) {
                Node new_ = new Node(-1);
                new_.insert(k.substring(commonPrefix.length(), k.length()), v.id());
                new_.insert(s.substring(commonPrefix.length(), s.length()), id);
                newChildren.put(commonPrefix, new_);
                done = true;
            } else if (commonPrefix.length() == k.length()) {
                newChildren.put(k, v);
                v.insert(s.substring(commonPrefix.length(), s.length()), id);
                done = true;
            } else {
                throw new RuntimeException("logic bug");
            }
        }

        if (!done) {
            newChildren.put(s, new Node(id));
        }
        this.children = newChildren;
    }

    boolean exactMatch(String s) {
        if (s.length() == 0) {
            return this.id >= 0;
        }
        for (Map.Entry<String, Node> n : this.children.entrySet()) {
            String k = n.getKey();
            Node v = n.getValue();
            if (k.length() > s.length()) {
                continue;
            }
            if (s.substring(0, k.length()).equals(k)) {
                String rest = s.substring(k.length(), s.length());
                return v.exactMatch(rest);
            }
        }
        return false;
    }

    boolean prefixMatch(String s) {
        if (this.children.size() == 0) {
            return true;
        }
        for (Map.Entry<String, Node> n : this.children.entrySet()) {
            String k = n.getKey();
            Node v = n.getValue();
            if (k.length() > s.length()) {
                continue;
            }
            if (s.substring(0, k.length()).equals(k)) {
                String rest = s.substring(k.length(), s.length());
                return v.prefixMatch(rest);
            }
        }
        return false;
    }

    Map<String, Node> children() {
        return this.children;
    }

    int id() {
        return this.id;
    }

    void dump(int indentLevel) {
        String indent = "";
        for (int i = 0; i < indentLevel; i++) {
            indent += "    ";
        }
        System.out.println(indent + this.id);
        for (Map.Entry<String, Node> n : this.children.entrySet()) {
            System.out.println(indent + n.getKey());
            n.getValue().dump(indentLevel + 1);
        }
    }
}
