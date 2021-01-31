package fr.isima.tp1.model;

import com.google.gson.annotations.SerializedName;

/* Model pour récupération des données JSON d'un produit */
public class ProductData {

    public String code;
    public Product product;
    public class Product {
        public String generic_name_fr;
        public Nutriments nutriments;
        public class Nutriments{
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
        }

        @Override
        public String toString() {
            return "Product{" +
                    "generic_name_fr='" + generic_name_fr + '\'' +
                    ", nutriments=" + nutriments +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ProductData{" +
                "code='" + code + '\'' +
                ", product=" + product +
                '}';
    }
}
