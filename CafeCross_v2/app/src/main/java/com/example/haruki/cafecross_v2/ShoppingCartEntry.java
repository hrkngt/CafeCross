package com.example.haruki.cafecross_v2;

public class ShoppingCartEntry {

    private Product product;
    private int quantity;

    public ShoppingCartEntry(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct(){
        return product;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public void addOne(){
        this.quantity += 1;
    }

    public void subOne(){
        this.quantity -= 1;
    }

}
