package org.hbdev.daos;

import lombok.extern.slf4j.Slf4j;
import org.hbdev.models.Product;

import java.sql.*;
import java.util.ArrayList;
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
                         "id INT AUTO_INCREMENT PRIMARY KEY, " +
                         "name VARCHAR(100), " +
                         "description VARCHAR(100), " +
                         "quantity INT, " +
                         "sku VARCHAR(100), " +
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
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try {
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                products.add(Product.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .price(rs.getDouble("price"))
                        .description(rs.getString("description"))
                        .sku(rs.getString("sku"))
                        .quantity(rs.getInt("quantity"))
                        .build());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        String sql = """
                INSERT INTO products (
                name, description, quantity, sku, price
                ) Values (?, ?, ?, ?, ?)
                """;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setInt(3, product.getQuantity());
            ps.setString(4, product.getSku());
            ps.setDouble(5, product.getPrice());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving product " + product.getName(), e);
        }
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
