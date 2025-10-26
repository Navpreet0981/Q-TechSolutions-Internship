package com.examproctor.OnlineExamProctoringSystem;


public class CheatingAlert {
    private String student;
    private String alertType;
    private String timestamp;

    // Default constructor
    public CheatingAlert() { }

    // Parameterized constructor
    public CheatingAlert(String student, String alertType, String timestamp) {
        this.student = student;
        this.alertType = alertType;
        this.timestamp = timestamp;
    }

    // Getter and Setter for student
    public String getStudent() {
        return student;
    }
    public void setStudent(String student) {
        this.student = student;
    }

    // Getter and Setter for alertType
    public String getAlertType() {
        return alertType;
    }
    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    // Getter and Setter for timestamp
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
