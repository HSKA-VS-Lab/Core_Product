package de.hska.iwi.vslab.Core_Product.Services;

import de.hska.iwi.vslab.Core_Product.Interfaces.ProductRepository;
import de.hska.iwi.vslab.Core_Product.Models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {



    @Autowired
    ProductRepository productRepo;

    public List getAllProducts(){
        List products = new ArrayList<>();
        productRepo.findAll().forEach(products::add);
        return products;
    }

    public List getAllProducts(Object[] args){

        Optional<String> searchValue = (Optional<String>) args[0];
        Optional<String> priceMinValue = (Optional<String>) args[1];
        Optional<String> priceMaxValue = (Optional<String>) args[2];

        boolean checkSearchValue = checkArgument(0,args,searchValue);
        boolean checkPriceMinValue = checkArgument(1,args,priceMinValue);
        boolean checkPriceMaxValue = checkArgument(2,args,priceMaxValue);

        System.out.println("checkSearchValue: " + checkSearchValue);
        System.out.println("checkPriceMinValue: " + checkPriceMinValue);
        System.out.println("checkPriceMinValue: " + checkPriceMaxValue);


        List<Product> filteredProducts = new ArrayList<Product>();
        List<Product> allProducts = getAllProducts();
        for(int pos = 0; pos< allProducts.size(); pos++){
            Product prod = allProducts.get(pos);

            if (checkSearchValue){
                String sv = searchValue.get();
                System.out.println("searchValue: " + sv);
                if(prod.getName().toUpperCase().contains(sv.toUpperCase()))
                    filteredProducts.add(prod);
            }

            // Hier fehlen noch Kombinationen mit searchValue und min und max Preis einzeln bzw. zusammen

            if (checkPriceMinValue & checkPriceMaxValue) {
                double priceMin = Double.valueOf(priceMinValue.get());
                double priceMax = Double.valueOf(priceMinValue.get());
                System.out.println("priceMin: " + priceMin);
                System.out.println("priceMax: " + priceMax);
                if (prod.getPrice() >= priceMin & prod.getPrice() <= priceMax)
                    filteredProducts.add(prod);
            }
            else {
                if (checkPriceMinValue){
                double priceMin = Double.valueOf(priceMinValue.get());
                System.out.println("priceMin: " + priceMin);
                if (prod.getPrice() >= priceMin)
                    filteredProducts.add(prod);
                }
                if (checkPriceMaxValue) {
                    double priceMax = Double.valueOf(priceMaxValue.get());
                    System.out.println("priceMax: " + priceMax);
                    if (prod.getPrice() <= priceMax)
                        filteredProducts.add(prod);
                }
            }

        }

        if(!checkSearchValue & !checkPriceMinValue & !checkPriceMaxValue){
            System.out.println("no filter set!");
            filteredProducts = getAllProducts();
        }

        return filteredProducts;
    }

    private boolean checkArgument(int pos,Object[] args, Optional<String> opt) {
        boolean check = false;
        if (args[pos] != null)
            check = true;
        if (check) {
            if (!opt.isPresent()) {
                check = false;
                if (check)
                    if (opt.get() == "")
                        check = false;
            }


        }
        return check;
    }

    public Product getProduct(String name){
        return productRepo.findByName(name);
    }

    public Product getProduct(int id){
        return productRepo.findById(id);
    }

    public void addProduct(Product product){
        productRepo.save(product);
    }

    public void updateProduct(Product product){
        productRepo.save(product);
    }

    public long deleteAllProducts(){
        long deleted = 0;
        for(Product prod: productRepo.findAll())
            deleted += productRepo.deleteById(prod.getId());
        return deleted;
    }

    public long deleteProduct(String name){
        return productRepo.deleteByName(name);
    }

    public long deleteProduct(int id){
        return productRepo.deleteById(id);
    }
}
