package miniproject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import miniproject.model.BinarySearchTree;
import miniproject.model.Student;

import java.util.Random;

public class MainController {

  @FXML
  private TextField tfName;
  @FXML
  private TextField tfCourse;
  @FXML
  private TextField tfMarks;
  @FXML
  private TextField tfCGPA;
  @FXML
  private TextField tfSearchRollNo;

  @FXML
  private TableView<Student> tvStudents;
  @FXML
  private TableColumn<Student, Integer> colRollNo;
  @FXML
  private TableColumn<Student, String> colName;
  @FXML
  private TableColumn<Student, String> colCourse;
  @FXML
  private TableColumn<Student, Double> colMarks;
  @FXML
  private TableColumn<Student, Double> colCGPA;

  private BinarySearchTree bst;
  private ObservableList<Student> studentList;
  private Random random;

  public MainController() {
    bst = new BinarySearchTree();
    studentList = FXCollections.observableArrayList();
    random = new Random();
  }

  @FXML
  public void initialize() {
    colRollNo.setCellValueFactory(new PropertyValueFactory<>("rollNumber"));
    colName.setCellValueFactory(new PropertyValueFactory<>("name"));
    colCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
    colMarks.setCellValueFactory(new PropertyValueFactory<>("totalMarks"));
    colCGPA.setCellValueFactory(new PropertyValueFactory<>("cgpa"));

    tvStudents.setItems(studentList);
  }

  @FXML
  private void handleAddStudent() {
    try {
      String name = tfName.getText();
      String course = tfCourse.getText();
      double marks = Double.parseDouble(tfMarks.getText());
      double cgpa = Double.parseDouble(tfCGPA.getText());

      if (name.isEmpty() || course.isEmpty()) {
        showAlert("Error", "Name and Course cannot be empty.");
        return;
      }

      int rollNumber = 1000 + random.nextInt(9000);

      Student newStudent = new Student(name, rollNumber, course, marks, cgpa);

      if (bst.insert(newStudent)) {
        refreshTable();
        handleClearForm();
        showAlert("Success", "Student added successfully! Roll No: " + rollNumber);
      } else {
        showAlert("Error", "Student with this Roll No already exists (Rare collision). Try again.");
      }

    } catch (NumberFormatException e) {
      showAlert("Error", "Marks and CGPA must be valid numbers.");
    }
  }

  @FXML
  private void handleDeleteStudent() {
    try {
      int rollNumber = Integer.parseInt(tfSearchRollNo.getText());
      if (bst.delete(rollNumber)) {
        refreshTable();
        tfSearchRollNo.clear();
        showAlert("Success", "Student record deleted.");
      } else {
        showAlert("Error", "Student not found.");
      }
    } catch (NumberFormatException e) {
      showAlert("Error", "Please enter a valid Roll Number.");
    }
  }

  @FXML
  private void handleSearchStudent() {
    try {
      int rollNumber = Integer.parseInt(tfSearchRollNo.getText());
      Student student = bst.search(rollNumber);

      if (student != null) {
        studentList.clear();
        studentList.add(student);
        showAlert("Found", "Student found: " + student.getName());
      } else {
        refreshTable();
        showAlert("Info", "Student not found.");
      }
    } catch (NumberFormatException e) {
      showAlert("Error", "Please enter a valid Roll Number.");
    }
  }

  @FXML
  private void handleShowAll() {
    refreshTable();
    tfSearchRollNo.clear();
  }

  @FXML
  private void handleClearForm() {
    tfName.clear();
    tfCourse.clear();
    tfMarks.clear();
    tfCGPA.clear();
  }

  private void refreshTable() {
    studentList.clear();
    studentList.addAll(bst.getAllStudents());
  }

  private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }
}
