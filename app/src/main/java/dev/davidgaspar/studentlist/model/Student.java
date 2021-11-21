package dev.davidgaspar.studentlist.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

import dev.davidgaspar.studentlist.helper.Model;

public class Student extends Model implements Serializable {
    // Attributes
    private int id = 0;
    private String name;
    private String phone;
    private String email;

    public Student(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter methods
    public int getId() {
        return id;
    }
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