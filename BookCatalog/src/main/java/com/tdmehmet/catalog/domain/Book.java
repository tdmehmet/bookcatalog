package com.tdmehmet.catalog.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * Book entity which tells spring jpa to create Book table
 * with corresponding attributes
 * 
 * @author Mehmet Tahir Dede
 *
 */
@Entity
@Table(name = "BOOK")
public class Book implements Serializable {

	private static final long serialVersionUID = 814795021676134237L;

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue
	private long id;

	@Column(name = "TITLE", nullable = false)
	private String title;
	
	@Column(name = "AUTHOR", nullable = false)
	private String author;
	
	@Column(name = "LINK", nullable = false)
	private String link;
	
	@Column(name = "IMAGE", nullable = true)
	private String image;

	@Column(name = "PRICE", nullable = false)
	private double price;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
