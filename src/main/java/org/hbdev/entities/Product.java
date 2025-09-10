package org.hbdev.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Entity
@Builder //PatternDesign
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id; //Automatically generated
    @NonNull
    private String name;
    private String description;
    @NonNull
    private double price;
    @NonNull
    private int quantity;
    private String sku;

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
