package de.hska.iwi.vslab.Core_Product.Controllers;

import de.hska.iwi.vslab.Core_Product.Models.Product;
import de.hska.iwi.vslab.Core_Product.Services.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;


    @GetMapping("/product")
    public List<Product> getAllProduct() {
        log.info("getAllProducts() was called");
        return productService.getAllProducts();
    }

    @RequestMapping(value = {"/product/find"},
                             method = RequestMethod.GET)
    public List<Product> getProducts(
            @RequestParam(value = "searchValue", required = false) Optional<String> searchValue,
            @RequestParam(value = "priceMinValue", required = false) Optional<String> priceMinValue,
            @RequestParam(value = "priceMaxValue", required = false) Optional<String> priceMaxValue) {

    log.info("getProducts() was called");
        Object[] args = new Object[3];
        if (searchValue.isPresent())
            args[0] = searchValue;
        if (priceMinValue.isPresent())
            args[1] = priceMinValue;
        if (priceMaxValue.isPresent())
            args[2] = priceMaxValue;

        return productService.getAllProducts(args);

    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable int id) {
        log.info("getProduct("+id+") was called");
        return productService.getProduct(id);
    }

    @PostMapping("/product")
    public void addProduct(@RequestBody Product product) {
        log.info("addProduct("+product.toString()+") was called");
        productService.addProduct(product);
    }

    @PutMapping("/product")
    public void updateProduct(@RequestBody Product product) {
        log.info("updateProduct("+product.toString()+") was called");
        productService.updateProduct(product);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable int id){
        log.info("deleteProduct("+id+") was called");
        productService.deleteProduct(id);
    }

    @DeleteMapping("/product")
    public void deleteProduct(){
        log.info("deleteProduct() was called");
        productService.deleteAllProducts();
    }
}
