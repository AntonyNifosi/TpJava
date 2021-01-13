package fr.isima.tp1.controllers;

import com.google.gson.Gson;
import fr.isima.tp1.service.OpenFoodFact;
import fr.isima.tp1.model.ProductData;
import fr.isima.tp1.model.Product;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;

@RestController
public class ProductController {

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String getProductById(@PathVariable final String id) {
        try {
            ProductData p = OpenFoodFact.getProductById(id);
            return new Gson().toJson(new Product(p));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
