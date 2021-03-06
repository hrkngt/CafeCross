package com.example.haruki.cafecross_v2;

import android.content.res.Resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;


public class ShoppingCartHelper {

    public static final String PRODUCT_INDEX = "PRODUCT_INDEX";

    private static List<Product> catalog;
    private static Map<Product, ShoppingCartEntry> cartMap = new HashMap<Product, ShoppingCartEntry>();


    public static void setQuantity(Product product, int quantity) {
        // Get the current cart entry
        ShoppingCartEntry curEntry = cartMap.get(product);
        // If a current cart entry doesn't exist, create one
        if(curEntry == null) {
            curEntry = new ShoppingCartEntry(product, quantity);
            cartMap.put(product, curEntry);
            return;
        }
        // Update the quantity
        curEntry.setQuantity(quantity);

    }

    public static int getProductQuantity(Product product) {

        // Get the current cart entry
        ShoppingCartEntry curEntry = cartMap.get(product);
        if(curEntry != null) {
            return curEntry.getQuantity();
        }
        return 0;

    }

    public static void removeProduct(Product product) {
        cartMap.remove(product);
    }

    public static List<Product> getCartList() {
        List<Product> cartList = new Vector<Product>(cartMap.keySet().size());
        for(Product p : cartMap.keySet()) {
            cartList.add(p);
        }
        return cartList;
    }

    public static int getAllProductQuantity(){
        int itemsNumInCart = 0;
        for(Product p: getCartList()){
            itemsNumInCart += getProductQuantity(p);
        }
        return itemsNumInCart;
    }



}
