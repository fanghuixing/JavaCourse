package org.fofo.course;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class ResponseFile implements Runnable {
    private String path;
    private String contentType;
    private HttpExchange httpExchange;

    /**
     *
     * @param path file path
     * @param contentType "text/html;charset=utf-8" or "multipart/form-data;charset=utf-8"
     * @param httpExchange
     */
    public ResponseFile(String path, String contentType, HttpExchange httpExchange) {
        this.path = path;
        this.contentType = contentType;
        this.httpExchange = httpExchange;
    }


    @Override
    public void run() {

        Worker wk = new Worker(path, contentType, httpExchange);

        try {
            wk.process();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
