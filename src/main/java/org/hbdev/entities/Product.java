package org.hbdev.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "products")
@Data
@Builder //PatternDesign
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //Auto_increment
    @NonNull
    @Column(name = "title")
    private String name;
    private String description;
    @NonNull
    private double price;
    @NonNull
    private int quantity;
    @Column(unique = true, nullable = false, updatable = false)
    private String sku;
    @ManyToOne
    @JoinColumn(name ="cat_id")
    private Category category;

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
            🏷 Category: %s
            """.formatted(id, name, description == null ? "-" : description, price, quantity, sku == null ? "-" : sku, category == null ? "-" : category);
    }

}
