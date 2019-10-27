package org.fofo.course;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.URI;

public class IndexHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println("index");
        URI uri = httpExchange.getRequestURI();
        if ("/".equals(uri.getPath()) == false) {
            //when the uri is not /
            if (uri.getPath().startsWith("/")) {
                ResponseFile rf = new ResponseFile(uri.getPath().substring(1), "multipart/form-data;charset=utf-8", httpExchange);
                Thread rft = new Thread(rf);
                rft.start();
            }

            return;
        }

        Worker wk = new Worker("index.html", "text/html;charset=utf-8", httpExchange);
        wk.process();
        return;
    }

}
