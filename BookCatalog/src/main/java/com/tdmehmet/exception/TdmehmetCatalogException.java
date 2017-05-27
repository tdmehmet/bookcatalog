package com.tdmehmet.exception;

/**
 * For HTTP 404 errros
 */
public class TdmehmetCatalogException extends RuntimeException {

	private static final long serialVersionUID = 7588523574568603270L;

	public TdmehmetCatalogException() {
        super();
    }

    public TdmehmetCatalogException(String message, Throwable cause) {
        super(message, cause);
    }

    public TdmehmetCatalogException(String message) {
        super(message);
    }

    public TdmehmetCatalogException(Throwable cause) {
        super(cause);
    }

}
