package com.tdmehmet.catalog.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class BookDto {

	private Double id;

	@NotNull
    private String title;

	@NotNull
	private String author;

	@NotNull
	private String link;

	private String image;

	@DecimalMin("0.00000001")
	private double price;

	public Double getId() {
		return id;
	}

	public void setId(Double id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
