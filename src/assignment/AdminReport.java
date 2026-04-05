package assignment;

import javax.swing.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 *
 * @author USER
 */
public class AdminReport extends javax.swing.JFrame {
    private Map<String, String> subjectIDMap = new HashMap<>();
    private Map<String, String> subjectRevenueMap = new HashMap<>();
    private Map<String, String> subjectLevelMap = new HashMap<>();
    private String selectedLevel = "";

    public AdminReport() {
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        loadLevels();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jComboBox2 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Report Generation Menu");

        jButton2.setBackground(java.awt.Color.red);
        jButton2.setForeground(java.awt.Color.black);
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox1, 0, 237, Short.MAX_VALUE)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addGap(43, 43, 43)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String selectedSubject = (String) jComboBox1.getSelectedItem();
        if (selectedSubject == null) return;

        String subjectID = subjectIDMap.get(selectedSubject);
        String revenue = subjectRevenueMap.get(selectedSubject);
        String level = subjectLevelMap.get(selectedSubject);

        if (subjectID == null || revenue == null || level == null) {
            jTextArea1.setText("No data found for: " + selectedSubject);
            return;
        }

        try {
            Files.createDirectories(Paths.get("report"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String reportPath = "report/" + subjectID + "_report.txt";
        Path reportFile = Paths.get(reportPath);

        try {
            if (Files.exists(reportFile)) {
                Files.delete(reportFile);
            }

            try (BufferedWriter writer = Files.newBufferedWriter(reportFile)) {
                writer.write("Subject Name: " + selectedSubject + "\n");
                writer.write("Subject ID: " + subjectID + "\n");
                writer.write("Subject Level: Form " + level + "\n");
                writer.write("Monthly Revenue: " + revenue + "\n");
            }

            displayFile(reportPath);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        String selectedForm = (String) jComboBox2.getSelectedItem();
        if (selectedForm == null) return;

        selectedLevel = selectedForm.replace("Form ", "");

        loadSubjectsByLevel(selectedLevel);
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void displayFile(String reportPath) {
        try {
            Path path = Paths.get(reportPath);
            String content = Files.readString(path);
            jTextArea1.setText(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void loadLevels() {
        // Populate Forms 1–5
        for (int i = 1; i <= 5; i++) {
            jComboBox2.addItem("Form " + i);
        }
    }

    
    private void loadSubjectsByLevel(String level) {
        jComboBox1.removeAllItems();
        jComboBox1.addItem("---");
        subjectIDMap.clear();
        subjectRevenueMap.clear();
        subjectLevelMap.clear();

        Path filePath = Paths.get("subjects.txt");
        Set<String> addedSubjects = new HashSet<>();
        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("::");
                if (parts.length >= 7) {
                    String subjectID = parts[1].trim(); 
                    String subjectName = parts[2].trim();
                    String revenue = parts[6].trim();

                    if (subjectID.length() >= 3) {
                        String levelDigit = subjectID.substring(2, 3); 
                        if (levelDigit.equals(level) && !addedSubjects.contains(subjectName)) {
                            jComboBox1.addItem(subjectName);
                            addedSubjects.add(subjectName);
                        }
                        subjectIDMap.put(subjectName, subjectID);
                        subjectRevenueMap.put(subjectName, revenue);
                        subjectLevelMap.put(subjectName, levelDigit);
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
