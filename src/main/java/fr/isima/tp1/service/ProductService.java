package fr.isima.tp1.service;

import fr.isima.tp1.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;



@Service
public class ProductService {

    @Autowired
    protected RuleRepository ruleRepository;
    @Autowired
    protected NutritionScoreRepository nutritionRepository;

    public List<Rule> getAll() {

        return ruleRepository.findAll();
    }

    private int calculateNutritionScore(ProductData p) {
        return calculateNScore(p) - calculatePScore(p);
    }

    private int calculateNScore(ProductData p){
        int score = 0;
        String[] fields = {"energy_100g", "salt_100g", "saturatedfat_100g", "sugars_100g"};
        float[] fieldsValues = { p.product.nutriments.energy_100g,
                                 p.product.nutriments.salt_100g,
                                 p.product.nutriments.saturatedfat_100g,
                                 p.product.nutriments.sugars_100g };

        for (int i = 0; i < fields.length; i++) {
            System.out.println("Retour de la requete : " + ruleRepository.findByNameAndValue(fieldsValues[i], fields[i]));
            score += ruleRepository.findByNameAndValue(fieldsValues[i], fields[i]);
        }
        System.out.println("Valur du score N : " + score);

        return score;
    }

    private int calculatePScore(ProductData p){
        int score = 0;
        String[] fields = {"fiber_100g", "proteins_100g"};
        float[] fieldsValues = { p.product.nutriments.fiber_100g,
                                 p.product.nutriments.proteins_100g };

        for (int i = 0; i < fields.length; i++) {
            score += ruleRepository.findByNameAndValue(fieldsValues[i], fields[i]);
        }
        System.out.println("Valur du score P : " + score);

        return score;
    }

    private String[] calculateRating(int score) {
        NutritionScore nutritionScores = nutritionRepository.findByScore(score);

        return new String[]{nutritionScores.getClasse(), nutritionScores.getColor()};
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
