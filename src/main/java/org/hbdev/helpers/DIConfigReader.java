package org.hbdev.helpers;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DIConfigReader {

    public static Map<String, String> loadConfig(String xmlPath) {
        // LinkedHashMap preserves insertion order
        Map<String, String> beans = new LinkedHashMap<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(new File(xmlPath));
            doc.getDocumentElement().normalize();

            NodeList beanList = doc.getElementsByTagName("bean");

            for (int i = 0; i < beanList.getLength(); i++) {
                Element bean = (Element) beanList.item(i);
                String name = bean.getAttribute("name");
                String value = bean.getAttribute("value");

                if (!name.isEmpty()) {
                    beans.put(name, value);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return beans;
    }

}
