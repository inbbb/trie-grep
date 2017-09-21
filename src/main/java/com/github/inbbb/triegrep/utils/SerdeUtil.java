package com.github.inbbb.triegrep.utils;

import java.io.*;

public class SerdeUtil {
    public static void serialize(Object obj, String fileName)
        throws FileNotFoundException, IOException {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            try (BufferedOutputStream bos = new BufferedOutputStream(fos)) {
                try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
                    oos.writeObject(obj);
                }
            }
        }
    }

    public static Object deserialize(String fileName)
        throws FileNotFoundException, IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(fileName)) {
            try (BufferedInputStream bis = new BufferedInputStream(fis)) {
                try (ObjectInputStream ois = new ObjectInputStream(bis)) {
                    return ois.readObject();
                }
            }
        }
    }
}
