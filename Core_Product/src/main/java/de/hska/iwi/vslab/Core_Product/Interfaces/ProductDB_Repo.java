package de.hska.iwi.vslab.Core_Product.Interfaces;

import de.hska.iwi.vslab.Core_Product.Models.Product;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ProductDB_Repo extends CrudRepository<Product, Long> {

    List<Product> findByName(String name);

    List<Product> findAll(); // TODO: filtered by: String searchValue, double priceMinValue, double
                             // priceMaxValue

    Product findById(int id);
}
