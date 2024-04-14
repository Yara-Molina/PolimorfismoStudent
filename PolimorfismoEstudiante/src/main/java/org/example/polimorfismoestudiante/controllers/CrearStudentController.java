package org.example.polimorfismoestudiante.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.polimorfismoestudiante.models.Database01;
import org.example.polimorfismoestudiante.models.Database02;
import org.example.polimorfismoestudiante.models.Database03;
import org.example.polimorfismoestudiante.models.IDataStudent;
import org.example.polimorfismoestudiante.models.Student;

import java.util.ArrayList;

public class CrearStudentController {
    @FXML
    private Button saveButton;

    @FXML
    private TextField textFieldApellido;

    @FXML
    private TextField textFieldEdad;

    @FXML
    private TextField textFieldID;

    @FXML
    private TextField textFieldnombre;

    private Student student;
    private ArrayList<IDataStudent> databases;

    public void initialize() {
        databases = new ArrayList<>();
        databases.add(new Database01());
        databases.add(new Database02());
        databases.add(new Database03());
    }

    public void initAttributes(Student student) {
        this.student = student;

        if (student != null) {
            textFieldnombre.setText(student.getNombre());
            textFieldApellido.setText(student.getApellido());
            textFieldID.setText(String.valueOf(student.getId()));
            textFieldEdad.setText(String.valueOf(student.getEdad()));
        }
    }

    @FXML
    void guardarAction(ActionEvent event) {
        try {
            String nombre = textFieldnombre.getText();
            String apellido = textFieldApellido.getText();
            int edad = Integer.parseInt(textFieldEdad.getText());
            int id = Integer.parseInt(textFieldID.getText());

            Student newStudent = new Student(nombre, apellido, id,edad);

            boolean exists = false;

            for (IDataStudent database : databases) {
                if (database.getStudents().contains(newStudent)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                if (student != null) {
                    student.setNombre(nombre);
                    student.setApellido(apellido);
                    student.setEdad(edad);
                    student.setId(id);
                    for (IDataStudent database : databases) {
                        database.updateStudent(student);
                    }
                } else {
                    student = newStudent;
                    for (IDataStudent database : databases) {
                        database.saveStudent(student);
                    }
                }
                closeWindow();
            } else {
                showErrorAlert("La tarea ya existe en una de las bases de datos.");
            }
        } catch (NumberFormatException e) {
            showErrorAlert("El ID debe ser un número entero.");
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public Student getStudent() {
        return student;
    }
}





