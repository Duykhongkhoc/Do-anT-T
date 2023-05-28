/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.event.*;

public class NewClass {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JButton button1 = new JButton("Open Dialog 1");
        JButton button2 = new JButton("Open Dialog 2");
        JTextField textField = new JTextField(10);
        JDialog dialog = new JDialog(frame, "Dialog", true);
        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
        dialog.add(textField);
        JButton closeButton = new JButton("Close Dialog");
        dialog.add(closeButton);
        dialog.pack();
        
        ActionListener openDialogListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(true);
            }
        };
        
        button1.addActionListener(openDialogListener);
        button2.addActionListener(openDialogListener);
        
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String value = textField.getText();
                JButton sourceButton = (JButton) e.getSource();
                sourceButton.setText(value);
                dialog.setVisible(false);
            }
        });
        frame.setLayout(new FlowLayout());
        frame.add(button1);
        frame.add(button2);
        frame.pack();
        frame.setVisible(true);
    }
}
