package assignment;

// Subject.java

public class ClassInfo {
    private String tutorId;
    private String subjectId;
    private String subjectName;
    private String day;
    private String startTime;
    private String endTime;
    private double monthlyCost;

    public ClassInfo() {
    }

    public ClassInfo(String tutorId, String subjectId, String subjectName, String day, String startTime, String endTime, double monthlyCost) {
        this.tutorId = tutorId;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.monthlyCost = monthlyCost;
    }

    // Getters and Setters
    public String getTutorId() {
        return tutorId;
    }

    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public double getMonthlyCost() {
        return monthlyCost;
    }

    public void setMonthlyCost(double monthlyCost) {
        this.monthlyCost = monthlyCost;
    }

    @Override
    public String toString() {
        return tutorId + " :: " + subjectId + " :: " + subjectName + " :: " + day + " :: " + startTime + " :: " + endTime + " :: " + monthlyCost;
    }
} 