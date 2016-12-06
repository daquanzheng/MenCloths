package com.men_cloths.model;

/**
 * Created by Administrator on 2016/12/4.
 */

public class Commodity {
    private String name;
    private String rule;
    private String price;
    private String iamge;
    private String id;

    public String getIamge() {
        return iamge;
    }


    public Commodity(String name, String rule, String price, String iamge, String id) {
        this.name = name;
        this.rule = rule;
        this.price = price;
        this.iamge = iamge;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRule() {
        return rule;
    }

    public String getPrice() {
        return price;
    }
}
