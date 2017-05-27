package com.tdmehmet.catalog.controller.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tdmehmet.catalog.TdmehmetCatalogRestApplication;
import com.tdmehmet.catalog.controller.CatalogController;
import com.tdmehmet.catalog.domain.Book;
import com.tdmehmet.catalog.dto.BookDto;
import com.tdmehmet.catalog.dto.BookListDto;
import com.tdmehmet.catalog.repository.CatalogRepository;
import com.tdmehmet.catalog.service.CatalogService;
import com.tdmehmet.catalog.util.CatalogUtil;
import com.tdmehmet.catalog.util.RestListMessage;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=TdmehmetCatalogRestApplication.class)
@WebAppConfiguration
public class TdmehmetCatalogControllerTest {

	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private ModelMapper modelMapper;

	@MockBean
    private CatalogRepository catalogRepository;
	
	@MockBean
    private CatalogService catalogService;
	
    @InjectMocks
    private CatalogController catalogController;

    private String basicDigestHeaderValue;
	private MockMvc mockMvc;
	private String bookJson;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(context) 
				.build();
		this.basicDigestHeaderValue = "Basic " + Base64.getEncoder().encodeToString("usertest:secret".getBytes());
    	List<BookListDto> bookListDtoList = new ArrayList<BookListDto>();
    	
    	Book book = new Book();
    	book.setId(7L);
    	book.setLink("/api/v1/items/7");
    	book.setTitle("Seven is my lucky number");
    	book.setPrice(7.77);
    	book.setAuthor("Rheinzand - The Buddha Nature");
    	book.setImage("http://assignment.golgek.mobi/static/img7.png");
    	
    	BookListDto bookListDto = modelMapper.map(book, BookListDto.class);
    	BookDto bookDto = modelMapper.map(book, BookDto.class);
    	bookListDto.setDetailLink("http://localhost:8080/api/v1/items/7");
    	
    	bookListDtoList.add(bookListDto);
    	RestListMessage<BookListDto> restListMessage = CatalogUtil.convertToRestListMessage(bookListDtoList, 1);
    	
    	Gson gson = new GsonBuilder().create();
    	this.bookJson = gson.toJson(bookDto);
    	
        when(catalogService.findByPage(null, null, "http://localhost:8080/api/v1/items")).thenReturn(restListMessage);
        when(catalogRepository.findById(7L)).thenReturn(book);
        when(catalogRepository.save(book)).thenReturn(book);
	}
	
    @Test
    public void testFindByPage() throws Exception{
        mockMvc.perform(get("/api/v1/items")
                .header("Authorization", basicDigestHeaderValue)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
        		.andExpect(status().is2xxSuccessful());

    }


    @Test
    public void testFindById() throws Exception{
    	mockMvc.perform(get("/api/v1/items/7")
                .header("Authorization", basicDigestHeaderValue)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
        		.andExpect(status().is2xxSuccessful());
    }
    
    @Test
    public void testSave() throws Exception{
    	mockMvc.perform(put("/api/v1/items/save")
                .header("Authorization", basicDigestHeaderValue)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(this.bookJson)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
        		.andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testUpdate() throws Exception{
    	mockMvc.perform(put("/api/v1/items/update")
                .header("Authorization", basicDigestHeaderValue)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(this.bookJson)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
        		.andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testDelete() throws Exception{
    	mockMvc.perform(delete("/api/v1/items/delete/7")
                .header("Authorization", basicDigestHeaderValue)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
        		.andExpect(status().is2xxSuccessful());
    }

}
