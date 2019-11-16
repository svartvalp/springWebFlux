package com.kasyan313.ReactiveWeb;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ServletHttpHandlerAdapter;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import java.io.IOException;


public class Server {
    public static final String HOST = "localhost";
    public static final int PORT = 8080;

    private  Server() {
        HttpHandler handler = getHandler();
        Tomcat tomcatServer = new Tomcat();
        tomcatServer.setHostname(HOST);
        tomcatServer.setPort(PORT);
        Context context = tomcatServer.addContext("", System.getProperty("java.io.tmpdir"));
        ServletHttpHandlerAdapter servlet = new ServletHttpHandlerAdapter(handler);
        Tomcat.addServlet(context, "httpHandlerServlet", servlet);
        context.addServletMapping("/", "httpHandlerServlet");
        try {
            tomcatServer.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }

    public HttpHandler getHandler(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        return WebHttpHandlerBuilder.applicationContext(applicationContext).build();
    }

    public static void main(String[] args) {
        Server server = new Server();
        System.out.println("Press ENTER to exit.");
        try {
            System.in.read();
        } catch (IOException e) {
        }
    }
}
