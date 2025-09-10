package org.hbdev.mappers;

import org.hbdev.dtos.ProductDto;
import org.hbdev.entities.Product;

public interface ProductMapper {
    Product toEntity(ProductDto productDto);
    ProductDto toDto(Product product);
    ProductDto toDtoWithoutCat(Product product);
}
