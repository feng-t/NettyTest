package com.nettytomcat;

import java.nio.CharBuffer;

public class TestBuffer {
    private static int index=0;
    private static String[] strings={
            "ambassador",
            "knew决定是否把受到广泛还是快点发给",
            "都发上来大家发挥零七二九吉林省地方和收到了"
    };
    public static void main(String[] args) throws Exception{
        CharBuffer buffer = CharBuffer.allocate(21);
        while (fileBuffer(buffer)){
            buffer.flip();
            drainBuffer(buffer);
            buffer.clear();
        }
    }
    private static void drainBuffer(CharBuffer buffer){
        while (buffer.hasRemaining()){
            System.out.print(buffer.get());
        }
        System.out.println();
    }
    private static boolean fileBuffer(CharBuffer buffer){
        if (index>=strings.length){
            return false;
        }
        String str = strings[index++];
        for (int i = 0; i < str.length(); i++) {
            buffer.put(str.charAt(i));
        }
        return true;
    }
}
