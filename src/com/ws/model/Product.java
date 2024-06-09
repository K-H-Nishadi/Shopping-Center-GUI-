package com.ws.model;

import java.io.Serializable;
import java.util.Objects;

public abstract class Product implements Serializable {

    private String productID;
    private String productName;
    private int availableItems;
    private double price;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Product product)) return false;
        return (
                getAvailableItems() == product.getAvailableItems()
                        && Double.compare(getPrice(), product.getPrice()) == 0
                        && Objects.equals(getProductID(), product.getProductID())
                        && Objects.equals(getProductName(), product.getProductName())
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductID(), getProductName(), getAvailableItems(), getPrice());
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getAvailableItems() {
        return availableItems;
    }

    public void setAvailableItems(int availableItems) {
        this.availableItems = availableItems;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product() {
    }

    public Product(String productID,
                   String productName,
                   int availableItems,
                   double price) {
        this.productID = productID;
        this.productName = productName;
        this.availableItems = availableItems;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product" + "\n" +
                "productID: " + productID + '\n' +
                "productName: " + productName + '\n' +
                "availableItems: " + availableItems + '\n' +
                "price: " + price ;
    }
}
