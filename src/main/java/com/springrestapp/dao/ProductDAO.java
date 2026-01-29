package com.springrestapp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springrestapp.model.Product;

@Repository("productDAO")
public class ProductDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Product> findAll(){
		String sql="select * from products";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Product>(Product.class));
	}
	
	public Product save(Product p){
		String sql="insert into products(code,name,price) values(?,?,?)";
		Object params[] = {p.getCode(),p.getName(),p.getPrice()};
		int n = jdbcTemplate.update(sql,params);
		if(n>0) {
			return p;
		}
		else
			return null;
	}
	
	public Product findByCode(String code) {
		String sql="select * from products where code=?";
		Object param[] = {code};
		
		return jdbcTemplate.queryForObject
				(sql,new BeanPropertyRowMapper<Product>(Product.class),param);
	}
	
	public Product delete(String code){
		String sql="delete from products where code=?";
		Product p =findByCode(code);
		int n = jdbcTemplate.update(sql,code);
		if(n>0) {
			return p;
		}
		else
			return null;
	}
	
	public Product update(String code,int price){
		String sql="update  products set price=? where code=?";
		Object params[] = {price,code};
		int n = jdbcTemplate.update(sql,params);
		if(n>0) {
			return findByCode(code);
		}
		else
			return null;
	}
	
}
