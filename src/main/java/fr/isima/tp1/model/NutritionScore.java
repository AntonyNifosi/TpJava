package fr.isima.tp1.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NUTRITION_SCORE")
public class NutritionScore {
    @Id
    private int id;
    private String classe;
    private int lower_bound;
    private int upper_bound;
    private String color;
}
