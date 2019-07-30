package com.tomcat.res;

import java.io.IOException;
import java.io.InputStream;

/**
 * 表示一个HTTP请求
 * @author feng
 */
public class Request {
    private InputStream input;
    private String uri;

    public Request(InputStream input) {
        this.input = input;
    }

    /**
     * 解析url
     *
     * @param requestString
     */
    private String parseUri(String requestString) {
        int index1, index2;
        index1 = requestString.indexOf(' ');
        if (index1 > -1) {
            index2 = requestString.indexOf(' ', index1 + 1);
            if (index2 > index1) {
                return requestString.substring(index1 + 1, index2);
            }
        }
        return null;
    }

    /**
     * 解析http原始数据，调用私有方法 parseUri 解析http的URI
     */
    public void parse() {
        StringBuffer buffer = new StringBuffer(2048);
        byte[] bytes = new byte[2048];
        int i;
        try {
            i = input.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            i = -1;
        }
        for (int j = 0; j < i; j++) {
            buffer.append((char) bytes[j]);
        }
        uri = parseUri(buffer.toString());
    }

    public String getUri() {
        return uri;
    }
}
