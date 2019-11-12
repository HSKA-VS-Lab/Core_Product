package de.hska.iwi.vslab.Core_Product.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private double price;
    private int categoryId;
    private String details;

    protected Product() {
    }

    public Product(String name, double price, int category, String details) {
        this.name = name;
        this.price = price;
        this.categoryId = category;
        this.details = details;
    }

    @Override
    public String toString() {
        return String.format("Product[id=%d, name='%s', price=%e, categoryId=%f, details='%g']", id, name, price,
                categoryId, details);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getCategoryIdFromProduct() {
        return categoryId;
    }

    public String getDetails() {
        return details;
    }
}
