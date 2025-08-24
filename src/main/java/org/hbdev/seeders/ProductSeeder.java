package org.hbdev.seeders;

import net.datafaker.Faker;
import org.hbdev.models.Product;

import java.util.Locale;

public class ProductSeeder {
    private static final Faker faker = new Faker(new Locale("fr"));

    public static void init(){
        for (int i = 0; i < 30; i++) {
            Product product = Product.builder()
                    .name(faker.commerce().productName())
                    .description(faker.lorem().sentence())
                    .price(faker.number().numberBetween(50,5000))
                    .quantity(faker.number().numberBetween(10,100))
                    .build(); //Product created (id AutoIncrement)

        }
    }

}
