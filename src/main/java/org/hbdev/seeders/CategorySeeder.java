package org.hbdev.seeders;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import net.datafaker.Faker;
import org.hbdev.entities.Category;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CategorySeeder {
    public static void insert(Faker faker, EntityManager em){

        EntityTransaction transaction= em.getTransaction();
        Query query = em.createQuery("select COUNT(*) from Category c");
        Number count = (Number) query.getSingleResult();
        if (count.intValue() == 0){
            transaction.begin();
            importData().forEach(em::persist);
            transaction.commit();
        }
        else {
            System.out.printf("%d Categories already exists.\n",count.intValue());
        }

    }
    private static List<Category> importData(){
        ObjectMapper mapper = new ObjectMapper();
        List<Category> categories = new ArrayList<>();

        InputStream inputStream = CategorySeeder.class
                .getClassLoader()
                .getResourceAsStream("static/categories.json"); //Read from resources files
        if (inputStream == null) {
            throw  new RuntimeException("categories.json not found");
        }
        try {
            JsonNode root = mapper.readTree(inputStream);
            JsonNode categoriesNode = root.get("categories");

            if (categoriesNode != null && categoriesNode.isArray()) {
                for (JsonNode node : categoriesNode) {
                    categories.add(Category.builder().name(node.asText()).isActive(true).build());
                }
                return categories;
            }
            else
                throw  new RuntimeException("Categories not found.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
