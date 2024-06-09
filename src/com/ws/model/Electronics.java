package com.ws.model;

public class Electronics extends Product {

    private String brand;
    private int warrantyPeriod;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public Electronics() {
    }

    public Electronics(String productID,
                       String productName,
                       int availableItems,
                       double price,
                       String brand,
                       int warrantyPeriod) {
        super(productID, productName, availableItems, price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Type: Electronics" + "\n" +
                "Brand: " + brand + '\n' +
                "Warranty period: " + warrantyPeriod + "\n";
    }
}
