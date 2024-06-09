package com.ws.uitl;

import com.ws.model.Product;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class ProductMapper implements Function<Object, List<Product>> {
    @Override
    public List<Product> apply(Object productList) {
        if (productList instanceof List<?>) {
            try {
                // Attempt to cast the Object to List<Product>
                return (List<Product>) productList;
            } catch (ClassCastException e) {
                System.err.println("Error in ProductMapper: " + e.getMessage());
                e.printStackTrace();
            }
        }
        // Return an empty list or handle the error in an appropriate way
        return Collections.emptyList();
    }
}
