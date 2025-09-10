package org.hbdev;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.hbdev.daos.DiscountDao;
import org.hbdev.daos.ProductDao;
import org.hbdev.entities.Category;
import org.hbdev.entities.Product;
import org.hbdev.helpers.DIConfigReader;
import org.hbdev.helpers.Tools;
import org.hbdev.mappers.ProductMapper;
import org.hbdev.mappers.ProductMapperImpl;
import org.hbdev.seeders.CategorySeeder;
import org.hbdev.services.ProductService;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

@Slf4j
public class Main {
    public static void main(String[] args) throws Exception {
        Map<String, String> config = DIConfigReader.loadConfig("dependencies/config.xml");
        List<String> dependencies = new ArrayList<>();
        config.entrySet().stream().forEach(entry -> {
            dependencies.add(entry.getValue());
        });
        ProductDao productDao = null; //Done
        DiscountDao discountDao = null;
        ProductMapper productMapper = null;
        ProductService service = null;
        for (String dependency : dependencies) {
            if (dependency.contains("daos") && dependency.contains("Product")) {
                Class<?> cDao = Class.forName(dependency);  //ProductJPADaoImpl
                productDao = (ProductDao) cDao.newInstance(); // -> New defaultConstructor(); -> new ProductJPADaoImpl();
            }
            if (dependency.contains("daos") && dependency.contains("Discount")) {
                Class<?> cDao = Class.forName(dependency);  //DiscountDaoImpl
                discountDao = (DiscountDao) cDao.newInstance(); // -> New defaultConstructor(); -> new DiscountDaoImpl();
            }
            if (dependency.contains("mapper") && dependency.contains("Product")) {
                Class<?> cMapper = Class.forName(dependency);  //ProductMapperImpl
                productMapper = (ProductMapper) cMapper.newInstance(); // -> New defaultConstructor(); -> new ProductMapperImpl();
            }
            if (dependency.contains("services") && dependency.contains("Product")) {
                Class<?> cService = Class.forName(dependency);  //ProductServiceImpl
                service = (ProductService) cService.newInstance(); // -> New defaultConstructor(); -> new ProductServiceImpl();
                Method setProductDao = cService.getMethod("setProductDao", ProductDao.class); // setProductDao(ProductDao)
                Method setDiscountDao = cService.getMethod("setDiscountDao", DiscountDao.class); // setProductDao(ProductDao)
                Method setProductMapper = cService.getMethod("setProductMapper", ProductMapper.class); // setProductDao(ProductDao)
                setProductDao.invoke(service, productDao);
                setDiscountDao.invoke(service, discountDao);
                setProductMapper.invoke(service, productMapper);

                /*System.out.println(service.save(productMapper.toDto(init())));*/
                service.findAll().forEach(System.out::println);
            }

        }


    }

    private static Product init() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hbdev.storeManager.oraclePU");
        EntityManager em = emf.createEntityManager();

        Faker faker = new Faker(Locale.of("fr"));
        CategorySeeder.insert(faker, em);


        Category category = em.find(Category.class, 1);
        return Product.builder().name("PC RYZEN").sku(Tools.generateSku(faker)).price(16000).quantity(faker.number().numberBetween(0, 30)).description(faker.lorem().sentence()).category(category).build();
    }
}