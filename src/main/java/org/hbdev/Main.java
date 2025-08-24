package org.hbdev;

import org.hbdev.daos.DatabaseConnection;
import org.hbdev.daos.ProductDao;
import org.hbdev.daos.ProductDaoImpl;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DatabaseConnection.getInstance();

        ProductDao dao = new ProductDaoImpl();
        ProductDao dao1 = new ProductDaoImpl();

        System.out.println("Press any button to exit...");
        System.in.read();
        //server.stop();
        System.out.println("H2 server stopped.");
    }
}