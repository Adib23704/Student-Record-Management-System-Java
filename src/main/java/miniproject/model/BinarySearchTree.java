package miniproject.model;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {

  private class Node {
    Student data;
    Node left;
    Node right;

    Node(Student data) {
      this.data = data;
      this.left = null;
      this.right = null;
    }
  }

  private Node root;

  public BinarySearchTree() {
    this.root = null;
  }

  public boolean insert(Student student) {
    if (search(student.getRollNumber()) != null) {
      return false;
    }
    root = insertRec(root, student);
    return true;
  }

  private Node insertRec(Node root, Student student) {
    if (root == null) {
      root = new Node(student);
      return root;
    }

    if (student.getRollNumber() < root.data.getRollNumber()) {
      root.left = insertRec(root.left, student);
    } else if (student.getRollNumber() > root.data.getRollNumber()) {
      root.right = insertRec(root.right, student);
    }

    return root;
  }

  public Student search(int rollNumber) {
    Node result = searchRec(root, rollNumber);
    return (result != null) ? result.data : null;
  }

  private Node searchRec(Node root, int rollNumber) {
    if (root == null || root.data.getRollNumber() == rollNumber) {
      return root;
    }

    if (rollNumber < root.data.getRollNumber()) {
      return searchRec(root.left, rollNumber);
    }

    return searchRec(root.right, rollNumber);
  }

  public boolean delete(int rollNumber) {
    if (search(rollNumber) == null) {
      return false;
    }
    root = deleteRec(root, rollNumber);
    return true;
  }

  private Node deleteRec(Node root, int rollNumber) {
    if (root == null) {
      return root;
    }

    if (rollNumber < root.data.getRollNumber()) {
      root.left = deleteRec(root.left, rollNumber);
    } else if (rollNumber > root.data.getRollNumber()) {
      root.right = deleteRec(root.right, rollNumber);
    } else {
      if (root.left == null) {
        return root.right;
      } else if (root.right == null) {
        return root.left;
      }

      root.data = minValue(root.right);

      root.right = deleteRec(root.right, root.data.getRollNumber());
    }

    return root;
  }

  private Student minValue(Node root) {
    Student minv = root.data;
    while (root.left != null) {
      minv = root.left.data;
      root = root.left;
    }
    return minv;
  }

  public List<Student> getAllStudents() {
    List<Student> students = new ArrayList<>();
    inOrderRec(root, students);
    return students;
  }

  private void inOrderRec(Node root, List<Student> students) {
    if (root != null) {
      inOrderRec(root.left, students);
      students.add(root.data);
      inOrderRec(root.right, students);
    }
  }
}
