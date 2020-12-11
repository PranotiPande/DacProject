package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.customException.ProductNotException;
import com.app.dao.IProductDao;
import com.app.pojos.Product;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {
	// dependency
	@Autowired
	private IProductDao dao;
	private Optional<Product> product;

	@Override
	public List<Product> getAllProducts() {
		// its work like select * from Product;
		System.out.println("dao imple class" + getClass().getName());
		// its work like select * from Product;
		return dao.findAll();
	}

	@Override
	public Optional<Product> getProductDetails(String pName) {

		return dao.findByName(pName);
	}

	@Override
	public Product addProductDetails(Product transientPOJO) {
		return dao.save(transientPOJO);
	}

	@Override
	public Product addProductDetails(int productId, Product p1) {
		// check product exists
		Optional<Product> p = dao.findById(productId);
		if (p.isPresent()) {
			// p.get(): PERSISTANCE
			// p1: detached POJO : contains the updates sent by client
			// change the state of persistent POJO
			Product product = p.get();
			product.setPrice(p1.getPrice());
			product.setExpDate((p1.getExpDate()));
			return product;
		}
		// in case of no product found : return custom exception
		throw new ProductNotException("Invalid Product Id");
	}

	@Override
	public Product updateProductDetails(int productId, Product p1) {
		// check if product exists
		Optional<Product> p = dao.findById(productId);
		if (p.isPresent()) {
			// p.get(): PERSISTANCE
			// p1: detached POJO : contains the updates sent by client
			// change the state of persistent POJO
			Product product = p.get();
			product.setPrice(p1.getPrice());
			product.setExpDate((p1.getExpDate()));
			return product;
		}
		// in case of no product found : return custom exception
		throw new ProductNotException("Invalid Product Id");
	}

	@Override
	public void DeleteProduct(Product p) {

		dao.delete(p);
	}

}
