package fr.isima.tp1.controller;

import com.google.gson.Gson;
import fr.isima.tp1.model.Rule;
import fr.isima.tp1.domain.OpenFoodFact;
import fr.isima.tp1.model.ProductData;
import fr.isima.tp1.model.Product;
import fr.isima.tp1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService rs;

    /* Route pour l'accès aux informations d'un produit avec son code bar */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable final String id) {
        try {
            return ResponseEntity.ok(rs.getProductByBarcode(id));
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
