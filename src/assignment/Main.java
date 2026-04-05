package assignment;

// Main.java

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Set the look and feel to the system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // If setting system look and feel fails, continue with default
            e.printStackTrace();
        }
        
        // Initialize data files
        FileHandler.initializeFiles();
        
        // Start the application on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Login loginForm = new Login();
                loginForm.setVisible(true);
            }
        });
    }
} 