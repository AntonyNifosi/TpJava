package fr.isima.tp1.service;

import fr.isima.tp1.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService {

    @Autowired
    protected RuleRepository ruleRepository;
    @Autowired
    protected NutritionScoreRepository nutritionRepository;

    public List<Rule> getAll() {

        return ruleRepository.findAll();
    }

    private int calculateNutritionScore(ProductData p){
        int score = 0;
        List<Rule> rules = ruleRepository.findAll();
        Field[] fields = p.product.nutriments.getClass().getFields();
        List<Rule> trueRules = new ArrayList<>();
        for (Field f: fields){
            f.setAccessible(true);
            List<Rule> tmp = rules.stream().filter(r -> r.getName().equals(f.getName())).collect(Collectors.toList());
            int i = 0;
            float minB = 0;
            Rule previousBound = tmp.get(0);
            try {
                minB = (float)f.get(p.product.nutriments);
                if (f.getName().equals("salt_100g"))
                    minB *= 100;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            while (i < tmp.size() && minB > tmp.get(i).getMin_bound()) {
                previousBound = tmp.get(i);
                i++;
            }
            trueRules.add(previousBound);
        }

        for (Rule r: trueRules) {
            if (r.getComponent().equals("P"))
                score -= r.getPoints();
            else
                score += r.getPoints();
        }

        return score;
    }
    private String[] calculateRating(int score) {
        List<NutritionScore> nutritionScores = nutritionRepository.findAll();
        int i = 0;
        System.out.println("Score du produit : " + score);

        while (i < nutritionScores.size() && !(score >= nutritionScores.get(i).getLower_bound() && score <= nutritionScores.get(i).getUpper_bound())) {
            System.out.println("Calcul du rang - Valeur de i : " + i);
            i++;
        }

        if (i >= nutritionScores.size())
            i = nutritionScores.size() - 1;

        return new String[]{nutritionScores.get(i).getClasse(), nutritionScores.get(i).getColor()};
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
