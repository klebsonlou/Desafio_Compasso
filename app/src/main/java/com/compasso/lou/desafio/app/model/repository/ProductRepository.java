package com.compasso.lou.desafio.app.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.compasso.lou.desafio.app.model.entity.Product;

/**
 * 	
 * @author klebson.roberto.lou
 *
 * Repositorio utilizando JPA
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(value = "select p from Product p where ((p.name = :name or :name is null)   or (p.description = :description or :description is null)) and (p.price >= :minPrice or :minPrice is null) and (p.price <= :maxPrice or :maxPrice is null)")
	public List<Product> findBySearch(@Param("name") String name, @Param("description") String description,
			@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);

}