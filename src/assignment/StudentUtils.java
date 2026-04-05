/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author weizh
 */
public class StudentUtils {
    public static Map<String, String> loadSubjectMap(String filename) {
        Map<String, String> subjectMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("::");
                if (parts.length >= 3) {
                    subjectMap.put(parts[1].trim(), parts[2].trim()); // code -> name
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading subjects.txt: " + e.getMessage());
        }
        return subjectMap;
    }
    
    public static List<Student> loadStudents(String studentFile, String subjectFile) {
        List<Student> students = new ArrayList<>();
        Map<String, String> subjectMap = loadSubjectMap(subjectFile);

        try (BufferedReader br = new BufferedReader(new FileReader(studentFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("::");
                if (parts.length >= 9) {
                    String id = parts[0];
                    String password = parts [1];
                    String name = parts[2];
                    String ic = parts[3];
                    String email = parts[4];
                    String phone = parts[5].trim();
                    String address = parts[6];
                    String level = parts[7];
                    String subjectsId = parts[8];

                    Student student = new Student(id, password, name, email, phone, ic, level, address);

                    // Translate subject codes to subject names
                    List<String> subjectNames = Arrays.stream(subjectsId.split(","))
                        .map(String::trim)
                        .map(code -> subjectMap.getOrDefault(code, code))
                        .collect(Collectors.toList());

                    student.getSubjects().addAll(subjectNames);
                    students.add(student);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading students.txt: " + e.getMessage());
        }

        return students;
    }
}
