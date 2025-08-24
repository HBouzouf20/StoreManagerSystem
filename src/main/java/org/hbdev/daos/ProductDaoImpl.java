package org.hbdev.daos;

import lombok.extern.slf4j.Slf4j;
import org.hbdev.models.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Slf4j
public class ProductDaoImpl implements ProductDao {
    private final Connection connection;

    public ProductDaoImpl() {
        connection = DatabaseConnection.getInstance().getConnection();
        createTable();
    }
    public void createTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS products (" +
                         "id INT PRIMARY KEY, " +
                         "name VARCHAR(100), " +
                         "price DOUBLE)");
            //log.info("✅ Table 'products' ready");
        } catch (SQLException e) {
            log.error("❌ Error creating table or table already exists", e);
        }
    }
    /**
     * @return
     */
    @Override
    public List<Product> findAll() {
        return List.of();
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
}
