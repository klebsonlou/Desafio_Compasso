package com.compasso.lou.desafio.app;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.compasso.lou.desafio.app.controller.ProductDTO;
import com.compasso.lou.desafio.app.model.entity.Product;
import com.compasso.lou.desafio.app.model.repository.ProductRepository;
import com.compasso.lou.desafio.app.util.Constants;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application.properties")
public class AppApplicationTests {
	
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String DESCRIPTION_VAZIO = "";
	public static final String ID_REQUEST_UM = "/1";
	public static final String ID_REQUEST_DOIS = "/2";
	public static final String RETORNO_CONTAINS = "\"id\":\"1\"";

	@Value("${local.server.port}")
	private int port;
	private static String base;
	
	@Mock
	private ProductRepository repository;
	
	@Autowired
	private TestRestTemplate template;
	
	ResponseEntity<String> resposta;
	
	@BeforeClass
	public static void setupValidatorInstance() {
	}
	
	@Test
	public void test1PostFormatoInvalido() {
		base = "http://localhost:" + port + "/products";
		ProductDTO dto = getProductDTO();
		dto.setDescription(DESCRIPTION_VAZIO);
		resposta = conexaoHttp(dto, base, HttpMethod.POST);
		assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
		assertTrue(resposta.getBody().toString().contains(Constants.ERRO_FORMATO_INVALIDO));
	}
	
	@Test
	public void test1PostValido() {
		when(repository.save(new Product())).thenReturn(new Product());
		resposta = conexaoHttp(getProductDTO(), base, HttpMethod.POST);
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
	}
	
	@Test
	public void test1PutValido() {
		when(repository.save(new Product())).thenReturn(new Product());
		resposta = conexaoHttp(getProductDTO(), base + ID_REQUEST_UM, HttpMethod.PUT);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Test
	public void test1PutIdNaoEncontrado() {
		resposta = conexaoHttp(getProductDTO(), base + ID_REQUEST_DOIS, HttpMethod.PUT);
		assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
		assertTrue(resposta.getBody().toString().contains(Constants.ERRO_ID_NAO_LOCALIZADO));
	}
	
	@Test
	public void test1PutFormatoInvalido() {
		ProductDTO dto = getProductDTO();
		dto.setDescription(DESCRIPTION_VAZIO);
		resposta = conexaoHttp(dto, base + ID_REQUEST_UM, HttpMethod.PUT);
		assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
		assertTrue(resposta.getBody().toString().contains(Constants.ERRO_FORMATO_INVALIDO));
	}
	
	@Test
	public void test2GetValido() {
		resposta = conexaoHttp(new ProductDTO(), base, HttpMethod.GET);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertTrue(resposta.getBody().toString().contains(RETORNO_CONTAINS));
	}
	
	@Test
	public void test2GetPorId() {
		resposta = conexaoHttp(new ProductDTO(), base + ID_REQUEST_UM, HttpMethod.GET);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertTrue(resposta.getBody().toString().contains(RETORNO_CONTAINS));
	}
	
	@Test
	public void test2GetPorIdInvalido() {
		resposta = conexaoHttp(new ProductDTO(), base + ID_REQUEST_DOIS, HttpMethod.GET);
		assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
	}
	
	@Test
	public void test2GetBySearchMax() {
		resposta = conexaoHttp(new ProductDTO(), base +  Constants.SEARCH +"?max_price=10" , HttpMethod.GET);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Test
	public void test2GetBySearchName() {
		resposta = conexaoHttp(new ProductDTO(), base +  Constants.SEARCH +"?q=" + NAME , HttpMethod.GET);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Test
	public void test2GetBySearchDescription() {
		resposta = conexaoHttp(new ProductDTO(), base +  Constants.SEARCH +"?q=" + DESCRIPTION , HttpMethod.GET);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Test
	public void test3DeletePorIdInvalido() {
		resposta = conexaoHttp(new ProductDTO(), base + ID_REQUEST_DOIS, HttpMethod.DELETE);
		assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
	}
	
	@Test
	public void test3DeleteIdvalido() {
		resposta = conexaoHttp(new ProductDTO(), base + ID_REQUEST_UM, HttpMethod.DELETE);
		assertEquals(HttpStatus.OK,resposta.getStatusCode());
	}
	
	private ResponseEntity<String> conexaoHttp(ProductDTO dto, String base, HttpMethod httpMethod) {
		final HttpHeaders headers = new HttpHeaders();
		final HttpEntity<ProductDTO> httpEntity = new HttpEntity<ProductDTO>(dto, headers);
		return template.exchange(base, httpMethod, httpEntity, String.class);
	}
	
	private ProductDTO getProductDTO() {
		ProductDTO dto = new ProductDTO("1", NAME, DESCRIPTION, 1.0D);
		return dto;
	}
}
