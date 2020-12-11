package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.app.pojos.Product;
import com.app.service.IProductService;


@RestController //=> @Controller at class level + @ResponseBody annotation on return type of all
//request handling methods
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private IProductService service;
	
	public ProductController() {
		System.out.println("in constructor"+getClass().getName());
	}
	
	//RESTFUL end point or API end point or API provider
	@GetMapping
	public ResponseEntity<?> listAllProducts(){
		System.out.println("in list all Products");
		
		//invoke service layer's method : controller--->service Imple(p)--->JPA repository methods
		List<Product> products = service.getAllProducts();
		if(products.isEmpty())
		//empty product list : set sts code: HTTP 204 (no contents)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		//in case of non empty list : OK, send the list
		return new ResponseEntity<>(products,HttpStatus.OK);
		
	}
	
	//getProduct details by name : supplied by client using path var
	@GetMapping("/{productName}")
	public ResponseEntity<?> getProductDetails(@PathVariable String productName)
	{
		System.out.println("in get product details"+productName);
		//invoke service method
		Optional<Product> productdetails=service.getProductDetails(productName);
		
		//valid name : HTTP 200,marshalled product details
		if(productdetails.isPresent())
			return new ResponseEntity<>(productdetails.get(),HttpStatus.OK);
		//in case of invalid name: HTTP 404
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	//req handling method to create a new product : post
	@PostMapping
	public ResponseEntity<?> addProduct(@RequestBody Product p)
	{
		System.out.println("in add product"+p);
			try {
				Product savedProduct = service.addProductDetails(p);
				return new ResponseEntity<>(savedProduct,HttpStatus.OK);
			}catch (RuntimeException e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
				
	}
	//req  handling method to update existing product
	@PutMapping("/{productId}")
	public ResponseEntity<?> updateProductDetails(@PathVariable int productId,@RequestBody Product p)
	{
		System.out.println("in update"+p);
		try {
			Product updatedetails=service.updateProductDetails(productId,p);
			return new ResponseEntity<>(updatedetails,HttpStatus.OK);
		}catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping
	public ResponseEntity<?> deleteProduct(Product p){
		 List<Product> products = null	;	
		 if(products.isEmpty())
			service.DeleteProduct(p);
		 else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return null;
		
		}

	}
		
	

