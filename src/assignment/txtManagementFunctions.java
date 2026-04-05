package assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class txtManagementFunctions {

    public List<String> findID(String targetID, String filePath) {
        List<String> rowData = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] columns = line.split("::");
                if (columns.length > 0) {
                    String id = columns[0].trim();
                    if (id.equals(targetID)) {
                        for (String column : columns) {
                            rowData.add(column.trim());
                        }
                        break;
                    }
                }
            }

            if (rowData.isEmpty()) {
                System.out.println("ID " + targetID + " not found in file.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return rowData;
    }

    public static List<String[]> openTxtFile(String filePath) {
        List<String[]> accountInfo = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] account = line.trim().split("::");
                accountInfo.add(account);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return accountInfo;
    }

    public static String generateID(List<String[]> txtData, String idType) {
        if (idType == null) {
            throw new IllegalArgumentException("Invalid id ");
        }

        int highestID = 0;

        for (String[] entry : txtData) {
            if (entry.length > 0) {
                String currentID = entry[0];
                if (currentID.length() >= 2) {
                    try {
                        int idNum = Integer.parseInt(currentID.substring(2));
                        if (idNum > highestID) {
                            highestID = idNum;
                        }
                    } catch (NumberFormatException e) {
                        // Ignore invalid IDs
                    }
                }
            }
        }

        int newID = highestID + 1;
        return String.format("%s%03d", idType, newID);
    }

    public void deleteID(String targetID, String filePath) {
        List<String> updatedLines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split("::");
                if (columns.length > 0) {
                    String id = columns[0].trim();
                    if (id.equals(targetID)) {
                        found = true;
                        continue;
                    }
                }
                updatedLines.add(line);
            }
            if (found) {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false))) {
                    for (String updatedLine : updatedLines) {
                        bw.write(updatedLine);
                        bw.newLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateUser(Users updatedUser) {
        String filename;

        String id = updatedUser.getId();

        if (id.startsWith("ST")) {
            filename = "students.txt";
        } else if (id.startsWith("TT")) {
            filename = "tutors.txt";
        } else if (id.startsWith("AD")) {
            filename = "admins.txt";
        } else if (id.startsWith("RP")) {
            filename = "receptionists.txt";
        } else {
            JOptionPane.showMessageDialog(null, "Unknown user role!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            List<String> lines = new ArrayList<>();
            boolean found = false;

            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("::");

                    if (parts[0].trim().equals(id)) {
                        String updatedLine = buildUserLine(updatedUser, filename);
                        lines.add(updatedLine);
                        found = true;
                    } else {
                        lines.add(line);
                    }
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(null, "User not found in file!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
                for (String l : lines) {
                    bw.write(l);
                    bw.newLine();
                }
            }

            JOptionPane.showMessageDialog(null, "Profile updated successfully!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error writing to file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static String buildUserLine(Users user, String filename) {
        return String.join("::",
                user.getId(),
                user.getPassword(),
                user.getName(),
                user.getEmail(),
                user.getPhone()
        );
    }

    public static void updateTutorClasses(String tutorID, List<String[]> classDataRows, String filePath) {
        try {
            List<String> allLines = new ArrayList<>();

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    // Skip lines specific tutor
                    if (!line.startsWith(tutorID + "::")) {
                        allLines.add(line);
                    }
                }
            }

            // Add new rows for this tutor
            for (String[] row : classDataRows) {
                StringBuilder sb = new StringBuilder();
                sb.append(tutorID);
                for (String field : row) {
                    sb.append("::").append(field);
                }
                allLines.add(sb.toString());
            }

            // Write back all lines to class.txt
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
                for (String line : allLines) {
                    bw.write(line);
                    bw.newLine();
                }
            }

            JOptionPane.showMessageDialog(null, "Classes saved successfully!");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving classes: " + e.getMessage());
        }
    }

    public static String generateID(File classFile) {
        int maxId = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(classFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("::");
                if (parts.length >= 2) {
                    String id = parts[1].trim();
                    if (id.startsWith("SJ")) {
                        int num = Integer.parseInt(id.substring(2));
                        if (num > maxId) {
                            maxId = num;
                        }
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return String.format("SJ%03d", maxId + 1);
    }
}
