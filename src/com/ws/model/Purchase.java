package com.ws.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Purchase {

    private String purchaseID;
    private String userID;
    Map<String, Map<Date, ShoppingCart>> purchases;

    public String getPurchaseID() {
        return purchaseID;
    }

    public void setPurchaseID(String purchaseID) {
        this.purchaseID = purchaseID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Map<String, Map<Date, ShoppingCart>> getPurchases() {
        return purchases;
    }

    public void setPurchases(Map<String, Map<Date, ShoppingCart>> purchases) {
        this.purchases = purchases;
    }

    public Purchase() {
        this.purchaseID = UUID.randomUUID().toString();
        this.purchases = new HashMap<>();
    }

    public Purchase(String purchaseID, String userID, Map<String, Map<Date, ShoppingCart>> purchases) {
        this.purchaseID = purchaseID;
        this.userID = userID;
        this.purchases = purchases;
    }
}
