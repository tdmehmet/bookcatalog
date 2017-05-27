package com.tdmehmet.catalog.controller;

import com.tdmehmet.catalog.dto.BookDetailDto;
import com.tdmehmet.catalog.dto.BookDto;
import com.tdmehmet.catalog.dto.BookListDto;
import com.tdmehmet.catalog.service.CatalogService;
import com.tdmehmet.catalog.util.RestListMessage;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class CatalogController {

    @Autowired
    CatalogService catalogService;
    
    private final Logger logger = LoggerFactory.getLogger(CatalogController.class);
    /**
     * 
     * This Restful Web Service returns response entity with count
     * which is the number of items to provide paging as mentioned on Takasbank
     * Technical Constraints.
     * 
     * Both offset and count Request Parameters are optional. If they were not 
     * given by the client service will return all the list items. Because they
     * are optional client will need to use this parameters with Query String.
     * 
     * Offset is the starting index of the items arranged by id in ascending order
     * (On Takasbank Technical Constraints it lists the items arranged by id)
     * and count is the number of list items from starting index
     * 
     * It requires basic authentication to access as mentioned on Takasbank Technical Constraints.
     * As mentioned on Takasbank Technical Constraints versioning is added to the @RequestMapping value
     * as api/v1/items
     * 
     * Gets HttpServletRequest to get requested URL to create item detail link
     * as mentioned Takasbank Technical Constraints
     *  
     * @param offset
     * @param count
     * @param request
     * @return ResponseEntity<RestListMessage<BookListDto>>
     * 
     */
    @RequestMapping(value = "api/v1/items", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<RestListMessage<BookListDto>> findAll(@RequestParam(value="offset", required = false) Integer offset, 
    		@RequestParam(value="count", required = false) Integer count, HttpServletRequest request) {
    	logger.info("Request for listing item(s) with " + offset + " offset and " + count + " count");
        return new ResponseEntity<RestListMessage<BookListDto>>(catalogService.findByPage(offset, count, request.getRequestURL().toString()), HttpStatus.OK);
    }

    /**
     * 
     * Id is required that is why service gets id as @PathVariable and it is required
     * 
     * It requires basic authentication to access as mentioned Takasbank Technical Constraints.
     * As mentioned on Takasbank Technical Constraints versioning is added to the @RequestMapping value
     * as api/v1/items
     * 
     * @param id
     * @return ResponseEntity<BookDetailDto>
     * 
     */
    @RequestMapping(value = "api/v1/items/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<BookDetailDto> findById(@PathVariable long id) {
    	logger.info("Request for listing item with " + id + " id");
        return new ResponseEntity<BookDetailDto>(catalogService.findById(id), HttpStatus.OK);
    }

    /**
     * 
     * This Restful Web Service saves new item.
     * 
     * It returns Response Status as HttpStatus.Created. Service definition may be void but
     * with @ResponseStatus annotation if it successfully saves an item returns HttpStatus.Created which is 
     * integer 201.
     * 
     * Validation is done by javax.validation as mentioned on Takasbank Technical Constraints (Object Validation)
     * 
     * @param bookDto
     */
    @RequestMapping(value = "api/v1/items/save", method = RequestMethod.PUT, consumes = "application/json", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.CREATED)
    public void save(@Valid @RequestBody BookDto bookDto) {
    	logger.info("Request for saving item");
        catalogService.save(bookDto);
    }
    
    /**
     * 
     * This Restful Web Service updates an existing item with corresponding item dto.
     * 
     * It returns Response Status as HttpStatus.Created. Service definition may be void but
     * with @ResponseStatus annotation if it successfully saves an item returns HttpStatus.Created which is 
     * integer 201.
     * 
     * Validation is done by javax.validation as mentioned on Takasbank Technical Constraints(Object Validation) 
     * 
     * @param bookDto
     */
    @RequestMapping(value = "api/v1/items/update", method = RequestMethod.PUT, consumes = "application/json", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.CREATED)
    public void update(@Valid @RequestBody BookDto bookDto) {
    	logger.info("Request for updating item");
        catalogService.update(bookDto);
    }

    /**
     * 
     * This Restful Web Service deletes an existing item with corresponding id.
     * 
     * It returns Response Status as HttpStatus.OK. Service definition may be void but
     * with @ResponseStatus annotation if it successfully saves an item returns HttpStatus.OK which is 
     * integer 200.
     * 
     * Validation is done by javax.validation as mentioned on Takasbank Technical Constraints(Object Validation) 
     * 
     * @param id
     */
    @RequestMapping(value = "api/v1/items/delete/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable long id) {
    	logger.info("Request for deleting item");
        catalogService.delete(id);
    }

}
