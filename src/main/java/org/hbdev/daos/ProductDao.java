package org.hbdev.daos;

import org.hbdev.models.Product;

import java.util.List;

public interface ProductDao extends CrudDao<Product,Integer> {
    Product findBySku(String sku);
    List<Product> findInStockProducts();
}
