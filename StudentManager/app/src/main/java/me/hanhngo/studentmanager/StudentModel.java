package me.hanhngo.studentmanager;

public class StudentModel {
    private int studentID;
    private String fullName;
    private String email;
    private String dob;

    public StudentModel(int studentID, String fullName, String email, String dob) {
        this.studentID = studentID;
        this.fullName = fullName;
        this.email = email;
        this.dob = dob;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
