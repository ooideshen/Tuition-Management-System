
package assignment;
import java.io.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
/**
 *
 * @author USER
 */
public class AddEditStudent extends javax.swing.JFrame {

    /**
     * Creates new form AddEditStudent
     */
    public AddEditStudent() {
        initComponents();
        javax.swing.event.DocumentListener docListener = new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { checkFields(addNew); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { checkFields(addNew); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { checkFields(addNew); }
        };
        editName.getDocument().addDocumentListener(docListener);
        editPass.getDocument().addDocumentListener(docListener);
        editIC.getDocument().addDocumentListener(docListener);
        editEmail.getDocument().addDocumentListener(docListener);
        editPhone.getDocument().addDocumentListener(docListener);
        editAddress.getDocument().addDocumentListener(docListener);
        editLevel.getDocument().addDocumentListener(docListener);
        setDefaultCloseOperation(AddEditStudent.DISPOSE_ON_CLOSE);
        String [] colHeading = {"Student ID","Username","Password","IC/Passport","Email","Phone Number","Address","Level"};
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setColumnIdentifiers(colHeading);


        try {
            BufferedReader br = new BufferedReader(new FileReader("students.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split("::");
                if (data.length == 8) {           
                    String userID = data[0].trim();
                    String username = data[2].trim();
                    String password = data[1].trim();
                    String ic_passport = data[3].trim();
                    String email = data[4].trim();
                    String phone_number = data[5].trim();
                    String address = data[6].trim();
                    String level = data[7].trim();

                    model.addRow(new Object[]{userID, username, password, ic_passport, email, phone_number, address, level});
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        save.setEnabled(false);
        delete.setEnabled(false);
        addNew.setEnabled(false);
        }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        save = new javax.swing.JButton();
        editPhone = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        editName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        editPass = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        editEmail = new javax.swing.JTextField();
        addNew = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        delete = new javax.swing.JButton();
        recep_back = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        editIC = new javax.swing.JTextField();
        editLevel = new javax.swing.JTextField();
        editAddress = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        editPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPhoneActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Password");

        editName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editNameActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Username");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Email");

        editPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPassActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Table of Registered Students");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Phone Number");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        addNew.setText("Add New");
        addNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Student Management ");

        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        recep_back.setBackground(java.awt.Color.red);
        recep_back.setForeground(java.awt.Color.black);
        recep_back.setText("Back");
        recep_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recep_backActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("IC/Passport");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Address");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Level");

        editIC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editICActionPerformed(evt);
            }
        });

        editLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editLevelActionPerformed(evt);
            }
        });

        editAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editAddressActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(addNew)
                .addGap(48, 48, 48)
                .addComponent(delete)
                .addGap(50, 50, 50)
                .addComponent(save)
                .addGap(50, 50, 50)
                .addComponent(recep_back)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(editEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                                    .addComponent(editPass)
                                    .addComponent(editName)
                                    .addComponent(editPhone))
                                .addGap(18, 29, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(18, 18, 18)
                                        .addComponent(editLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(18, 18, 18)
                                        .addComponent(editAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(18, 18, 18)
                                        .addComponent(editIC, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(editIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel12)
                    .addComponent(editAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(editEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(save)
                                .addComponent(recep_back))
                            .addComponent(delete)
                            .addComponent(addNew))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int selectedRowIndex = jTable1.getSelectedRow();
        if (selectedRowIndex >= 0) {
            model.setValueAt(editName.getText().trim(), selectedRowIndex, 2);
            model.setValueAt(editPass.getText().trim(), selectedRowIndex, 1);
            model.setValueAt(editIC.getText().trim(), selectedRowIndex, 3);
            model.setValueAt(editEmail.getText().trim(), selectedRowIndex, 4);
            model.setValueAt(editPhone.getText().trim(), selectedRowIndex, 5);
            model.setValueAt(editAddress.getText().trim(), selectedRowIndex, 6);
            model.setValueAt(editLevel.getText().trim(), selectedRowIndex, 7);


            try (BufferedWriter bw = new BufferedWriter(new FileWriter("students.txt", false))) {
                for (int i = 0; i < model.getRowCount(); i++) {
                    String id = model.getValueAt(i, 0).toString();
                    String pass = model.getValueAt(i, 2).toString();
                    String uname = model.getValueAt(i, 1).toString();
                    String ic = model.getValueAt(i, 3).toString();
                    String email = model.getValueAt(i, 4).toString();
                    String phone = model.getValueAt(i, 5).toString();
                    String address = model.getValueAt(i, 6).toString();
                    String level = model.getValueAt(i, 7).toString();

                    String line = String.join("::", id, pass, uname, ic, email, phone, address, level);
                    bw.write(line);
                    bw.newLine();
                }
                System.out.println("File updated successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No row selected to save.");
        }
    }//GEN-LAST:event_saveActionPerformed

    private void editPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editPhoneActionPerformed

    private void editNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editNameActionPerformed

    private void editPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editPassActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
         DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int selectedRowIndex = jTable1.getSelectedRow();
        if (selectedRowIndex >= 0) {
            editName.setText(model.getValueAt(selectedRowIndex, 2).toString());
            editPass.setText(model.getValueAt(selectedRowIndex, 1).toString());
            editIC.setText(model.getValueAt(selectedRowIndex, 3).toString());
            editEmail.setText(model.getValueAt(selectedRowIndex, 4).toString());
            editPhone.setText(model.getValueAt(selectedRowIndex, 5).toString());
            editAddress.setText(model.getValueAt(selectedRowIndex, 6).toString());
            editLevel.setText(model.getValueAt(selectedRowIndex, 7).toString());
            save.setEnabled(true);
            delete.setEnabled(true);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void addNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewActionPerformed
        txtManagementFunctions run = new txtManagementFunctions();
        List<String[]> data = run.openTxtFile("students.txt");
        String newID = run.generateID(data, "ST");

        String[] row = {
            newID,
            editName.getText().trim(),
            editPass.getText().trim(),
            editIC.getText().trim(),
            editEmail.getText().trim(),
            editPhone.getText().trim(),
            editAddress.getText().trim(),
            editLevel.getText().trim()
        };

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(row);

        String[] txtRow = {
            newID,
            editPass.getText().trim(),
            editName.getText().trim(),
            editIC.getText().trim(),
            editEmail.getText().trim(),
            editPhone.getText().trim(),
            editAddress.getText().trim(),
            editLevel.getText().trim()
        };

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt", true))) {
            writer.write(String.join("::", txtRow));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_addNewActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow >= 0) {
            String targetID = model.getValueAt(selectedRow, 0).toString();

            txtManagementFunctions run = new txtManagementFunctions();
            run.deleteID(targetID, "students.txt");

            model.removeRow(selectedRow);

            editName.setText("");
            editPass.setText("");
            editEmail.setText("");
            editPhone.setText("");

            save.setEnabled(false);
            delete.setEnabled(false);

            System.out.println("Deleted students ID: " + targetID);
        } else {
            System.out.println("No row selected.");
        }
    }//GEN-LAST:event_deleteActionPerformed

    private void recep_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recep_backActionPerformed
        this.dispose();
    }//GEN-LAST:event_recep_backActionPerformed

    private void editICActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editICActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editICActionPerformed

    private void editAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editAddressActionPerformed

    private void editLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editLevelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editLevelActionPerformed
    
    private void checkFields(javax.swing.JButton button) {
    boolean allFilled = !editName.getText().trim().isEmpty()
        && !editPass.getText().trim().isEmpty()
        && !editIC.getText().trim().isEmpty()
        && !editEmail.getText().trim().isEmpty()
        && !editPhone.getText().trim().isEmpty()
        && !editAddress.getText().trim().isEmpty()
        && !editLevel.getText().trim().isEmpty();
    button.setEnabled(allFilled);
    }
    /**}
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
            java.util.logging.Logger.getLogger(AddEditStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddEditStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddEditStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddEditStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddEditStudent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addNew;
    private javax.swing.JButton delete;
    private javax.swing.JTextField editAddress;
    private javax.swing.JTextField editEmail;
    private javax.swing.JTextField editIC;
    private javax.swing.JTextField editLevel;
    private javax.swing.JTextField editName;
    private javax.swing.JTextField editPass;
    private javax.swing.JTextField editPhone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton recep_back;
    private javax.swing.JButton save;
    // End of variables declaration//GEN-END:variables
}
