package com.customify.desktop.components;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class FormControl extends JPanel {

    public FormControl(String placeholderTextParam){
        setBackground(Color.white);
        JLabel placeholderText = new JLabel(placeholderTextParam);
        placeholderText.setFont(new Font("Montserrat", Font.PLAIN, 18));
        placeholderText.setBackground(Color.white);
        placeholderText.setPreferredSize(new Dimension(200, 30));
        placeholderText.setBounds(2,30,100,200);

        JTextField textField = new JTextField("", 20);
        textField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.black, 1, true),
                BorderFactory.createEmptyBorder(8, 15, 8, 15))
        );
        textField.setFont(new Font("Montserrat", Font.PLAIN, 18));
        textField.setBounds(2,3,100,200);

        add(placeholderText);
        add(textField);
        setPreferredSize(new Dimension(100,500));
    }
}
