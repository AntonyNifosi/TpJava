package fr.isima.tp1.domain;

import com.google.gson.Gson;
import fr.isima.tp1.model.ProductData;
import org.springframework.stereotype.Service;


import java.io.*;
import java.net.URL;

@Service
public class OpenFoodFact {
    private static final String BASE_URL = "https://fr.openfoodfacts.org/api/v0/produit";

    public static ProductData toProductData(Reader reader) {
        ProductData p = new Gson().fromJson(reader, ProductData.class);
        return p;
    }

    /* Requête d'un produit à l'API FoodFact d'après son code bar */
    public static ProductData getProductById(String id) throws IOException {
        StringBuilder urlStringBuilder = new StringBuilder();
        urlStringBuilder.append(BASE_URL).append("/").append(id);

        try (InputStream is = new URL(urlStringBuilder.toString()).openStream();
             Reader reader = new InputStreamReader(is)) {
            return toProductData(reader);
        }
    }
}
