package fr.isima.tp1.service;

import fr.isima.tp1.domain.OpenFoodFact;
import fr.isima.tp1.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.List;


/* Classe gérant tous les services concernant les produits comme :
    - Calcul du score nutritrionnel
    - Calcul du rang du produit en fonction de son score
    - Conversion d'un Product_Data en Product
    - Stockage d'un  Product dans le ProductRepository
 */
@Service
public class ProductService {

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private NutritionScoreRepository nutritionRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Rule> getAll() {
        return ruleRepository.findAll();
    }

    private int calculateNutritionScore(ProductData p) {
        return calculateNScore(p) - calculatePScore(p);
    }

    private int calculateNScore(ProductData p) {
        int score = 0;
        String[] fields = {"energy_100g", "salt_100g", "saturated-fat_100g", "sugars_100g"};
        float[] fieldsValues = {p.product.nutriments.energy_100g,
                p.product.nutriments.salt_100g,
                p.product.nutriments.saturatedfat_100g,
                p.product.nutriments.sugars_100g};

        for (int i = 0; i < fields.length; i++) {
            score += ruleRepository.findByNameAndValue(fieldsValues[i], fields[i]);
        }

        return score;
    }

    private int calculatePScore(ProductData p) {
        int score = 0;
        String[] fields = {"fiber_100g", "proteins_100g"};
        float[] fieldsValues = {p.product.nutriments.fiber_100g,
                p.product.nutriments.proteins_100g};

        for (int i = 0; i < fields.length; i++) {
            score += ruleRepository.findByNameAndValue(fieldsValues[i], fields[i]);
        }

        return score;
    }

    private String[] calculateRating(int score) {
        NutritionScore nutritionScores = nutritionRepository.findByScore(score);

        return new String[]{nutritionScores.getClasse(), nutritionScores.getColor()};
    }

    public Product getProductByBarcode(String barCode) throws IOException {
        Product product = productRepository.findByBarCode(barCode);

        if (product == null) {
            ProductData pData = OpenFoodFact.getProductById(barCode);
            if (pData.product == null) {
                throw new IOException("Code barre invalide");
            }

            product = toProduct(pData);
            productRepository.save(product);
        }
        return product;
    }

    public Product toProduct(ProductData p) {
        System.out.println(p);
        int score = calculateNutritionScore(p);
        String[] rating = calculateRating(score);
        Product product = new Product(p);
        product.setNutritionScore(score);
        product.setClasse(rating[0]);
        product.setColor(rating[1]);

        return product;
    }
}
