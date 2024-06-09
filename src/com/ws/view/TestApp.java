package com.ws.view;

import com.ws.controller.WestminsterShoppingManager;

public class TestApp {
    public static void main(String[] args) {
        WestminsterShoppingManager manager = new WestminsterShoppingManager();
        manager.init();
        new MainFrame();
    }
}
