package assignment;

import java.awt.Color;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class Attendance extends javax.swing.JFrame {

    private Tutor tutor;

    /**
     * Creates new form Attendance
     */
    public Attendance(Tutor tutor) {
        this.tutor = tutor;
        initComponents();
        populateClassComboBox();

    }

    class AttendanceCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {

            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            Boolean present = (Boolean) table.getModel().getValueAt(row, 2);

            if (present != null && present) {
                c.setBackground(new Color(144, 238, 144));  // light green
            } else {
                c.setBackground(new Color(255, 182, 193));  // light red
            }

            if (isSelected) {
                c.setBackground(c.getBackground().darker());
            }

            return c;
        }
    }

    private void populateClassComboBox() {
        String tutorID = tutor.getId();
        List<String> tutorClasses = getTutorClasses(tutorID);

        classCodeBox.removeAllItems();

        // Add a placeholder at the top
        classCodeBox.addItem("-");

        // Then add actual classes
        for (String classID : tutorClasses) {
            classCodeBox.addItem(classID);
        }

        // Add listener to handle when tutor selects a different class
        classCodeBox.addActionListener(e -> {
            String selectedClass = (String) classCodeBox.getSelectedItem();
            if (selectedClass != null && !selectedClass.equals("-")) {
                loadClassDetails(selectedClass);
                loadStudentsForClass(selectedClass);
            } else {
                labelSubject.setText("");
                startTime.setText("");
                endTime.setText("");
                studentTable.setModel(new DefaultTableModel(
                        new Object[]{"Student ID", "Name", "Present?"}, 0
                ));
            }
        });
    }

    private List<String> getTutorClasses(String tutorID) {
        List<String> tutorClasses = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("class.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("::");
                if (parts[0].equals(tutorID)) {
                    tutorClasses.add(parts[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tutorClasses;
    }

    private void loadClassDetails(String classID) {
        try (BufferedReader reader = new BufferedReader(new FileReader("class.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("::");
                if (parts.length >= 6 && parts[1].equals(classID)) {
                    labelSubject.setText(parts[2]);
                    startTime.setText(parts[4]);
                    endTime.setText(parts[5]);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadStudentsForClass(String selectedClass) {
        List<String> studentIDs = new ArrayList<>();

        //Read enrollment.txt and find students
        try (BufferedReader reader = new BufferedReader(new FileReader("enrollment.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("::");
                String studentID = parts[0];
                for (int i = 1; i < parts.length; i++) {
                    if (parts[i].equals(selectedClass)) {
                        studentIDs.add(studentID);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Map StudentID and Name
        Map<String, String> idToName = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("::");
                String studentID = parts[0];
                String studentName = parts[2];
                idToName.put(studentID, studentName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Student ID", "Name", "Present?"}, 0
        ) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 2) {
                    return Boolean.class;
                }
                return String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;       // only allow editing the checkbox column
            }

        };

        // populate table with students
        for (String id : studentIDs) {
            String name = idToName.getOrDefault(id, "Unknown");
            model.addRow(new Object[]{id, name, false});
        }

        studentTable.setModel(model);
        studentTable.createDefaultColumnsFromModel();

        AttendanceCellRenderer renderer = new AttendanceCellRenderer();

        for (int i = 0; i < studentTable.getColumnCount(); i++) {
            if (i != 2) {
                studentTable.getColumnModel().getColumn(i).setCellRenderer(renderer);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        classCodeBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        labelSubject = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        studentTable = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        startTime = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        endTime = new javax.swing.JLabel();
        saveBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Attendance");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Class Code:");

        classCodeBox.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Subject:");

        labelSubject.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelSubject.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        studentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Name", "Present?"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        studentTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(studentTable);
        if (studentTable.getColumnModel().getColumnCount() > 0) {
            studentTable.getColumnModel().getColumn(0).setResizable(false);
            studentTable.getColumnModel().getColumn(1).setResizable(false);
            studentTable.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Time:");

        startTime.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        startTime.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        startTime.setText(" ");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("-");

        endTime.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        endTime.setText(" ");

        saveBtn.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        saveBtn.setText("Save");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        backBtn.setBackground(new java.awt.Color(255, 0, 0));
        backBtn.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(classCodeBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82)
                        .addComponent(saveBtn)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(backBtn)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(labelSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(98, 98, 98)
                                    .addComponent(startTime, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(270, 270, 270)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(endTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(60, 60, 60))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(backBtn))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(classCodeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(saveBtn)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(startTime)
                        .addComponent(jLabel7)
                        .addComponent(endTime)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        String classID = (String) classCodeBox.getSelectedItem();
        if (classID == null) {
            return;
        }

        DefaultTableModel model = (DefaultTableModel) studentTable.getModel();

        String today = LocalDate.now().format(DateTimeFormatter.ISO_DATE);

        // 👇 folder and file path
        String folderName = "attendance";
        String filename = folderName + "/attendance_" + classID + ".txt";

        // ✅ Ensure data folder exists
        java.io.File folder = new java.io.File(folderName);
        if (!folder.exists()) {
            folder.mkdir();
        }

        boolean alreadyRecorded = false;

        // Check for existing record
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("::");
                if (parts.length >= 2 && parts[0].equals(today) && parts[1].equals(classID)) {
                    alreadyRecorded = true;
                    break;
                }
            }
        } catch (java.io.FileNotFoundException fnfe) {
            // File not found = no previous attendance → OK
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (alreadyRecorded) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Attendance for this class and date has already been recorded.",
                    "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Save new attendance
        try (java.io.PrintWriter writer = new java.io.PrintWriter(
                new java.io.FileWriter(filename, true))) {

            for (int i = 0; i < model.getRowCount(); i++) {
                String studentID = model.getValueAt(i, 0).toString();
                String studentName = model.getValueAt(i, 1).toString();
                boolean present = (boolean) model.getValueAt(i, 2);

                writer.printf("%s::%s::%s::%s::%s%n",
                        today, classID, studentID, studentName, present ? "Present" : "Absent");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        javax.swing.JOptionPane.showMessageDialog(this, "Attendance saved for " + today);
    }//GEN-LAST:event_saveBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        TutorPortal tp = new TutorPortal(tutor);
        tp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

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
            java.util.logging.Logger.getLogger(Attendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Attendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Attendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Attendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JComboBox<String> classCodeBox;
    private javax.swing.JLabel endTime;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelSubject;
    private javax.swing.JButton saveBtn;
    private javax.swing.JLabel startTime;
    private javax.swing.JTable studentTable;
    // End of variables declaration//GEN-END:variables
}
