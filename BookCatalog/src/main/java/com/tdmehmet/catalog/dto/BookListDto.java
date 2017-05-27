package com.tdmehmet.catalog.dto;

import java.io.Serializable;

/**
 * 
 * DTO used to get BookList by findByPage service
 * 
 * @author Mehmet Tahir Dede
 *
 */

public class BookListDto implements Serializable{

	private static final long serialVersionUID = 6144481909109310605L;

	private long id;
	private String link;
	private String title;
	private double price;
	private String detailLink;

	public BookListDto() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDetailLink() {
		return detailLink;
	}

	public void setDetailLink(String detailLink) {
		this.detailLink = detailLink;
	}

}
