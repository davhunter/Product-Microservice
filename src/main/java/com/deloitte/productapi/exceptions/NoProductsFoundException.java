package com.deloitte.productapi.exceptions;

import org.mule.module.apikit.exception.NotFoundException;

public class NoProductsFoundException extends NotFoundException {

	private static final long serialVersionUID = 1L;

	public NoProductsFoundException(String path) {
		super(path);
	}

}
