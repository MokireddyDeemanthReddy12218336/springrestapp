package com.springrestapp.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.springrestapp.model.Product;
import com.springrestapp.service.ProductService;

@EnableWebMvc
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/")
	public ResponseEntity<List<Product>> getProducts(){
		//return ResponseEntity.ok(productService.getAll());
		List<Product> products = productService.getAll();
		if(products==null) {
			return new ResponseEntity<List<Product>>(products,HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
		}
	}
	
	@GetMapping("/{code}")
	public ResponseEntity<Product> getProducts(@PathVariable("code") String code){
		//return new ResponseEntity<Product>(productService.getByCode(code),HttpStatus.OK);
		Product product=productService.getByCode(code);
		if(product==null) {
			return new ResponseEntity<Product>(product,HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<Product>(product,HttpStatus.OK);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<Product> addProduct(@RequestBody Product p){
		return new ResponseEntity<Product>(productService.save(p),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{code}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("code") String code){
		//return new ResponseEntity<Product>(productService.getByCode(code),HttpStatus.OK);
		Product product=productService.deleteByCode(code);
		if(product==null) {
			return new ResponseEntity<Product>(product,HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<Product>(product,HttpStatus.OK);
		}
	}
	
	@PutMapping("/")
	public ResponseEntity<Product> updateProduct(@RequestBody Product p){
		//return new ResponseEntity<Product>(productService.getByCode(code),HttpStatus.OK);
		Product product=productService.updateByCode(p.getCode(),p.getPrice());
		if(product==null) {
			return new ResponseEntity<Product>(product,HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<Product>(product,HttpStatus.OK);
		}
	}
	
	
	
}
