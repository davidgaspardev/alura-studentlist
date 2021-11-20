package dev.davidgaspar.studentlist.repository;

import java.util.ArrayList;

import dev.davidgaspar.studentlist.helper.Repository;
import dev.davidgaspar.studentlist.model.Student;

public class StudentRepo implements Repository<Student> {

    private static ArrayList<Student> students = new ArrayList<>();

    public static ArrayList<Student> getAllStudents() {
        return new ArrayList<>(StudentRepo.students);
    }

    @Override
    public void save(Student student) {
        students.add(student);
    }
}
