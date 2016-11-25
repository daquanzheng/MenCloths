package com.men_cloths.model;

public class Shop {
	private int image;
	private String title;
	private String size;
	private String price;
	
	public int getImage() {
		return image;
	}
	public String getTitle() {
		return title;
	}
	public String getSize() {
		return size;
	}
	public String getPrice() {
		return price;
	}
	
	public Shop(int image, String title, String size, String price) {
		super();
		this.image = image;
		this.title = title;
		this.size = size;
		this.price = price;
	}

}
