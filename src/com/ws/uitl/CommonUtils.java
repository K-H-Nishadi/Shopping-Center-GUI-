package com.ws.uitl;

import com.ws.model.Product;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CommonUtils {

    private final static Logger LOGGER = Logger.getLogger(CommonUtils.class.getName());


    public static boolean getProduct(String productID, List<Product> products) {
        return products.stream().anyMatch(p -> p.getProductID().equals(productID));
    }

    public static boolean validateWhiteSpaces(String input) {
        if (input.split(" ").length == 1) {
            return true;
        } else {
            System.out.println("Invalid input. Please enter a valid option.");
            return false;
        }
    }

    public static boolean isValidProductName(String productName) {
        if (productName != null && !productName.trim().isEmpty()) {
            return true;
        } else {
            System.out.println("Invalid input. Please enter a valid product name");
            return false;
        }
    }

    public static boolean validateInteger(String availableItems) {
        if (availableItems.matches("\\d+")) {
//            try {
            int count = Integer.parseInt(availableItems);
            if (count >= 0) {
                return true;
            } else {
                System.out.println("Please enter a valid number of available items (It should be greater than or equal to 0).");
                return false;
            }
//            } catch (NumberFormatException e) {
            // Parsing failed
//            }
        } else {
            System.out.println("Please enter a valid input for the number of available items. (It should be a non-negative integer)");
            return false;
        }
    }


    public static boolean validateDouble(String price) {
        if (price.matches("[-+]?\\d+")) {
            double p = Double.parseDouble(price);
            if (p >= 0.0) {
                return true;
            } else {
                System.out.println("Please enter a valid input for price(It should be a non-negative number)");
                return false;
            }
        } else {
            System.out.println("Please enter a valid input for price");
            return false;
        }
    }

    public static void writeEncryptedToFile(List<Object> objects, String filePath, String secretKey) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);

            oos.writeObject(objects);
            byte[] serializedData = baos.toByteArray();

            byte[] encryptedData = encrypt(serializedData, secretKey);

            // Write the encrypted data to the file
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                fos.write(encryptedData);
                LOGGER.info("Data successfully written to file: " + filePath);
            }
        } catch (IOException | GeneralSecurityException e) {
            LOGGER.log(Level.WARNING, e.getLocalizedMessage());
        }
    }

    private static byte[] encrypt(byte[] data, String secretKey) throws
            NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        return cipher.doFinal(data);
    }

    public static List<Object> readEncryptedFromFile(String filePath, String secretKey) {
        try {
            // Read the encrypted data from the file
            byte[] encryptedData = Files.readAllBytes(Paths.get(filePath));

            // Decrypt the data
            byte[] decryptedData = decrypt(encryptedData, secretKey);

            // Deserialize the decrypted data
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decryptedData);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (List<Object>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException | GeneralSecurityException e) {
            LOGGER.log(Level.WARNING, e.getLocalizedMessage());
            return null;
        }
    }

    private static byte[] decrypt(byte[] encryptedData, String secretKey) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        return cipher.doFinal(encryptedData);
    }
}
