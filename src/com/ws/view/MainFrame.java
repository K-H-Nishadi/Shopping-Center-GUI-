package com.ws.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() throws HeadlessException {

        this.add(new ProductView());

        ImageIcon icn = new ImageIcon("resource/logo.jpeg");

        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setIconImage(icn.getImage());
        this.setTitle("Westminster Shopping Center");
        this.setVisible(true);
    }

}
