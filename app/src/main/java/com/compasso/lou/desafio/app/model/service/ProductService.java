package com.compasso.lou.desafio.app.model.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.compasso.lou.desafio.app.controller.ProductDTO;

/**
 * 
 * @author klebson.roberto.lou
 *
 *  Service da Api de Product
 */
@Service
public interface ProductService {
	ResponseEntity<?> cadastrarProducts(ProductDTO productDTO);
	ResponseEntity<?> atualizarProducts(long id, ProductDTO productDTO);
	ResponseEntity<ProductDTO> listarProductsById(long id);
	ResponseEntity<List<ProductDTO>> listarProducts();
	ResponseEntity<List<ProductDTO>> listarProductsSearch(Double minPrice, Double maxPrice, String q);
	ResponseEntity<Void> deletarProducts(long id);
}