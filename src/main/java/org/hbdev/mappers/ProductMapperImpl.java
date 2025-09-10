package org.hbdev.mappers;

import org.hbdev.dtos.ProductDto;
import org.hbdev.entities.Category;
import org.hbdev.entities.Product;

public class ProductMapperImpl implements ProductMapper {
    /**
     * @param productDto
     * @return
     */
    @Override
    public Product toEntity(ProductDto productDto) {
        return Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .sku(productDto.getSku())
                .quantity(productDto.getStock())
                .category(Category.builder()
                        .id(productDto.getCategoryId())
                        .name(productDto.getCategoryName())
                        .isActive(productDto.isActive())
                        .build())
                .build();
    }

    /**
     * @param product
     * @return
     */
    @Override
    public ProductDto toDto(Product product) {
        return ProductDto.builder()
                .name(product.getName())
                .description(product.getDescription())
                .stock(product.getQuantity())
                .price(product.getPrice())
                .categoryName(product.getCategory().getName() == null ? "" : product.getCategory().getName())
                .active(product.getCategory().isActive())
                .sku(product.getSku())
                .categoryId(product.getCategory().getId())
                .build();
    }

    /**
     * @param product
     * @return
     */
    @Override
    public ProductDto toDtoWithoutCat(Product product) {

        return ProductDto.builder()
                .name(product.getName())
                .description(product.getDescription())
                .stock(product.getQuantity())
                .price(product.getPrice())
                .sku(product.getSku())
                .build();
    }
}
