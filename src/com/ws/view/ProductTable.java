package com.ws.view;

import com.ws.model.Clothing;
import com.ws.model.Electronics;
import com.ws.model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

public class ProductTable extends JTable {

    public ProductTable(List<Product> products) {
        String[] columns = {"Product ID", "Name", "Category", "Price", "Info"};

        DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        products.forEach(product -> {
            Object[] rowData = {
                    product.getProductID(),
                    product.getProductName(),
                    product instanceof Electronics ? "Electronics" : "Clothing",
                    product.getPrice(),
                    product instanceof Electronics ?
                            ((Electronics) product).getBrand() + ", " + ((Electronics) product).getWarrantyPeriod() + " weeks warranty " :
                            ((Clothing) product).getSize() + ", " + ((Clothing) product).getColour()
            };
            tableModel.addRow(rowData);
        });
        super.setModel(tableModel);
        TableRowSorter<?> sorter = new TableRowSorter<>(tableModel);
        super.setRowSorter(sorter);
        super.setDefaultRenderer(Object.class, new CellColor(products));

    }

    private static class CellColor extends DefaultTableCellRenderer {

        private final List<Product> products;

        public CellColor(List<Product> products) {
            this.products = products;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            int modelIndex = table.getRowSorter().convertRowIndexToModel(row);
            Product product = products.get(modelIndex);

            if (product.getAvailableItems() <= 3) {
                super.setBackground(Color.RED);
                super.setForeground(Color.WHITE);
            } else {
                super.setBackground(table.getBackground());
                super.setForeground(table.getForeground());
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }
}
