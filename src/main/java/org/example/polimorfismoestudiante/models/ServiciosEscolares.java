package org.example.polimorfismoestudiante.models;

import java.util.ArrayList;

public class ServiciosEscolares {
    private ArrayList<IDataStudent> databases = new ArrayList<>();
    private Database01 database01;
    private Database02 database02;
    private Database03 database03;

    public ServiciosEscolares() {
        database01 = new Database01();
        database02 = new Database02();
        database03 = new Database03();

        databases.add(database01);
        databases.add(database02);
        databases.add(database03);
    }

    public void saveToDatabases(Student student) {
        for (IDataStudent database : databases) {
            database.saveStudent(student);
        }
    }
    public void updateInDatabases(Student modifiedStudent, Student originalStudent) {
        for (IDataStudent database : databases) {
            database.updateStudent(modifiedStudent);
        }
    }
    public ArrayList<Student> readFromDatabases() {
        ArrayList<Student> allStudents = new ArrayList<>();
        for (IDataStudent database : databases) {
            allStudents.addAll(database.readStudents());
        }
        return allStudents;
    }
}
