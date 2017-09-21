package com.github.inbbb.triegrep.utils;

import java.io.*;
import java.util.Random;
import java.util.stream.Stream;

import org.junit.Test;
import static org.junit.Assert.*;

class Foo implements Serializable {
    private static final long serialVersionUID = 1L;
    public int x;
    Foo(int x) {
        this.x = x;
    }
}

public class SerdeUtilTest {
    @Test
    public void testSerdePos0() throws Exception {
        Random rand = new Random();
        Foo foo0 = new Foo(rand.nextInt(1000));
        SerdeUtil.serialize(foo0, "/tmp/foo04283402934");
        Foo foo1 = (Foo)SerdeUtil.deserialize("/tmp/foo04283402934");
        assertEquals(foo0.x, foo1.x);
    }
}
