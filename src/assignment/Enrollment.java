package assignment;

// Enrollment.java

public class Enrollment {
    private String studentId;
    private String subjectId1;
    private String subjectId2;
    private String subjectId3;

    public Enrollment() {
    }

    public Enrollment(String studentId, String subjectId1, String subjectId2, String subjectId3) {
        this.studentId = studentId;
        this.subjectId1 = subjectId1;
        this.subjectId2 = subjectId2;
        this.subjectId3 = subjectId3;
    }

    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSubjectId1() {
        return subjectId1;
    }

    public void setSubjectId1(String subjectId1) {
        this.subjectId1 = subjectId1;
    }

    public String getSubjectId2() {
        return subjectId2;
    }

    public void setSubjectId2(String subjectId2) {
        this.subjectId2 = subjectId2;
    }

    public String getSubjectId3() {
        return subjectId3;
    }

    public void setSubjectId3(String subjectId3) {
        this.subjectId3 = subjectId3;
    }

    @Override
    public String toString() {
        return studentId + "::" + 
               (subjectId1 != null ? subjectId1 : "") + "::" + 
               (subjectId2 != null ? subjectId2 : "") + "::" + 
               (subjectId3 != null ? subjectId3 : "");
    }
} 