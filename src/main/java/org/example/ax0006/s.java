package org.example.ax0006;

import org.h2.tools.Server;

public class s {
    public static void main(String[] args) throws Exception {
        Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
    }
}