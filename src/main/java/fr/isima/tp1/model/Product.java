package fr.isima.tp1.model;


public class Product {
    private int id;
    private String barCode;
    private String name;
    private float nutritionScore;
    private String classe;
    private String color;



    public Product(ProductData p) {
        this.barCode = p.code;
        this.name = p.product.generic_name_fr;
    }

    public void setNutritionScore(float nutritionScore) {
        this.nutritionScore = nutritionScore;
    }

    public void setClasse(String classe) {
        this.classe = classe;
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