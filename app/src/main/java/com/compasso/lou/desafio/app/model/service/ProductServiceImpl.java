package com.compasso.lou.desafio.app.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.compasso.lou.desafio.app.controller.ProductDTO;
import com.compasso.lou.desafio.app.exceptions.ResourceBabRequestException;
import com.compasso.lou.desafio.app.exceptions.ResourceNotFoundtException;
import com.compasso.lou.desafio.app.exceptions.ResponseException;
import com.compasso.lou.desafio.app.model.entity.Product;
import com.compasso.lou.desafio.app.model.repository.ProductRepository;
import com.compasso.lou.desafio.app.util.Constants;

/**
 * 
 * @author klebson.roberto.lou
 *
 *         Implementacao do service de Product
 */
@Service
class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;

	ModelMapper modelMapper = new ModelMapper();

	@Override
	public ResponseEntity<?> cadastrarProducts(ProductDTO productDTO) {
		if (StringUtils.isEmpty(productDTO.getName()) || StringUtils.isEmpty(productDTO.getDescription())
				|| ObjectUtils.isEmpty(productDTO.getPrice()) || 0 >= productDTO.getPrice()) {
			return new ResponseException().resourceBabRequestException(
					new ResourceBabRequestException(String.format(Constants.ERRO_FORMATO_INVALIDO)));
		} else {
			Product product = modelMapper.map(productDTO, Product.class);
			product = repository.save(product);
			return new ResponseEntity<>(modelMapper.map(product, ProductDTO.class), HttpStatus.CREATED);
		}
	}

	@Override
	public ResponseEntity<?> atualizarProducts(long id, ProductDTO productDTO) {
		Optional<Product> produtcs = repository.findById(id);
		if (produtcs.isPresent()) {
			if (StringUtils.isEmpty(productDTO.getName()) || StringUtils.isEmpty(productDTO.getDescription())
					|| ObjectUtils.isEmpty(productDTO.getPrice()) || 0 >= productDTO.getPrice()) {
				return new ResponseException().resourceBabRequestException(
						new ResourceBabRequestException(String.format(Constants.ERRO_FORMATO_INVALIDO)));
			} else {
				Product produto = produtcs.get();
				produto.setDescription(productDTO.getDescription());
				produto.setName(productDTO.getName());
				produto.setPrice(productDTO.getPrice());
				produto = repository.save(produto);
				return new ResponseEntity<>(modelMapper.map(produto, ProductDTO.class), HttpStatus.OK);
			}
		} else {
			return new ResponseException().resourceNotFoundtException(
					new ResourceNotFoundtException(String.format(Constants.ERRO_ID_NAO_LOCALIZADO)));
		}
	}

	@Override
	public ResponseEntity<ProductDTO> listarProductsById(long id) {
		Optional<Product> produtcs = repository.findById(id);
		if (produtcs.isPresent()) {
			ProductDTO productDTO = modelMapper.map(produtcs.get(), ProductDTO.class);
			return new ResponseEntity<>(productDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<List<ProductDTO>> listarProducts() {
		List<Product> listaProdutcs = repository.findAll();
		List<ProductDTO> listaProdutcsDTO = new ArrayList<>();
		for (Product listad : listaProdutcs) {
			ProductDTO productDTO = modelMapper.map(listad, ProductDTO.class);
			listaProdutcsDTO.add(productDTO);
		}
		return new ResponseEntity<>(listaProdutcsDTO, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<ProductDTO>> listarProductsSearch(Double minPrice, Double maxPrice, String q) {
		List<Product> listaProdutcs = repository.findBySearch(q, q, minPrice, maxPrice);
		List<ProductDTO> listaProdutcsDTO = new ArrayList<>();
		for (Product listad : listaProdutcs) {
			ProductDTO productDTO = modelMapper.map(listad, ProductDTO.class);
			listaProdutcsDTO.add(productDTO);
		}
		return new ResponseEntity<>(listaProdutcsDTO, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> deletarProducts(long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}