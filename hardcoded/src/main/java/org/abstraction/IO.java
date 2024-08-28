package org.abstraction;

public interface IO {
    Object read(String filepath);
    void write(Object object);
}
