package com.tdmehmet.catalog.util;

import java.util.List;

/**
 * 
 * Catalog Utility Methods
 * 
 */

public class CatalogUtil {

	/**
	 * Converts List items to RestListMessage for paging client requires
	 * count of the items. If count value is null then sets the count 
	 * attribute of RestListMessage to number of items in the list  
	 * 
	 * @param list
	 * @param count
	 * @return RestListMessage<T>
	 */
	static public <T> RestListMessage<T> convertToRestListMessage(List<T> list, Integer count) {
        RestListMessage<T> message = new RestListMessage<T>();

        message.setContent(list);
        int countVal = count == null ? list.size() : count;
        message.setCount(countVal);

        return message;
    }
}
