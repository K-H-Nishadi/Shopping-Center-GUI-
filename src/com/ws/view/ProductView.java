package com.ws.view;

import com.ws.controller.WestminsterShoppingManager;
import com.ws.exception.ProductNotFoundException;
import com.ws.model.Clothing;
import com.ws.model.Electronics;
import com.ws.model.Product;
import com.ws.model.ShoppingCart;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductView extends JPanel {

    private final JButton shoppingCartBtn = new JButton("Shopping Cart", new ImageIcon("resource/shopping_cart.png"));
    private final JButton addToCartBtn = new JButton("Add to Shopping Cart");
    private final JComboBox<String> categories = new JComboBox<>();
    private final List<Product> products = WestminsterShoppingManager.products;

    private final ProductTable productsTable = new ProductTable(products);
    Product product;
    private final ShoppingCart shoppingCart = new ShoppingCart(new ArrayList<>());
    private final JLabel label1 = new JLabel();
    private final JLabel label2 = new JLabel();
    private final JLabel label3 = new JLabel();
    private final JLabel label4 = new JLabel();
    private final JLabel label5 = new JLabel();
    private final JLabel label6 = new JLabel();
    private final JLabel label7 = new JLabel();
    private final JLabel label8 = new JLabel();

    public ProductView() {
        this.setLayout(new BorderLayout());

        this.add(createRow_1(), BorderLayout.NORTH);
        this.add(createRow_2(), BorderLayout.CENTER);

        categories.addActionListener(
                e -> filterProductsByCategory(Objects.requireNonNull(categories.getSelectedItem()).toString())
        );
        addToCartBtn.addActionListener(e -> {
            shoppingCart.addProduct(product);
        });


        shoppingCartBtn.addActionListener(e -> {
            if (!ShoppingCartView.isShoppingCartOpen) {
                new ShoppingCartView(shoppingCart);
            }
        });



        this.add(createRow_3(), BorderLayout.SOUTH);
    }

    // To hold the button for shopping cart btn
    private JPanel createRow_1() {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        shoppingCartBtn.setFocusable(false);
        row.add(shoppingCartBtn);
        return row;
    }

    // To hold the drop-down and table and info
    private JPanel createRow_2() {
        JPanel row = new JPanel(new BorderLayout());
        row.add(createContainerForDropDown(), BorderLayout.NORTH);
        row.add(createContainerForTableAndInfo(), BorderLayout.CENTER);
        JPanel rSpace = new JPanel();
        rSpace.setPreferredSize(new Dimension(100, 0));
        row.add(rSpace, BorderLayout.EAST);
        JPanel lSpace = new JPanel();
        lSpace.setPreferredSize(new Dimension(100, 0));
        row.add(lSpace, BorderLayout.WEST);
        return row;
    }

    // Container for drop-down
    private JPanel createContainerForDropDown() {
        JPanel row = new JPanel(new GridLayout());
        row.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel leftPanel = new JPanel();
        gbc.weightx = 3.0;
        JLabel cLabel = new JLabel("Select Product Category: ");
        leftPanel.add(cLabel);

        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        comboBoxModel.addElement("All");
        comboBoxModel.addElement("Electronics");
        comboBoxModel.addElement("Clothing");
        categories.setModel(comboBoxModel);
        leftPanel.add(categories);
        row.add(leftPanel);
        return row;
    }

    // Container for  table and info
    private JPanel createContainerForTableAndInfo() {
        JPanel row = new JPanel(new BorderLayout());
        row.add(tableContainer(), BorderLayout.NORTH);
        row.add(createInfo(), BorderLayout.CENTER);
        return row;
    }

    // Container for  table
    private JScrollPane tableContainer() {
        return new JScrollPane(productsTable);
    }

    private void filterProductsByCategory(String selectedCategory) {
        TableRowSorter<?> sorter = (TableRowSorter<?>) productsTable.getRowSorter();

        if ("All".equals(selectedCategory)) {
            sorter.setRowFilter(null); // Show all rows
        } else {
            sorter.setRowFilter(RowFilter.regexFilter(selectedCategory, 2)); // Filter based on category column
        }
    }

    // Container for and info
    private JPanel createInfo() {
        JPanel row = new JPanel(new GridLayout(7, 1));
        ListSelectionModel listSelectionModel = productsTable.getSelectionModel();
        listSelectionModel.addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                int selectedRow = productsTable.getSelectedRow();
                if (selectedRow == -1) {
                    hideLabels();
                    return; // No row selected, hide labels and do nothing
                }
                Object productId = productsTable.getValueAt(selectedRow, 0);

                product = WestminsterShoppingManager.products.stream()
                        .filter(p -> p.getProductID().equals(productId.toString()))
                        .findFirst()
                        .orElseThrow(
                                () -> new ProductNotFoundException("There is no product with this ID")
                        );

                label1.setText("Selected Product - Details");
                label2.setText("Product Id: " + product.getProductID());
                label3.setText("Category: " + (product instanceof Electronics ? "Electronics" : "Clothing"));
                label4.setText("Name: " + product.getProductName());
                label5.setText(product instanceof Electronics ?
                        "Brand: " + ((Electronics) product).getBrand() : "Size: " + ((Clothing) product).getSize());

                label6.setText(product instanceof Electronics ?
                        "Warranty period(weeks): " + ((Electronics) product).getWarrantyPeriod()
                        : "Colour: " + ((Clothing) product).getColour());

                label8.setText("Items Available: " + product.getAvailableItems());

                showLabels();
            }
        });
        row.add(label1);
        row.add(label2);
        row.add(label3);
        row.add(label4);
        row.add(label5);
        row.add(label6);
        row.add(label8);
        return row;
    }

    private void hideLabels() {
        label1.setVisible(false);
        label2.setVisible(false);
        label3.setVisible(false);
        label4.setVisible(false);
        label5.setVisible(false);
        label6.setVisible(false);
        label7.setVisible(false);
        label8.setVisible(false);
    }

    private void showLabels() {
        label1.setVisible(true);
        label2.setVisible(true);
        label3.setVisible(true);
        label4.setVisible(true);
        label5.setVisible(true);
        label6.setVisible(true);
        label7.setVisible(true);
        label8.setVisible(true);
    }

    // Container for add to cart btn
    private JPanel createRow_3() {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        addToCartBtn.setFocusable(false);
        row.add(addToCartBtn);
        return row;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
}
