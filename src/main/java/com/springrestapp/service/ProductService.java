package com.springrestapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrestapp.dao.ProductDAO;
import com.springrestapp.model.Product;
@Service("productService")
public class ProductService {
	
	@Autowired
	private ProductDAO productDAO;

	public ProductDAO getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
	public List<Product> getAll(){
		return productDAO.findAll();
	}
	
	
		public Product save(Product p) {
			return productDAO.save(p);
		}
		
	
		public Product getByCode(String code) {
			return productDAO.findByCode(code);
		}
		
	
		public Product deleteByCode(String code) {
			return productDAO.delete(code);
		}
		
		public Product updateByCode(String code,int price) {
			return productDAO.update(code, price);
		}
}
