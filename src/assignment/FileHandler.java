package assignment;

// FileHandler.java

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    
    // Read all receptionists from file
    public static List<Receptionist> readReceptionists() {
        List<Receptionist> receptionists = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("receptionists.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("::");
                if (parts.length == 5) {
                    Receptionist receptionist = new Receptionist(
                        parts[0].trim(),
                        parts[1].trim(),
                        parts[2].trim(),
                        parts[3].trim(),
                        parts[4].trim()
                    );
                    receptionists.add(receptionist);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading receptionist.txt: " + e.getMessage());
        }
        return receptionists;
    }
    
    // Read all students from file
    public static List<Student> readStudents() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("::");
                if (parts.length == 8) {
                    Student student = new Student(
                        parts[0].trim(), // id
                        parts[1].trim(), // password
                        parts[2].trim(), // name
                        parts[4].trim(), // email
                        parts[5].trim(), // phone
                        parts[3].trim(), // ic
                        parts[7].trim(), // level
                        parts[6].trim()  // address
                    );
                    students.add(student);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading students.txt: " + e.getMessage());
        }
        return students;
    }
    
    // Read all subjects from file
    public static List<ClassInfo> readClasses() {
        List<ClassInfo> classes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("class.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("::");
                if (parts.length == 7) {
                    try {
                        ClassInfo classInfo = new ClassInfo(
                            parts[0].trim(), // tutor_id
                            parts[1].trim(), // subject_id
                            parts[2].trim(), // subject_name
                            parts[3].trim(), // day
                            parts[4].trim(), // start_time
                            parts[5].trim(), // end_time
                            Double.parseDouble(parts[6].trim()) // cost(monthly)
                        );
                        classes.add(classInfo);
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing cost for class: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading subject.txt: " + e.getMessage());
        }
        return classes;
    }
    
    // Read all enrollments from file
    public static List<Enrollment> readEnrollments() {
        List<Enrollment> enrollments = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("enrollment.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("::");
                if (parts.length >= 1) {
                    Enrollment enrollment = new Enrollment(
                        parts[0].trim(),
                        parts.length > 1 && !parts[1].trim().isEmpty() ? parts[1].trim() : null,
                        parts.length > 2 && !parts[2].trim().isEmpty() ? parts[2].trim() : null,
                        parts.length > 3 && !parts[3].trim().isEmpty() ? parts[3].trim() : null
                    );
                    enrollments.add(enrollment);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading enrollment.txt: " + e.getMessage());
        }
        return enrollments;
    }
    
    // Read all payments from file
    public static List<Payment> readPayments() {
        List<Payment> payments = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("payment.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("::");
                if (parts.length == 4) {
                    try {
                        Payment payment = new Payment(
                            parts[0].trim(),
                            parts[1].trim(),
                            Double.parseDouble(parts[2].trim()),
                            parts[3].trim()
                        );
                        payments.add(payment);
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing amount for payment: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading payment.txt: " + e.getMessage());
        }
        return payments;
    }
    
    // Append a new student to file
    public static boolean appendStudent(Student student) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("students.txt", true))) {
            pw.println(student.toString());
            return true;
        } catch (IOException e) {
            System.err.println("Error appending to students.txt: " + e.getMessage());
            return false;
        }
    }
    
    // Append a new enrollment to file
    public static boolean appendEnrollment(Enrollment enrollment) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("enrollment.txt", true))) {
            pw.println(enrollment.toString());
            return true;
        } catch (IOException e) {
            System.err.println("Error appending to enrollment.txt: " + e.getMessage());
            return false;
        }
    }
    
    // Append a new payment to file
    public static boolean appendPayment(Payment payment) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("payment.txt", true))) {
            pw.println(payment.toString());
            return true;
        } catch (IOException e) {
            System.err.println("Error appending to payment.txt: " + e.getMessage());
            return false;
        }
    }
    
    // Update enrollment file (overwrite entire file)
    public static boolean updateEnrollments(List<Enrollment> enrollments) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("enrollment.txt"))) {
            for (Enrollment enrollment : enrollments) {
                pw.println(enrollment.toString());
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error updating enrollment.txt: " + e.getMessage());
            return false;
        }
    }
    
    // Update student file (overwrite entire file)
    public static boolean updateStudents(List<Student> students) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("students.txt"))) {
            for (Student student : students) {
                pw.println(student.toString());
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error updating students.txt: " + e.getMessage());
            return false;
        }
    }
    
    // Update receptionist file (overwrite entire file)
    public static boolean updateReceptionists(List<Receptionist> receptionists) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("receptionists.txt"))) {
            for (Receptionist receptionist : receptionists) {
                pw.println(receptionist.toString());
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error updating receptionist.txt: " + e.getMessage());
            return false;
        }
    }
    
    // Generate next student ID
    public static String generateNextStudentId() {
        List<Student> students = readStudents();
        int maxId = 0;
        for (Student student : students) {
            String id = student.getId();
            if (id.startsWith("ST")) {
                try {
                    int num = Integer.parseInt(id.substring(2));
                    if (num > maxId) {
                        maxId = num;
                    }
                } catch (NumberFormatException e) {
                    // Ignore invalid IDs
                }
            }
        }
        return String.format("ST%03d", maxId + 1);
    }
    
    // Generate next payment ID
    public static String generateNextPaymentId() {
        List<Payment> payments = readPayments();
        int maxId = 0;
        for (Payment payment : payments) {
            String id = payment.getPaymentId();
            if (id.startsWith("PAY")) {
                try {
                    int num = Integer.parseInt(id.substring(3));
                    if (num > maxId) {
                        maxId = num;
                    }
                } catch (NumberFormatException e) {
                    // Ignore invalid IDs
                }
            }
        }
        return String.format("PAY%03d", maxId + 1);
    }
    
    // Check if file exists and create it if not
    public static void ensureFileExists(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Error creating file " + filename + ": " + e.getMessage());
            }
        }
    }
    
    // Initialize all required files
    public static void initializeFiles() {
        ensureFileExists("receptionists.txt");
        ensureFileExists("students.txt");
        ensureFileExists("class.txt");
        ensureFileExists("enrollment.txt");
        ensureFileExists("payment.txt");
    }
} 