/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import view.NewJFrame;

/**
 *
 * @author jiang
 */
public class Main {
    public static void main(String[] args) {
        try {
            FlatArcIJTheme.setup();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        NewJFrame newJFrame = new NewJFrame();
        newJFrame.setVisible(true);
    }
}
