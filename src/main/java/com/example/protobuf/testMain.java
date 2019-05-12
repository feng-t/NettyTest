package com.example.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

public class testMain {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        Test.info info = Test.info.newBuilder().setAddress("司法解释的").setAge(2).setName("房贷首付").build();
        byte[] infoArray=info.toByteArray();

        Test.info parse = Test.info.parseFrom(infoArray);

        System.out.println(parse.getAddress());
        System.out.println(parse.getName());
        System.out.println(parse.getAge());
    }
}
