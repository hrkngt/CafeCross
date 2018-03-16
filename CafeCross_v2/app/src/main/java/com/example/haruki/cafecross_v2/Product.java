package com.example.haruki.cafecross_v2;

public class Product{

    //TODO: might implement id and imageview as well

    private String id;
    private String product_name;
    private String description;
    private int price;
    private String image;


    public Product(String id, String product_name, String description, int price, String image) {
        this.id = id;
        this.product_name = product_name;
        this.description = description;
        this.price = price;
        this.image = image;
    }


    public String getId() {
        return id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public String getImage() { return image; }

}
