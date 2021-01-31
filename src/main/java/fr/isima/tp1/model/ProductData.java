package fr.isima.tp1.model;

import com.google.gson.annotations.SerializedName;

/* Model pour récupération des données JSON d'un produit */
public class ProductData {

    public String code;
    public Product product;

    public static class Product {
        public String generic_name;
        public Nutriments nutriments;

        public static class Nutriments {
            public float energy_100g;
            @SerializedName("saturated-fat_100g")
            public float saturatedfat_100g;
            public float sugars_100g;
            public float salt_100g;
            public float fiber_100g;
            public float proteins_100g;

            @Override
            public String toString() {
                return "Nutriments{" +
                        "energy_100g=" + energy_100g +
                        ", saturatedfat_100g=" + saturatedfat_100g +
                        ", sugars_100g=" + sugars_100g +
                        ", salt_100g=" + salt_100g +
                        ", fiber_100g=" + fiber_100g +
                        ", proteins_100g=" + proteins_100g +
                        '}';
            }

            public Nutriments(float energy_100g, float saturatedfat_100g, float sugars_100g, float salt_100g, float fiber_100g, float proteins_100g) {
                this.energy_100g = energy_100g;
                this.saturatedfat_100g = saturatedfat_100g;
                this.sugars_100g = sugars_100g;
                this.salt_100g = salt_100g;
                this.fiber_100g = fiber_100g;
                this.proteins_100g = proteins_100g;
            }
        }

        public Product(String generic_name, Nutriments nutriments) {
            this.generic_name = generic_name;
            this.nutriments = nutriments;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "generic_name_fr='" + generic_name + '\'' +
                    ", nutriments=" + nutriments +
                    '}';
        }
    }

    public ProductData(String code, Product product) {
        this.code = code;
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductData{" +
                "code='" + code + '\'' +
                ", product=" + product +
                '}';
    }
}
