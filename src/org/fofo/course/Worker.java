package org.fofo.course;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Worker {

    private String path;
    private String contentType;
    private HttpExchange httpExchange;

    /**
     *
     * @param path file path
     * @param contentType "text/html;charset=utf-8" or "multipart/form-data;charset=utf-8"
     * @param httpExchange
     */
    public Worker(String path, String contentType, HttpExchange httpExchange) {
        this.path = path;
        this.contentType = contentType;
        this.httpExchange = httpExchange;
    }


    public void process() throws IOException {
        Headers responseHeaders = httpExchange.getResponseHeaders();
        responseHeaders.set("Content-Type", contentType);
        System.out.println("path is " + path);
        File index = new File(path);
        long length = index.length();
        ByteBuffer byteBuffer = ByteBuffer.allocate(10000);
        FileInputStream is = new FileInputStream(path);
        FileChannel inChannel = is.getChannel();
        httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, length);
        OutputStream responseBody = httpExchange.getResponseBody();

        while(true)
        {
            int eof = inChannel.read(byteBuffer);
            if(eof == -1 ) break;
            byteBuffer.flip();
            responseBody.write(byteBuffer.array(),0, eof);
            byteBuffer.clear();
        }
        responseBody.close();
        inChannel.close();
        is.close();
    }
}
