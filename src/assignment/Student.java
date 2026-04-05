/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author weizh
 */
public class Student extends Users {

    private String ic;
    private String address;
    private String level;
    private List<String> subjectsId;
    private List<String> subjects;
//    private double amountPaid;
//    private String paymentStatus;

    public Student(String id, String password, String name, String email, String phone, String ic, String level, String address) {
        super(id, password, name, email, phone);
        this.ic = ic;
        this.level = level;
        this.address = address;
        this.subjectsId = new ArrayList<>();
        this.subjects = new ArrayList<>();
    }

    public String getIc() {
        return ic;
    }

    public String getLevel() {
        return level;
    }

    public String getAddress() {
        return address;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getSubjectsId() {
        return subjectsId;
    }

    public void setSubjectsId(List<String> subjectsId) {
        this.subjectsId = subjectsId;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;    
    }
    
    
    public double getTotalFee() {
    double total = 0.0;

    try (BufferedReader br = new BufferedReader(new FileReader("subjects.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("::");
            if (parts.length >= 7) {
                String subjectCode = parts[1].trim();
                double fee = Double.parseDouble(parts[6].trim());

                if (this.subjectsId.contains(subjectCode)) {
                    total += fee;
                }
            }
        }
    } catch (IOException | NumberFormatException e) {
        System.err.println("Error reading subjects.txt in getTotalFee(): " + e.getMessage());
    }

    return total;
    }
    
    @Override
    public String toString() {
        return getId() + "::" + getPassword() + "::" + getName() + "::" + 
               getIc() + "::" + getEmail() + "::" + getPhone() + "::" + 
               getAddress() + "::" + getLevel();
    }
    
}    
