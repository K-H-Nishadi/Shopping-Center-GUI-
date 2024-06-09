package com.ws.view;

import com.ws.model.Clothing;
import com.ws.model.Electronics;
import com.ws.model.ShoppingCart;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ShoppingCartView extends JFrame {
    private final ShoppingCart shoppingCart;
    static boolean isShoppingCartOpen = false;

    public ShoppingCartView(ShoppingCart shoppingCart) throws HeadlessException {
        super("Shopping Cart");
        this.shoppingCart = shoppingCart;
        isShoppingCartOpen = true;
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleWindowClosing();
            }
        });
        isShoppingCartOpen = true;
        ImageIcon icn = new ImageIcon("resource/logo.jpeg");

        JPanel rootPanel = new JPanel(new BorderLayout());
        JPanel rSpace = new JPanel();
        rSpace.setPreferredSize(new Dimension(100, 0));
        rSpace.setBackground(Color.BLUE);
        rootPanel.add(rSpace, BorderLayout.EAST);
        JPanel lSpace = new JPanel();
        lSpace.setPreferredSize(new Dimension(100, 0));
        lSpace.setBackground(Color.BLUE);
        rootPanel.add(lSpace, BorderLayout.WEST);

        rootPanel.add(createContainer());

        this.add(rootPanel);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setIconImage(icn.getImage());
        this.setTitle("Shopping Cart");
        this.setResizable(false);
        this.setVisible(true);
    }

    private JPanel createContainer() {
        JPanel container = new JPanel(new BorderLayout(0, 10));
        JScrollPane tableContainer = new JScrollPane(createShoppingCart(this.shoppingCart));
        container.add(tableContainer, BorderLayout.NORTH);
        container.add(createPriceInfo(this.shoppingCart), BorderLayout.CENTER);
        return container;
    }

    private JPanel createPriceInfo(ShoppingCart cart) {
        JPanel container = new JPanel(new GridLayout(4, 1));
        JLabel label1 = new JLabel("Total\t" + cart.calculateTotalPrice());
        JLabel label2 = new JLabel("First purchase discount\t" + 25.052);
        JLabel label3 = new JLabel("Three items in same category\t" + 25);
        JLabel label4 = new JLabel("Final total\t" + cart.calculateTotalPrice());
        container.add(label1);
        container.add(label2);
        container.add(label3);
        container.add(label4);
        return container;
    }

    private JTable createShoppingCart(ShoppingCart cart) {
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Product", "Quantity", "Price"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        cart.getProducts().forEach(product -> {
            String electronics = product instanceof Electronics ? (((Electronics) product).getBrand() + ", " + ((Electronics) product).getWarrantyPeriod()) :
                    (((Clothing) product).getSize() + ", " + ((Clothing) product).getColour());
            String prod = product.getProductID() + ", " + product.getProductID() + ", " + product.getProductName() + ", " + electronics;

            Object[] rowData = {
                    prod,
                    product.getAvailableItems(),
                    product.getPrice()
            };
            tableModel.addRow(rowData);
        });
        return new JTable(tableModel);
    }

    private void handleWindowClosing() {
        int option = JOptionPane.showConfirmDialog(
                this,
                "Closing the shopping cart will remove all items. Are you sure?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                new ImageIcon("resource/warning_icn_64.png"));

        if (option == JOptionPane.YES_OPTION) {
            // If the user clicks "Yes," close the window
            isShoppingCartOpen = false;
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        } else if (option == JOptionPane.NO_OPTION) {
            isShoppingCartOpen = true;
            this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        }
    }

}
