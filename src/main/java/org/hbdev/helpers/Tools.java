package org.hbdev.helpers;

import net.datafaker.Faker;

public class Tools {
    public static String generateSku(Faker faker){
        String categoryCode = faker.commerce().department().substring(0, 3).toUpperCase(); // ex: ELE
        String productCode = faker.commerce().productName().replaceAll("[^A-Za-z0-9]", "").substring(0, 4).toUpperCase(); // ex: PHON
        String uniqueId = faker.number().digits(5); // ex: 48293

        return categoryCode + "-" + productCode + "-" + uniqueId;
    }
}
