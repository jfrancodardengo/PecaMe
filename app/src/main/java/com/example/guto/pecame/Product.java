package com.example.guto.pecame;

import java.util.List;

/**
 * Created by GUTO on 02/05/2018.
 */

public class Product {
    private String description;
    private String price;
    private int quantity;
    private List ingredients;

    public Product() {
    }

    public Product(String description, String price) {
        this.description = description;
        this.price = price;
    }

    public Product(String description, String price, int quantity) {
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(String description, String price, int quantity, List ingredients) {
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.ingredients = ingredients;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List getIngredients() {
        return ingredients;
    }

    public void setIngredients(List ingredients) {
        this.ingredients = ingredients;
    }
}
