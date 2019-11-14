package de.hska.iwi.vslab.Core_Product.Interfaces;

import de.hska.iwi.vslab.Core_Product.Models.Product;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ProductDB_Repo extends CrudRepository<Product, Long> {

    int add();

    boolean delete();

    boolean update();

    List<Product> findByName(String name);

    Product findById(int id);

    List<Product> findAll(); // TODO: optional searchParams

}
