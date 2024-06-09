package com.ws.model;

import com.ws.exception.NullProductException;
import com.ws.exception.ProductNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoppingCart {

    private List<Product> products;


    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    //TODO: This constructor should be no arg constructor
    public ShoppingCart() {
        this.products = new ArrayList<>();
        products.add(new Clothing("C001", "T-Shirt", 3, 19.99, "M", "Blue"));
        products.add(new Clothing("C002", "Jeans", 4, 49.99, "L", "Black"));
        products.add(new Electronics("E001", "Laptop", 10, 899.99, "Dell", 2));
        products.add(new Electronics("E002", "Smartphone", 3, 699.99, "Samsung", 1));
    }


    public ShoppingCart(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        Objects.requireNonNull(product,
                () -> {
                    throw new NullProductException("Product cannot be null.");
                });
        if (product.getAvailableItems() == 0) {
            throw new RuntimeException();
        }
        products.add(product);
        product.setAvailableItems(product.getAvailableItems() - 1);
    }

    public Product removeProduct(String productId) {
        Product product = this.products.stream()
                .filter(
                        p -> p.getProductID().equals(productId)
                )
                .findFirst()
                .orElseThrow(
                        () -> new ProductNotFoundException("THere is no product with this ID.")
                );
        products.remove(product);
        return product;
    }

    public Double calculateTotalPrice() {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public void print() {
        System.out.println("Products in the cart");
        products.forEach(System.out::println);
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "products=" + products +
                '}';
    }
}
