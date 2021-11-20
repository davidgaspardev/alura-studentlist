package dev.davidgaspar.studentlist.model;

import androidx.annotation.NonNull;

import dev.davidgaspar.studentlist.helper.Model;

public class Student extends Model {
    // Attributes
    private final String name;
    private final String phone;
    private final String email;

    public Student(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    // Getter methods
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }

    @NonNull
    @Override
    public String toString() {
        return getName();
    }
}