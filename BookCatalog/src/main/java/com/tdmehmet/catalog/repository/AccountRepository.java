package com.tdmehmet.catalog.repository;

import org.springframework.data.repository.CrudRepository;

import com.tdmehmet.catalog.domain.Account;

/**
 * 
 * Simple Crud Repository for CRUD(Create, Read, Update, Delete) operations
 * on Account Entity
 * 
 * @author Mehmet Tahir Dede
 *
 */

public interface AccountRepository extends CrudRepository<Account, Long>{

	public Account findByUsername(String username);

}
