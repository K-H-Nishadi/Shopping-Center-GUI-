package com.ws.controller;

import com.sun.jdi.ClassType;
import com.ws.exception.NullProductException;
import com.ws.exception.ProductAlreadyExistsException;
import com.ws.exception.ProductRemovalException;
import com.ws.model.Clothing;
import com.ws.model.Electronics;
import com.ws.model.Product;
import com.ws.model.ShoppingManager;
import com.ws.uitl.CommonUtils;
import com.ws.uitl.ProductMapper;
import com.ws.uitl.Validator;
import com.ws.view.MainFrame;

import javax.swing.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;


public class WestminsterShoppingManager implements ShoppingManager {


    private final ProductMapper mapper = new ProductMapper();
    private static final Scanner SCANNER = new Scanner(System.in);
    public static List<Product> products = new ArrayList<>();

    @Override
    public Product addNewProduct(Product product) {
        if (products.size() == 50) {
            throw new ArrayIndexOutOfBoundsException("The store archived its maximum capacity.");
        } else {
            Objects.requireNonNull(product,
                    () -> {
                        throw new NullProductException("Product cannot be null.");
                    });

            if (CommonUtils.getProduct(product.getProductID(), products)) {
                throw new ProductAlreadyExistsException("Product already exists in the shopping cart.");
            } else {
                products.add(product);
                return product;
            }
        }
    }

    @Override
    public Product deleteProduct(String productID) {
        if (CommonUtils.getProduct(productID, products)) {
            Product product = products.stream().filter(p -> p.getProductID().equals(productID)).findFirst().orElseThrow();
            String choice = null;
            while (true) {
                System.out.println("Are you want to delete the product?\n");
                System.out.println(product);
                System.out.println("1. Yes\n2. No");
                choice = SCANNER.next();
                if (choice.trim().isEmpty()) {
                    System.out.println("please select valid option");
                } else {
                    if (choice.matches("^\\d+")) {
                        switch (Integer.parseInt(choice)) {
                            case 1 -> {
                                products.remove(product);
                                return product;
                            }
                            case 2 -> {
                                break;
                            }
                        }
                    }
                }
            }
        } else {
            throw new ProductRemovalException("Failed to remove product from the shopping cart.");
        }
    }

    @Override
    public void printProducts() {
        if (products.isEmpty()) {
            System.out.println("There is no products in stock");
        } else {
            products.stream()
                    .map(Product::toString)
                    .forEach(System.out::println);
        }
    }

