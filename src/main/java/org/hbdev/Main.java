package org.hbdev;

import net.datafaker.Faker;
import org.hbdev.daos.DatabaseConnection;
import org.hbdev.daos.ProductDao;
import org.hbdev.daos.ProductDaoImpl;
import org.hbdev.models.Product;

import java.io.IOException;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws IOException {
        DatabaseConnection.getInstance();
        Faker faker = new Faker(new Locale("en-US"));

        ProductDao dao = new ProductDaoImpl();
       /* for (int i = 0; i < 400; i++) {
            Product product = Product.builder()
                    .name(faker.commerce().productName())
                    .price(faker.random().nextInt(2000))
                    .quantity(faker.random().nextInt(100))
                    .description(faker.lorem().sentence())
                    .build();
           // dao.save(product);

        }*/
        dao.findAll().forEach(System.out::println);

        System.out.println("Press any button to exit...");
        System.in.read();
        //server.stop();
        System.out.println("H2 server stopped.");
    }
}