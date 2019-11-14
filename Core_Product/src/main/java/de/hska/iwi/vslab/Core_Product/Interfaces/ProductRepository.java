package de.hska.iwi.vslab.Core_Product.Interfaces;

import de.hska.iwi.vslab.Core_Product.Models.Product;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long>{

    Product findByName(String name);

    Product findById(int id);

    List<Product> findAll();

    long deleteById(int id);

    long deleteByName(String name);

}
