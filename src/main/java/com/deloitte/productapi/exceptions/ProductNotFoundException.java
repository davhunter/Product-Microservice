package com.deloitte.productapi.exceptions;

import org.mule.module.apikit.exception.NotFoundException;

public class ProductNotFoundException extends NotFoundException {

	public ProductNotFoundException(String path) {
		super(path);
	}

	private static final long serialVersionUID = 1L;

}
