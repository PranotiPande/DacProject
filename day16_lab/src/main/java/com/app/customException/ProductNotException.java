package com.app.customException;

public class ProductNotException  extends RuntimeException{

	

	public ProductNotException(String mesg) {
		super(mesg);
	}
}
