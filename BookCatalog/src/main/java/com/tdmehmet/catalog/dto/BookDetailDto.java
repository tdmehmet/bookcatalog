package com.tdmehmet.catalog.dto;

import java.io.Serializable;

/**
 * 
 * DTO used to get BookDetail by findById service
 * 
 * @author Mehmet Tahir Dede
 *
 */

public class BookDetailDto implements Serializable{

	private static final long serialVersionUID = 663045191892577291L;

	private long id;
	private String image;
	private String title;
	private String author;
	private double price;

	public BookDetailDto() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
