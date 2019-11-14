package de.hska.iwi.vslab.Core_Product.Controllers;

import de.hska.iwi.vslab.Core_Product.Models.Product;
import de.hska.iwi.vslab.Core_Product.Services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/product")
    public List getAllProduct() {
        return productService.getAllProducts();
    }
/*
    @GetMapping(path = {"/product/searchValue={searchValue}&priceMinValue={priceMinValue}&priceMaxValue={priceMaxValue}",
                        "/product/searchValue={searchValue}",
                        "/product/searchValue={searchValue}&priceMinValue={priceMinValue}",
                        "/product/searchValue={searchValue}&priceMaxValue={priceMaxValue}",
                        "/product/priceMinValue={priceMinValue}&priceMaxValue={priceMaxValue}",
                        "/product/priceMinValue={priceMinValue}",
                        "/product/priceMaxValue={priceMaxValue}"})*/
    /*@GetMapping(path = {"/product/searchValue={searchValue}&priceMinValue={priceMinValue}&priceMaxValue={priceMaxValue}"})
    public List getAllProduct(@RequestParam(value = "searchValue", required = false) Optional<String> searchValue,
                              @RequestParam(value = "priceMinValue", required = false) Optional<Long> priceMinValue,
                              @RequestParam(value = "priceMaxValue", required = false) Optional<Long> priceMaxValue) {

        Object[] args = new Object[3];
        args[0] = searchValue;
        args[1] = priceMinValue;
        args[2] = priceMaxValue;

        return productService.getAllProducts(args);
    }*/

    /*    @RequestMapping(value = {"/product/{searchValue=searchValue&priceMinValue=priceMinValue&priceMaxValue=priceMaxValue}",
                             "/product/{searchValue=searchValue}",
                             "/product/{searchValue=searchValue&priceMinValue=priceMinValue}",
                             "/product/{searchValue=searchValue&priceMaxValue=priceMaxValue}",
                             "/product/{priceMinValue=priceMinValue&priceMaxValue=priceMaxValue}",
                             "/product/{priceMinValue=priceMinValue}",
                             "/product/{priceMaxValue=priceMaxValue}"},*/

    @RequestMapping(value = {"/product/find"},
                             method = RequestMethod.GET)
    public List<Product> getProducts(
            @RequestParam(value = "searchValue", required = false) Optional<String> searchValue,
            @RequestParam(value = "priceMinValue", required = false) Optional<String> priceMinValue,
            @RequestParam(value = "priceMaxValue", required = false) Optional<String> priceMaxValue) {


        /*System.out.println(params);

        String searchText = null;
        String minValue = null;
        String maxValue = null;
        searchText = params.substring(params.indexOf("searchValue=") + "searchValue=".length(), params.indexOf("+"));
        params = params.substring("searchValue=".length() + searchText.length() + 1);
        minValue = params.substring(params.indexOf("priceMinValue=") + "priceMinValue=".length(), params.indexOf("+"));
        params = params.substring("priceMinValue=".length() + minValue.length() + 1);
        minValue = params.substring(params.indexOf("priceMaxValue=") + "priceMaxValue=".length());
*/

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
        return productService.getProduct(id);
    }

    @PostMapping("/product")
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @PutMapping("/product")
    public void updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
    }

    @DeleteMapping("/product/{id}")
    public long deleteProduct(@PathVariable int id){
        return productService.deleteProduct(id);
    }

    @DeleteMapping("/product")
    public long deleteProduct(){
        return productService.deleteAllProducts();
    }
}
