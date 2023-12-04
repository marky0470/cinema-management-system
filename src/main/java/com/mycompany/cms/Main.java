/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cms;
import com.formdev.flatlaf.FlatLightLaf;
import com.mycompany.cms.gui.LoginForm;
import javax.swing.*;

/**
 *
 * @author marks
 */
public class Main {

    public static void main(String[] args) {
        
        try {
            javax.swing.UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            
            LoginForm loginWindow = new LoginForm();
            
            loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            loginWindow.pack();
            loginWindow.setVisible(true);
        });
        
    };
}
