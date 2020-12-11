package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.pojos.Product;

public interface IProductService {
	//list all products
	List<Product> getAllProducts();
	//get product details by name
	Optional<Product> getProductDetails(String pName);
	//add new product details
	Product addProductDetails(Product transientPOJO);
	//update existing product details
	Product addProductDetails(int productId,Product detachedPOJO);
	Product updateProductDetails(int productId, Product p);
	void DeleteProduct(Product p);	
		
	

}
