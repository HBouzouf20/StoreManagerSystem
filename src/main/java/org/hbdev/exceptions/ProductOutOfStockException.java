package org.hbdev.exceptions;

import org.hbdev.entities.Product;

public class ProductOutOfStockException extends RuntimeException {
    public ProductOutOfStockException(String message) {
        super(message);
    }
    public ProductOutOfStockException(Product product) {
        super("Product " + product.getName() + " is out of stock");
    }
}
