package miniproject.model;

public class Student {
  private String name;
  private int rollNumber;
  private String course;
  private double totalMarks;
  private double cgpa;

  public Student(String name, int rollNumber, String course, double totalMarks, double cgpa) {
    this.name = name;
    this.rollNumber = rollNumber;
    this.course = course;
    this.totalMarks = totalMarks;
    this.cgpa = cgpa;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getRollNumber() {
    return rollNumber;
  }

  public void setRollNumber(int rollNumber) {
    this.rollNumber = rollNumber;
  }

  public String getCourse() {
    return course;
  }

  public void setCourse(String course) {
    this.course = course;
  }

  public double getTotalMarks() {
    return totalMarks;
  }

  public void setTotalMarks(double totalMarks) {
    this.totalMarks = totalMarks;
  }

  public double getCgpa() {
    return cgpa;
  }

  public void setCgpa(double cgpa) {
    this.cgpa = cgpa;
  }
}
