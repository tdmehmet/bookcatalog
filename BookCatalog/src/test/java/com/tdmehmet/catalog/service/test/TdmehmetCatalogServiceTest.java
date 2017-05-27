package com.tdmehmet.catalog.service.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.tdmehmet.catalog.TdmehmetCatalogRestApplication;
import com.tdmehmet.catalog.domain.Book;
import com.tdmehmet.catalog.dto.BookDto;
import com.tdmehmet.catalog.repository.CatalogRepository;
import com.tdmehmet.catalog.service.CatalogServiceImpl;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=TdmehmetCatalogRestApplication.class)
public class TdmehmetCatalogServiceTest {
    

    @Autowired
    private CatalogServiceImpl catalogService;

    @MockBean
    private CatalogRepository catalogRepository;

	@Autowired
	private ModelMapper modelMapper;

    @Before
    public void setup() {
    	List<Book> bookList = new ArrayList<Book>();
    	Book book = new Book();
    	book.setId(7L);
    	book.setLink("/api/v1/items/7");
    	book.setTitle("Seven is my lucky number");
    	book.setPrice(7.77);
    	book.setAuthor("Rheinzand - The Buddha Nature");
    	book.setImage("http://assignment.golgek.mobi/static/img7.png");

    	bookList.add(book);

        when(catalogRepository.findAll()).thenReturn(bookList);
        when(catalogRepository.findById(7L)).thenReturn(book);
        when(catalogRepository.save(book)).thenReturn(book);
    }

    @Test
    public void testFindByPage() throws Exception {
    	assertEquals(7L, catalogService.findByPage(null, null, "http://localhost:8080/api/v1/items/7").getContent().get(0).getId());
    }

    @Test
    public void testFindById() throws Exception {
    	assertEquals(7L, catalogService.findById(7L).getId());
    }
    
    @Test
    public void testUpdate() throws Exception {
    	Book book = new Book();
    	book.setId(7L);
    	book.setLink("/api/v1/items/7");
    	book.setTitle("Seven is my lucky number");
    	book.setPrice(7.77);
    	book.setAuthor("Rheinzand - The Buddha Nature");
    	book.setImage("http://assignment.golgek.mobi/static/img7.png");
    	
    	BookDto bookDto = modelMapper.map(book, BookDto.class);
    	
    	catalogService.update(bookDto);
    }
}
