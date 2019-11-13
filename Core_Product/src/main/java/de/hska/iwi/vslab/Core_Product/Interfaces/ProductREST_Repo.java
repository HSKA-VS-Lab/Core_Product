package de.hska.iwi.vslab.Core_Product.Interfaces;

import de.hska.iwi.vslab.Core_Product.Models.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "product", path = "product")
public interface ProductREST_Repo extends PagingAndSortingRepository<Product, Long> {

    List<Product> findByName(@Param("name") String name);

    List<Product> findAll();
}
