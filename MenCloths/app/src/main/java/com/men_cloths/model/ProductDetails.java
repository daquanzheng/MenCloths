package com.men_cloths.model;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;

/**
 * Created by Administrator on 2016/12/6.
 */
public class ProductDetails {
    Bitmap product;
    String description;
    String type;
    double price;
    double original;

    public Bitmap getProduct() {
        return product;
    }

    public void setProduct(Bitmap product) {
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
