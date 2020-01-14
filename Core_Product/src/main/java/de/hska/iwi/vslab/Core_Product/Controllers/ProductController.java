package de.hska.iwi.vslab.Core_Product.Controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    @HystrixCommand(fallbackMethod = "fallbackGetProducts")
    public Product[] getAllProduct() {
        log.info("getAllProducts() was called");
        return productService.getAllProducts();
    }

    public Product[] fallbackGetProducts() {
        Product product1 = new Product("productFallback1",1.0, 1, "dies das");
        Product product2 = new Product("productFallback2",2.0, 1, "dies das");
        Product[] productA = new Product[2];
        productA[0] = product1;
        productA[1] = product2;
        return productA;
    }

    @RequestMapping(value = { "/product/find" }, method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "fallbackGetProduct")
    public Product[] getProducts(@RequestParam(value = "searchValue", required = false) Optional<String> searchValue,
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
    @HystrixCommand(fallbackMethod = "fallbackGetProduct")
    public Product getProduct(@PathVariable int id) {
        log.info("getProduct(" + id + ") was called");
        return productService.getProduct(id);
    }

    public Product fallbackGetProduct() {
        Product product = new Product("productFallback",1.0, 1, "dies das");
        return product;
    }

    @PostMapping(path = "/product", consumes = "application/json")
    @HystrixCommand(fallbackMethod = "defaultFallback")
    public void addProduct(@RequestBody Product product) {
        log.info("addProduct(" + product.toString() + ") was called");
        productService.addProduct(product);
    }

    @PutMapping(path = "/product/{id}", consumes = "application/json")
    @HystrixCommand(fallbackMethod = "defaultFallbackWithId")
    public void updateProduct(@PathVariable int id, @RequestBody(required = true) Product product) {
        log.info("updateProduct(" + product.toString() + ") was called");
        productService.updateProduct(product);
    }

    @DeleteMapping("/product/{id}")
    @HystrixCommand(fallbackMethod = "defaultFallbackWithId")
    public void deleteProduct(@PathVariable int id) {
        log.info("deleteProduct(" + id + ") was called");
        productService.deleteProduct(id);
    }

    @DeleteMapping("/product")
    @HystrixCommand(fallbackMethod = "defaultFallback")
    public void deleteProduct() {
        log.info("deleteProduct() was called");
        productService.deleteAllProducts();
    }

    public void defaultFallback(Throwable throwable) {
        System.out.printf("DefaultFallback, exception=%s%n", throwable);
    }

    public void defaultFallbackWithId(int id, Throwable throwable) {
        System.out.printf("DefaultFallbackWithId, id=%s, exception=%s%n", id, throwable);
    }
}
