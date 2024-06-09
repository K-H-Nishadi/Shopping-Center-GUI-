package com.ws.model;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


public interface ShoppingManager {
    String SECRET_KEY = Base64.getEncoder().encodeToString(new SecureRandom().generateSeed(16));

    //Add a new product
    Product addNewProduct(Product product);

    //Delete a product
    Product deleteProduct(String productID);

    //Print the list of the products
    void printProducts();

    //Save in a file
    void saveToFile();

    void openGUI();

    //Load from file
    void loadFromFile();

    // For Testing need to remove
    void init();
}
