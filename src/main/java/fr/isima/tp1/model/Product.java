package fr.isima.tp1.model;

import javax.persistence.*;

/* Classe représentant un produit récupérer depuis l'API FoodFact */
@Entity
@Table(name = "product")
public class Product {
    @Column(name = "id" )
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public long id;
    private String barCode;
    private String name;
    private float nutritionScore;
    private String classe;
    private String color;


    public Product() {

    }

    public Product(ProductData p) {
        this.barCode = p.code;
        this.name = p.product.generic_name_fr;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getNutritionScore() {
        return nutritionScore;
    }

    public void setNutritionScore(float nutritionScore) {
        this.nutritionScore = nutritionScore;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", barCode='" + barCode + '\'' +
                ", name='" + name + '\'' +
                ", nutritionScore=" + nutritionScore +
                ", classe='" + classe + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}