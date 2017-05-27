package com.tdmehmet.catalog.util;

import java.util.List;

/**
 * This class is used to return ResponseEntity with requested parameters and Lists
 * T is any kind of class that List values can be instantiated
 * 
 * @author Mehmet Tahir Dede
 *
 * @param <T>
 */

public class RestListMessage<T> {
	Integer count;
	List<T> content;
	
	public RestListMessage(){
		super();
	}

	public RestListMessage(int count, List<T> content) {
		super();
		this.count = count;
		this.content = content;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

}
