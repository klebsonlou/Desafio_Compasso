package com.compasso.lou.desafio.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.compasso.lou.desafio.app.model.service.ProductService;
import com.compasso.lou.desafio.app.util.Constants;

/**
 * 
 * @author klebson.roberto.lou
 * 
 *         controlador da api
 */
@RestController
@RequestMapping("products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@SuppressWarnings("unchecked")
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<ProductDTO> cadastrarProducts(@RequestBody ProductDTO productDTO) {
		return (ResponseEntity<ProductDTO>) productService.cadastrarProducts(productDTO);
	}

	@SuppressWarnings("unchecked")
	@PutMapping(path = Constants.ID_REQUEST,  consumes = "application/json", produces = "application/json")
	public ResponseEntity<ProductDTO> editarProducts(@PathVariable("id") long id, @RequestBody ProductDTO productDTO) {
		return (ResponseEntity<ProductDTO>) productService.atualizarProducts(id, productDTO);
	}

	@GetMapping(path = Constants.ID_REQUEST, produces = "application/json")
	public ResponseEntity<ProductDTO> listarProductsPorId(@PathVariable("id") long id) {
		return productService.listarProductsById(id);
	}

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<ProductDTO>> listarProducts() {
		return productService.listarProducts();
	}

	@GetMapping(path = Constants.SEARCH, produces = "application/json")
	public ResponseEntity<List<ProductDTO>> listarProductsSearch(
			@RequestParam(value = "min_price", required = false) Double minPrice,
			@RequestParam(value = "max_price", required = false) Double maxPrice,
			@RequestParam(value = "q", required = false) String q) {
		return productService.listarProductsSearch(minPrice, maxPrice, q);
	}

	@DeleteMapping(path = Constants.ID_REQUEST)
	public ResponseEntity<Void> deletarProductsPorId(@PathVariable("id") long id) {
		return productService.deletarProducts(id);
	}
}