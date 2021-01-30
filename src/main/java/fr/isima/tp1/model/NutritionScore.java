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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public int getLower_bound() {
        return lower_bound;
    }

    public void setLower_bound(int lower_bound) {
        this.lower_bound = lower_bound;
    }

    public int getUpper_bound() {
        return upper_bound;
    }

    public void setUpper_bound(int upper_bound) {
        this.upper_bound = upper_bound;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
