package org.hbdev.services;

import lombok.Setter;
import org.hbdev.daos.DiscountDao;
import org.hbdev.daos.ProductDao;
import org.hbdev.dtos.ProductDto;
import org.hbdev.entities.Product;
import org.hbdev.exceptions.ProductNotFoundException;
import org.hbdev.exceptions.ProductOutOfStockException;
import org.hbdev.mappers.ProductMapper;

import java.util.List;

@Setter
public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;
    private ProductMapper productMapper;
    private DiscountDao discountDao;

    /**
     * @param product
     * @return
     */
    @Override
    public ProductDto save(ProductDto product) {
        Product p = productMapper.toEntity(product);
        p.setPrice(product.getPrice() - (p.getPrice() * discountDao.getValue()));
        ProductDto dto = productMapper.toDto(
                productDao.save(p)
        );
        System.out.println("DTP -> " + dto);
        return dto;
    }

    /**
     * @param product
     * @return
     */
    @Override
    public ProductDto update(ProductDto product) {
        return productMapper.toDto(
                productDao.update(productMapper.toEntity(product))
        );
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ProductDto findById(Integer id) {
        Product product = productDao.findById(id);
        if (product == null) {
            throw new ProductNotFoundException();
        }
        return productMapper.toDto(product);
    }

    /**
     * @return
     */
    @Override
    public List<ProductDto> findAll() {
        return productDao.findAll().stream().map(p -> p.getCategory() == null ? productMapper.toDtoWithoutCat(p) : productMapper.toDto(p)).toList();
    }

    /**
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        productDao.delete(productDao.findById(id));
    }

    /**
     * @param sku
     */
    @Override
    public void orderNow(String sku) {
        Product product = productDao.findBySku(sku);
        if (product == null) {
            throw new ProductNotFoundException("Product with sku " + sku + " not found");
        }
        if (product.getQuantity() == 0) throw new ProductOutOfStockException(product);
        product.setQuantity(product.getQuantity() - 1);
        productDao.update(product);
    }

}
