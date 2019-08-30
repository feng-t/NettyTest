package com.httpserver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;

public class test {
    public static void main(String[] args) {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = new FileInputStream("E:\\work\\text.txt");
            outputStream = new FileOutputStream("E:\\work\\text1.txt");
            ByteBuffer buffer = ByteBuffer.allocate(1024 * 16);
            inputStream.getChannel().read(buffer);
            buffer.put("asdfakkkkkkkkkkkkkkkkkkkkkkkkkkkk".getBytes());
            buffer.flip();
            while (buffer.hasRemaining()) {
                outputStream.write(buffer.get());
            }

            buffer.compact();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception ignored) {
            }
        }
    }
}
