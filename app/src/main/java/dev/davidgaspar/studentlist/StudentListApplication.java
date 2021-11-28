package dev.davidgaspar.studentlist;

import android.app.Application;

import dev.davidgaspar.studentlist.model.Student;
import dev.davidgaspar.studentlist.repository.StudentRepo;

public class StudentListApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        createTestStudents();
    }

    private void createTestStudents() {
        StudentRepo studentRepo = new StudentRepo();

        for(int i = 0; i < 50; i++) {
            studentRepo.save(new Student(
                    "David Gaspar",
                    "48984596882",
                    "davidgaspar.dev@gmail.com"
            ));
            studentRepo.save(new Student(
                    "Elizabete Valda Corrêa",
                    "48984272584",
                    "betthcorrea@gmail.com"
            ));
            studentRepo.save(new Student(
                    "Smauel Gaspar",
                    "48984120118",
                    "gasparmoveis@gmail.com"
            ));
        }
    }
}
