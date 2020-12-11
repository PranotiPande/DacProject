package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Product;

public interface IProductDao  extends JpaRepository<Product, Integer>{
//search by product name
	Optional<Product> findByName(String pName);
}
