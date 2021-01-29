package fr.isima.tp1.service;

import fr.isima.tp1.model.ProductData;
import fr.isima.tp1.model.Rule;
import fr.isima.tp1.model.RuleRepository;
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

    public List<Rule> getAll() {

        return ruleRepository.findAll();
    }

    public int calulateNutritionScore(ProductData p){
        int score = 0;
        List<Rule> rules = ruleRepository.findAll();
        Field[] fields = p.product.nutriments.getClass().getFields();
        List<Rule> trueRules = new ArrayList<>();
        for (Field f: fields){
            f.setAccessible(true);
            List<Rule> tmp = rules.stream().filter(r -> r.getName().equals(f.getName())).collect(Collectors.toList());
            int i = 0;
            float minB = 0;
            Rule previousBound = null;
            try {
                minB = (float)f.get(p.product.nutriments);
                if (f.getName().equals("salt_100g"))
                    minB *= 1000;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            while (minB > tmp.get(i).getMin_bound()) {
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
}
