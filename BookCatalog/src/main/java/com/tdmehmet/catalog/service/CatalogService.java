package com.tdmehmet.catalog.service;

import com.tdmehmet.catalog.dto.BookDetailDto;
import com.tdmehmet.catalog.dto.BookDto;
import com.tdmehmet.catalog.dto.BookListDto;
import com.tdmehmet.catalog.util.RestListMessage;

/**
 * 
 * Interface for CatalogServiceImpl
 * 
 * @author Mehmet Tahir Dede
 *
 */

public interface CatalogService {
	/**
     * 
     * This Service returns RestListMessage with count
     * which is the number of items to provide paging as mentioned on Takasbank Technical Constraints.
     * 
     * Gets offset and count parameters and according to parameters returns list of items.
     *  
     * Offset is the starting index of the items arranged by id in ascending order
     * and count is the number of list items from starting index
     * 
     * Pagination was requested with offset and count parameters that is why spring data
     * pagination was not used. Because Spring Pageable definition does not have any implementation
     * of offset and count. It only has pageNum and pageSize as parameters.
     * 
     * @param offset
     * @param count
     * @return RestListMessage<BookListDto>
     * 
     */
	public RestListMessage<BookListDto> findByPage(Integer offset, Integer count, String requestUrl);
	 /**
     * 
     * This Service returns item details by given id
     * 
     * Id is required
     * 
     * @param id
     * @return BookDetailDto
     * 
     */
	public BookDetailDto findById(long id);

	/**
	 * 
	 * Saves item with corresponding values. Id must be null
	 * 
	 * @param saveBookDto
	 * 
	 */
	public void save(BookDto saveBookDto);

	/**
	 * 
	 * Updates item with corresponding values. Id is necessary
	 * 
	 * @param bookDto
	 * 
	 */
	public void update(BookDto bookDto);

	/**
	 * 
	 * Deletes item with corresponding id
	 * 
	 * @param id
	 * 
	 */
	public void delete(long id);

}
