package fr.isima.tp1.model;

import com.google.gson.annotations.SerializedName;

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
        }
    }


}
