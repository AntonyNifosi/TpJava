package fr.isima.tp1.model;


public class Product {
    private int id;
    private String barCode;
    private String name;
    private float nutritionScore;



    public Product(ProductData p) {
        this.barCode = p.code;
        this.name = p.product.generic_name_fr;
        this.nutritionScore = p.product.nutriments.salt_100g;
    }

}