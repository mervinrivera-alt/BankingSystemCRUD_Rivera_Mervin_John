/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bank_systems_v1;

/**
 *
 * @author river
 */
public class Bank_Systems_v1 {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Frame frame1 = new Frame();
                frame1.setLocationRelativeTo(null);
                frame1.setVisible(true);
            }
        });
    }

    // Call this from anywhere: MainClass.switchWindow(this, new Logs());
    public static void switchWindow(javax.swing.JFrame current, javax.swing.JFrame next) {
        next.setLocationRelativeTo(null);
        next.setVisible(true);
        if (current != null) {
            current.dispose(); // Deletes the old window from memory
        }
    }
    
}
