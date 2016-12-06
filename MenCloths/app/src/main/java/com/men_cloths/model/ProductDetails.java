package com.men_cloths.model;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/12/6.
 */
public class ProductDetails {
    int product;
    String description;
    String type;
    double price;
    double original;

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOriginal() {
        return original;
    }

    public void setOriginal(double original) {
        this.original = original;
    }
}
