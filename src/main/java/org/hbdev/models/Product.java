package org.hbdev.models;

import lombok.*;

@Data

@Builder //PatternDesign
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Product {
    private int id; //Automatically generated
    @NonNull
    private String name;
    private String description;
    @NonNull
    private double price;
    @NonNull
    private int quantity;
    private String sku;


    private static int PRODUCT_NUMBER;

    @Override
    public String toString() {
        return """
            🆔 ID: %d
            🏷️ Name: %s
            📝 Description: %s
            💰 Price: %.2f
            📦 Quantity: %d
            🔖 SKU: %s
            """.formatted(id, name, description == null ? "-" : description, price, quantity, sku == null ? "-" : sku);
    }

}
