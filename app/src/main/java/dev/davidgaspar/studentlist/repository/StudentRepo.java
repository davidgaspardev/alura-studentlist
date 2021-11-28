package dev.davidgaspar.studentlist.repository;

import java.util.ArrayList;

import dev.davidgaspar.studentlist.helper.Repository;
import dev.davidgaspar.studentlist.model.Student;

public class StudentRepo implements Repository<Student> {
    private static final ArrayList<Student> students = new ArrayList<>();
    private static int id = 1;

    public static ArrayList<Student> getAllStudents() {
        return new ArrayList<>(StudentRepo.students);
    }

    @Override
    public void save(Student student) {
        student.setId(id);
        students.add(student);
        id++;
    }

    @Override
    public void edit(Student studentEdited) {
        for (Student student : students) {
            if (student.getId() == studentEdited.getId()) {
                int index = students.indexOf(student);
                students.set(index, studentEdited);
            }
        }
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).getId() == id) {
                students.remove(i);
                return;
            }
        }
    }

    @Override
    public boolean alreadyExist(Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (isStudentEqual(student, students.get(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean isStudentEqual(Student studentA, Student studentB) {
        return studentA.getName().equals(studentB.getName())
                && studentA.getPhone().equals(studentB.getPhone())
                && studentA.getEmail().equals(studentB.getEmail());
    }
}
