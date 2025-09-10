package org.hbdev.services;

import org.hbdev.dtos.ProductDto;
import org.hbdev.entities.Product;

public interface ProductService extends CrudService<ProductDto, Integer> {
    void orderNow(String sku);
}
