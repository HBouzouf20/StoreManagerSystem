package org.hbdev.daos.Spring;

import net.datafaker.Faker;
import org.hbdev.daos.ProductDao;
import org.hbdev.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductSpringDaoImpl implements ProductDao {

    public ProductSpringDaoImpl() {
        System.out.println( "✅ Connected to Spring Data!");
    }

    /**
     * @param sku
     * @return
     */
    @Override
    public Product findBySku(String sku) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<Product> findInStockProducts() {
        return List.of();
    }

    /**
     * @param product
     */
    @Override
    public void notify(Product product) {

    }

    /**
     * @return
     */
    @Override
    public List<Product> findAll() {

        Faker faker = new Faker();
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            products.add(Product.builder()
                            .name(faker.name().firstName())
                            .sku(faker.book().title())
                            .price(faker.number().numberBetween(1, 100))
                            .description(faker.lorem().sentence())
                    .build()
            );
        }
        return products;
    }

    /**
     * @param integer
     * @return
     */
    @Override
    public Product findById(Integer integer) {
        return null;
    }

    /**
     * @param product
     * @return
     */
    @Override
    public Product save(Product product) {
        return null;
    }

    /**
     * @param product
     * @return
     */
    @Override
    public Product update(Product product) {
        return null;
    }

    /**
     * @param product
     */
    @Override
    public void delete(Product product) {

    }
}
