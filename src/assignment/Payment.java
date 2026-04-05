package assignment;

// Payment.java

public class Payment {
    private String paymentId;
    private String studentId;
    private double amountPaid;
    private String dateOfPayment;

    public Payment() {
    }

    public Payment(String paymentId, String studentId, double amountPaid, String dateOfPayment) {
        this.paymentId = paymentId;
        this.studentId = studentId;
        this.amountPaid = amountPaid;
        this.dateOfPayment = dateOfPayment;
    }

    // Getters and Setters
    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(String dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    @Override
    public String toString() {
        return paymentId + " :: " + studentId + " :: " + amountPaid + " :: " + dateOfPayment;
    }
} 