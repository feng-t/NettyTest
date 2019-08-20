package com.tomcat.res;

import com.tomcat.HttpServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author feng
 */
public class Response {

    private static final int buff_size = 1024;
    Request request;
    OutputStream output;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[buff_size];
        FileInputStream fs = null;
        try {
            String uri = request.getUri() == null ? "index" : "/".equals(request.getUri()) ? "index" : request.getUri();
            File file = new File(HttpServer.WEB_ROOT, uri);
            if (file.exists()) {
                fs = new FileInputStream(file);
                int ch = fs.read(bytes, 0, buff_size);
                while (ch != -1) {
                    output.write(bytes, 0, ch);
                    ch = fs.read(bytes, 0, buff_size);
                }
            } else {
                //不存在文件
                String errmsg = "HTTP/1.1 404 File Not Found\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: 23\r\n" +
                        "\r\n" +
                        "<h1>File Not Found</h1>";
                output.write(errmsg.getBytes());
            }
        } catch (Exception e) {
        } finally {
            if (fs != null) {
                fs.close();
            }
        }

    }
}
