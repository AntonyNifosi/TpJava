package fr.isima.tp1.controller;

import com.google.gson.Gson;
import fr.isima.tp1.model.Rule;
import fr.isima.tp1.domain.OpenFoodFact;
import fr.isima.tp1.model.ProductData;
import fr.isima.tp1.model.Product;
import fr.isima.tp1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService rs;

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String getProductById(@PathVariable final String id) {
        try {
            ProductData p = OpenFoodFact.getProductById(id);

            return new Gson().toJson(new Product(p, rs.calulateNutritionScore(p)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/rules", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Rule> getRules() {
           // System.out.println(rs.getAll());
            return rs.getAll();
        }
}
