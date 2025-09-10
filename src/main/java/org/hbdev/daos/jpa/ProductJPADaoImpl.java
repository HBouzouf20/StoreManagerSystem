package org.hbdev.daos.jpa;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hbdev.daos.ProductDao;
import org.hbdev.entities.Product;

import java.util.List;

@Slf4j
public class ProductJPADaoImpl implements ProductDao {
    private EntityManager em;

    public ProductJPADaoImpl() {
        System.out.println(" ✅ Connected to Oracle database!\n");
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("hbdev.storeManager.oraclePU");
        this.em = emf.createEntityManager();
    }
    /**
     * @param sku
     * @return
     */
    @Override
    public Product findBySku(String sku) throws NoResultException {
        return em.createQuery("Select p from Product p WHERE p.sku = :sku", Product.class)
                .setParameter("sku", sku)
                .getSingleResult();
    }

    /**
     * @return
     */
    @Override
    public List<Product> findInStockProducts() {
        return em.createQuery("Select p from Product p WHERE p.quantity > 0", Product.class)
                .getResultList();
    }

    /**
     * @param product
     */
    @Override
    public void notify(Product product) {
        System.out.println("Notifying Out of stock product " + product.getName());
    }

    /**
     * @return
     */
    @Override
    public List<Product> findAll() {
        return em.createQuery("Select p from Product p ", Product.class)
                .getResultList();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Product findById(Integer id) {
        return em.find(Product.class, id);
    }

    /**
     * @param product
     * @return
     */
    @Override
    public Product save(Product product) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(product);
        tx.commit();
        return product;
    }

    /**
     * @param product
     * @return
     */
    @Override
    public Product update(Product product) {
        em.getTransaction().begin();
        Product p = em.merge(product);
        em.getTransaction().commit();

        return p;
    }

    /**
     * @param product
     */
    @Override
    public void delete(Product product) {
        em.getTransaction().begin();
        if (!em.contains(product)) {
            product = em.merge(product);
        }
        log.warn("Deleting product: {}", product);
        em.remove(product);
        em.getTransaction().commit();
    }
}
