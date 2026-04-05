package assignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class ViewStudents extends javax.swing.JFrame {

    private Tutor tutor;

    /**
     * Creates new form ViewStudents
     */
    public ViewStudents(Tutor tutor) {
        this.tutor = tutor;
        initComponents();
        populateClassComboBox();
    }

    private void populateClassComboBox() {
        String tutorID = tutor.getId();
        List<String> tutorClasses = getTutorClasses(tutorID);

        classComboBox.removeAllItems();
        classComboBox.addItem("-");
        for (String classID : tutorClasses) {
            classComboBox.addItem(classID);
        }

        classComboBox.addActionListener(e -> {
            String selectedClass = (String) classComboBox.getSelectedItem();
            if (selectedClass != null && !selectedClass.equals("-")) {
                loadStudentRecords(selectedClass);
            } else {
                // clear the table
                studentsRecordTable.setModel(new DefaultTableModel(
                        new Object[]{"Student ID", "Name", "Attendance", "Attendance %"}, 0
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
                    tutorClasses.add(parts[1]); // ClassID
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tutorClasses;
    }

    private void loadStudentRecords(String classID) {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Student ID", "Name", "Attendance", "Attendance %"}, 0
        );

        Map<String, String> studentIdToName = new HashMap<>();
        List<String> enrolledStudents = new ArrayList<>();
        Map<String, Integer> attendanceCount = new HashMap<>();

        // 🔷 Read students.txt → map IDs to names
        try (BufferedReader br = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("::");
                if (parts.length >= 3) {
                    studentIdToName.put(parts[0], parts[2]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 🔷 Read enrollment.txt → find students enrolled in classID
        try (BufferedReader br = new BufferedReader(new FileReader("enrollment.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("::");
                String studentID = parts[0];
                for (int i = 1; i < parts.length; i++) {
                    if (parts[i].equals(classID)) {
                        enrolledStudents.add(studentID);
                        attendanceCount.put(studentID, 0); // init
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int totalClasses = 0;

        // 🔷 Read attendance file if exists
        String attendanceFile = "attendance/attendance_" + classID + ".txt";
        File attFile = new File(attendanceFile);
        if (attFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(attendanceFile))) {
                String line;
                Set<String> datesSeen = new HashSet<>();
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("::");
                    if (parts.length >= 5) {
                        String date = parts[0];
                        String studentID = parts[2];
                        String status = parts[4];

                        if (!datesSeen.contains(date)) {
                            totalClasses++;
                            datesSeen.add(date);
                        }

                        if (status.equalsIgnoreCase("Present")) {
                            attendanceCount.put(studentID, attendanceCount.getOrDefault(studentID, 0) + 1);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "No attendance record found for class " + classID + ".\nAll students are at 0 attendance.",
                    "No Attendance",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }

        // 🔷 Populate table
        for (String studentID : enrolledStudents) {
            String name = studentIdToName.getOrDefault(studentID, "Unknown");
            int attended = attendanceCount.getOrDefault(studentID, 0);
            String ratio = attended + "/" + (totalClasses > 0 ? totalClasses : 0);
            String percent = totalClasses > 0
                    ? String.format("%.1f%%", (attended * 100.0 / totalClasses))
                    : "0%";

            model.addRow(new Object[]{studentID, name, ratio, percent});
        }

        studentsRecordTable.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        classComboBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        studentsRecordTable = new javax.swing.JTable();
        backBtn = new javax.swing.JButton();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Student List");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Class Code:");

        classComboBox.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        studentsRecordTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Student ID", "Name", "Attendance", "Percentage"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(studentsRecordTable);

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(jLabel3)
                        .addGap(27, 27, 27)
                        .addComponent(classComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(backBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(165, 165, 165))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(backBtn))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(classComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ViewStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JComboBox<String> classComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable studentsRecordTable;
    // End of variables declaration//GEN-END:variables
}
