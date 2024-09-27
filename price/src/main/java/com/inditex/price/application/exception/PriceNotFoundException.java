package com.inditex.price.application.exception;
/**
 * Custom exception to be thrown when a price is not found for the given parameters.
 */
public class PriceNotFoundException extends RuntimeException {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructs a new PriceNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public PriceNotFoundException(String message) {
        super(message);
    }
}
