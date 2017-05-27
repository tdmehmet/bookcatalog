package com.tdmehmet.catalog.service;

import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tdmehmet.catalog.domain.Book;
import com.tdmehmet.catalog.dto.BookDetailDto;
import com.tdmehmet.catalog.dto.BookDto;
import com.tdmehmet.catalog.dto.BookListDto;
import com.tdmehmet.catalog.repository.CatalogRepository;
import com.tdmehmet.catalog.util.CatalogUtil;
import com.tdmehmet.catalog.util.RestListMessage;
import com.tdmehmet.exception.TdmehmetCatalogException;

/**
 *
 * Catalog Services (findByPage, findById, save, update, delete)
 * 
 * @author Mehmet Tahir Dede
 *
 */

@Service
public class CatalogServiceImpl implements CatalogService{
	
	@Autowired
	private CatalogRepository catalogRepository;
	
	@Autowired
	EntityManager entityManager;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	Environment environment;
	
	@Override
	@Transactional
	public RestListMessage<BookListDto> findByPage(Integer offset, Integer count, String requestUrl) {
		//Here i did not use the catalog repository because pagination was requested
		//with offset and count parameters. So got hibernatesession from entity manager.
		//created a hibernate query depending on hibernate session and setted it' s
		//first result and max result values with corresponding offset and count variables
		//If they are empty it will return all of the list items.
		Session hibernateSession = entityManager.unwrap(Session.class);
		Query query = hibernateSession.createQuery("from Book order by id asc");
		if(offset != null) {
			query.setFirstResult(offset);
		}
		if(count != null) {
			query.setMaxResults(count);
		}
		List<Book> bookList = query.list();
		Type bookDtoListType = new TypeToken<List<BookListDto>>() {}.getType();
		//Adding item detail link 
		List<BookListDto> bookDtoList = modelMapper.map(bookList, bookDtoListType);
		for(BookListDto bookListDto : bookDtoList) {
			bookListDto.setDetailLink(requestUrl + "/" + bookListDto.getId());
		}
		//prepared a new class RestListMessage which has the number of items
		//and list of items as attributes
		return CatalogUtil.convertToRestListMessage(bookDtoList, count);
	}
	
   
	@Override
	@Transactional
	public BookDetailDto findById(long id) {
		//Calls simply crud repository function
		Book book = catalogRepository.findById(id);
		//Maps book to bookDetail to get requested files
		BookDetailDto bookDetailDto = modelMapper.map(book, BookDetailDto.class);
		return bookDetailDto;
	}
	
	@Override
	@Transactional
	public void save(BookDto bookDto) {
		if(bookDto.getId() != null) {
			//if id is entered it will update the item. To hinder this throws exception
			throw new TdmehmetCatalogException("You must not enter id for saving a new item!...");
		}
		//BookDto is mapped to book
		Book book = modelMapper.map(bookDto, Book.class);
		//Simply calls crud repository save method
		catalogRepository.save(book);
	}
	
	@Override
	@Transactional
	public void update(BookDto bookDto) {
		if(bookDto.getId() == null) {
			//To update an item it's unique key must be entered
			throw new TdmehmetCatalogException("Item must have a valid id!...");
		}
		if(catalogRepository.findById(bookDto.getId().longValue()) == null) {
			//To update an item it must be in the catalog
			throw new TdmehmetCatalogException("Item not found for update!...");
		}
		//BookDto is mapped to book
		Book book = modelMapper.map(bookDto, Book.class);
		//Simply calls crud repository save method		
		catalogRepository.save(book);
	}
	
	@Override
	@Transactional
	public void delete(long id) {
		if(catalogRepository.findById(id) == null) {
			//To update an item it must be in the catalog
			throw new TdmehmetCatalogException("Item not found for delete!...");
		}
		//Simply calls crud repository delete method
		catalogRepository.delete(id);
	}

}
