package org.hbdev.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor@NoArgsConstructor
@Data
@Builder
public class ProductDto {
    private String name;
    private String description;
    private String sku;
    private double price;
    private int stock;
    private int categoryId;
    private String categoryName;
    private boolean active;
}
