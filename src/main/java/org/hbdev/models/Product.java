package org.hbdev.models;

import lombok.*;

@Data

@Builder //PatternDesign
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Product {
    @Builder.Default
    private int id = generateId(); //Automatically generated
    @NonNull
    private String name;
    private String description;
    @NonNull
    private int price;
    @NonNull
    private int quantity;
    private String sku;


    private static int PRODUCT_NUMBER;

    private static int generateId() {
        return ++PRODUCT_NUMBER;
    }

}
