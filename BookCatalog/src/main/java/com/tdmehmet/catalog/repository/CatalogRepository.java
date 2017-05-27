package com.tdmehmet.catalog.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tdmehmet.catalog.domain.Book;

/**
 * 
 * Simple Crud Repository for CRUD(Create, Read, Update, Delete) operations
 * on Book Entity
 * 
 * @author Mehmet Tahir Dede
 *
 */

public interface CatalogRepository extends CrudRepository<Book, Long>{

	@Query
	public Book findById(long id);

}
