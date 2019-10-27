package org.fofo.course;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;

/**
 * 在局域网内，提供文件的简单下载功能
 */
public class Main {

    public static void main(String[] arg) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(80), 0);
        server.createContext("/test", new TestHandler());
        server.createContext("/", new IndexHandler());
        server.start();
    }

    static  class TestHandler implements HttpHandler{
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            Headers responseHeaders = httpExchange.getResponseHeaders();
            responseHeaders.set("Content-Type", "text/html;charset=utf-8");
            String response = "同学，你好！";
            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.getBytes("UTF-8").length);
            OutputStream responseBody = httpExchange.getResponseBody();
            OutputStreamWriter writer = new OutputStreamWriter(responseBody, "UTF-8");
            writer.write(response);
            writer.close();
            responseBody.close();
        }
    }
}