    @Override
    public void saveToFile() {
        //CommonUtils.writeEncryptedToFile(Collections.singletonList(products), "resource/products.txt", SECRET_KEY);
        try {
            // Create a new file output stream to write to a file
            FileOutputStream fileOut = new FileOutputStream("resource/products.ser");

            // Create a new print writer to write text to the file
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            // Write the object to the print writer
            objectOut.writeObject(products);

            // Close the print writer
            objectOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void openGUI() {
        WestminsterShoppingManager manager = new WestminsterShoppingManager();
        //manager.init();
        new MainFrame();
    }

    @Override
    public void loadFromFile() {
        try {
            // Create a new file input stream to read from the saved file
            FileInputStream fileIn = new FileInputStream("resource/products.ser");

            // Deserialize the object from the string representation
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            products = (List<Product>) objectIn.readObject();
            objectIn.close();

            System.out.println("Loaded Successfully");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void init() {
        //products.add(new Clothing("C001", "T-Shirt", 50, 19.99, "M", "Blue"));
//        products.add(new Clothing("C002", "Jeans", 30, 49.99, "L", "Black"));
//        products.add(new Clothing("C003", "Dress Shirt", 20, 29.99, "S", "White"));
//        products.add(new Clothing("C004", "Sweater", 40, 39.99, "XL", "Gray"));
//        products.add(new Clothing("C005", "Shorts", 25, 24.99, "M", "Green"));
//        products.add(new Clothing("C006", "Hoodie", 35, 44.99, "L", "Red"));
//        products.add(new Clothing("C007", "Skirt", 15, 34.99, "S", "Pink"));
//        products.add(new Clothing("C008", "Jacket", 45, 59.99, "XL", "Brown"));
//        products.add(new Clothing("C009", "Socks", 100, 9.99, "One Size", "Black"));
//        products.add(new Clothing("C010", "Hat", 60, 14.99, "One Size", "Blue"));
//
//        // Electronics instances
//        products.add(new Electronics("E001", "Laptop", 10, 899.99, "Dell", 2));
//        products.add(new Electronics("E002", "Smartphone", 30, 699.99, "Samsung", 1));
//        products.add(new Electronics("E003", "Headphones", 50, 79.99, "Sony", 1));
//        products.add(new Electronics("E004", "Smartwatch", 15, 149.99, "Apple", 1));
//        products.add(new Electronics("E005", "Camera", 8, 499.99, "Canon", 3));
//        products.add(new Electronics("E006", "Bluetooth Speaker", 25, 29.99, "JBL", 1));
//        products.add(new Electronics("E007", "Gaming Console", 12, 399.99, "Microsoft", 2));
//        products.add(new Electronics("E008", "Tablet", 20, 249.99, "Lenovo", 1));
//        products.add(new Electronics("E009", "Printer", 5, 129.99, "HP", 2));
//        products.add(new Electronics("E010", "External Hard Drive", 18, 79.99, "Seagate", 2));
//
//        // Adding 10 more Clothing instances
//        products.add(new Clothing("C011", "Polo Shirt", 40, 29.99, "L", "Yellow"));
//        products.add(new Clothing("C012", "Joggers", 20, 34.99, "M", "Gray"));
//        products.add(new Clothing("C013", "Dress", 15, 69.99, "S", "Purple"));
//        products.add(new Clothing("C014", "Sweatpants", 30, 24.99, "XL", "Navy"));
//        products.add(new Clothing("C015", "Blouse", 25, 39.99, "M", "Red"));
//        products.add(new Clothing("C016", "Denim Jacket", 18, 54.99, "L", "Denim Blue"));
//        products.add(new Clothing("C017", "Tank Top", 35, 14.99, "S", "Green"));
//        products.add(new Clothing("C018", "Beanie", 50, 9.99, "One Size", "Black"));
//        products.add(new Clothing("C019", "Winter Coat", 10, 89.99, "XL", "White"));
//        products.add(new Clothing("C020", "Scarf", 45, 19.99, "One Size", "Pink"));
//
//// Adding 10 more Electronics instances
//        products.add(new Electronics("E011", "Desktop Computer", 8, 1299.99, "HP", 3));
//        products.add(new Electronics("E012", "Fitness Tracker", 40, 49.99, "Fitbit", 1));
//        products.add(new Electronics("E013", "Wireless Mouse", 25, 19.99, "Logitech", 1));
//        products.add(new Electronics("E014", "VR Headset", 7, 299.99, "Oculus", 2));
//        products.add(new Electronics("E015", "Smart Thermostat", 15, 129.99, "Nest", 2));
//        products.add(new Electronics("E016", "Digital Camera", 12, 399.99, "Nikon", 3));
//        products.add(new Electronics("E017", "Bluetooth Earbuds", 30, 39.99, "Anker", 1));
//        products.add(new Electronics("E018", "Tablet Stand", 20, 14.99, "AmazonBasics", 1));
//        products.add(new Electronics("E019", "Wireless Keyboard", 18, 29.99, "Microsoft", 1));
//        products.add(new Electronics("E020", "Solar Charger", 22, 49.99, "RAVPower", 1));


    }

    private static void displayMenu() {
        System.out.print("\nWelcome to Westminster Shopping center. \n" +
                "1. Add new product \n" +
                "2. Delete a product \n" +
                "3. Show all products \n" +
                "4. Save details \n" +
                "5. Open GUI \n" +
                "6. Load from file \n" +
                "0. Exit the application \n" +
                "Please select an option: ");
    }

    private static Product getProductDetails(String type) {
        String productID, productName, size, colour, brand;
        int warrantyPeriod, availableItems;
        double price;
        boolean isFinished = true;

        productID = getProductID("product ID");
        productName = getProductAndBrandName("product name");
        availableItems = getIntegerDetails("number of items available");
        price = getPrice("price");

        if (type.equalsIgnoreCase("electronics")) {
            brand = getProductAndBrandName("brand name");
            warrantyPeriod = getIntegerDetails("warranty periods in weeks");
            return new Electronics(productID, productName, availableItems, price, brand, warrantyPeriod);
        } else {
            size = getProductID("size of the " + productName);
            colour = getProductID("colour of the" + productName);
            return new Clothing(productID, productName, availableItems, price, size, colour);
        }
    }

    private static <T> T getInput(String detail, Validator<T> validator) {
        boolean isValid = false;
        T input = null;
        while (!isValid) {
            System.out.printf("Please enter the %s: ", detail);
            String userInput = SCANNER.nextLine().trim();
            if (userInput != null && !userInput.isEmpty()) {
                try {
                    input = validator.validate(userInput);
                    isValid = true;
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid input. " + e.getMessage());
                }
            } else {
                System.out.println("Invalid input. Please enter a non-null value.");
            }
        }
        return input;
    }


    // Implement specific validators
    private static final Validator<String> WHITE_SPACE_VALIDATOR = input -> {
        if (CommonUtils.validateWhiteSpaces(input)) {
            return input;
        } else {
            throw new IllegalArgumentException("Input must not contain only white spaces.");
        }
    };

    private static final Validator<String> PRODUCT_NAME_VALIDATOR = input -> {
        if (CommonUtils.isValidProductName(input)) {
            return input;
        } else {
            throw new IllegalArgumentException("Invalid product name.");
        }
    };

    private static final Validator<Integer> INTEGER_VALIDATOR = input -> {
        if (CommonUtils.validateInteger(input)) {
            return Integer.parseInt(input);
        } else {
            throw new IllegalArgumentException("Invalid integer input.");
        }
    };

    private static final Validator<Double> DOUBLE_VALIDATOR = input -> {
        if (CommonUtils.validateDouble(input)) {
            return Double.parseDouble(input);
        } else {
            throw new IllegalArgumentException("Invalid double input.");
        }
    };

    private static String getProductID(String detail) {
        return getInput(detail, WHITE_SPACE_VALIDATOR);
    }

    private static String getProductAndBrandName(String detail) {
        return getInput(detail, PRODUCT_NAME_VALIDATOR);
    }

    private static int getIntegerDetails(String detail) {
        return getInput(detail, INTEGER_VALIDATOR);
    }

    private static double getPrice(String detail) {
        return getInput(detail, DOUBLE_VALIDATOR);
    }


    public static void main(String[] args) {
        WestminsterShoppingManager manager = new WestminsterShoppingManager();
        //manager.init();
        boolean isRunning = true;
        while (isRunning) {
            displayMenu();
            String input = SCANNER.nextLine();

            if (input.matches("\\d")) {
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 0 -> {
                        SCANNER.close();
                        System.out.println("Thank you for using our application");
                        isRunning = false;
                    }

                    case 1 -> {
                        String opt = null;
                        boolean isFished = false;
                        while (!isFished) {
                            System.out.println("Please select the type of the product:\n1. Electronics \n2. Clothing");
                            SCANNER.next();
                            int ch = getIntegerDetails("option");
                            switch (ch) {
                                case 1 -> {
                                    Electronics electronics = (Electronics) getProductDetails("electronics");
                                    manager.addNewProduct(electronics);
                                    System.out.println("Product added to the system. \n" + electronics);
                                    isFished = true;
                                }
                                case 2 -> {
                                    Clothing clothing = (Clothing) getProductDetails("clothing");
                                    manager.addNewProduct(clothing);
                                    System.out.println("Product added to the system. \n" + clothing);
                                    isFished = true;
                                }
                                default -> {
                                    System.out.println("please select valid option");
                                }
                            }
                        }
                    }
                    case 2 -> {
                        String productID = getProductID("product ID");
                        manager.deleteProduct(productID);
                    }

                    case 3 -> {
                        manager.printProducts();
                    }

                    case 4 -> {
                        manager.saveToFile();
                    }

                    case 5 -> {
                        manager.openGUI();
                    }

                    case 6 -> {
                        manager.loadFromFile();
                    }

//                    case 7 -> {
//                        manager.init();
//                    }

                    default -> {
                        System.out.println("Invalid choice. Please enter a valid option.");
                    }
                }
            } else {
                System.out.println("Invalid input. Please enter a valid option (numeric).");
            }
        }
    }
}
