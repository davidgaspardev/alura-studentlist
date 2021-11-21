package dev.davidgaspar.studentlist.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import dev.davidgaspar.studentlist.R;
import dev.davidgaspar.studentlist.helper.Repository;
import dev.davidgaspar.studentlist.model.Student;
import dev.davidgaspar.studentlist.repository.StudentRepo;

public class StudentFormActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String[] APPBAR_TITLE = {
            "New student",
            "Edit student"
    };
    private Student student;

    final Repository<Student> repository = new StudentRepo();

    private EditText edtName;
    private EditText edtPhone;
    private EditText edtEmail;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_student);
        initEditsText();
        settingSaveButton();

        Intent intent = getIntent();
        student = (Student) intent.getSerializableExtra("student");
        if (student != null) {
            edtName.setText(student.getName());
            edtPhone.setText(student.getPhone());
            edtEmail.setText(student.getEmail());
        }

        setTitle(APPBAR_TITLE[student == null ? 0 : 1]);
    }

    private void initEditsText() {
        edtName = findViewById(R.id.edt_name);
        edtPhone = findViewById(R.id.edt_phone);
        edtEmail = findViewById(R.id.edt_email);
    }

    private void settingSaveButton() {
        Button btnSave = findViewById(R.id.btn_save_student);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        try {
            if (student != null){
                fillStudent();
                editStudent();
            } else {
                createStudent();
                saveStudent();
            }
            finish();
        } catch (Error e) {
            showMessage(e.getMessage());
        }
    }

    private void fillStudent() {
        student.setName(edtName.getText().toString());
        student.setPhone(edtPhone.getText().toString());
        student.setEmail(edtEmail.getText().toString());
    }

    private void createStudent() {
        String name = edtName.getText().toString();
        String phone = edtPhone.getText().toString();
        String email = edtEmail.getText().toString();

        if(name.length() < 2) {
            throw new Error("Student name is required");
        }

        student = new Student(name, phone, email);
    }

    private void saveStudent() {
        if(repository.alreadyExist(student)) {
            student = null;
            throw new Error("There is already a student with this data.");
        }
        repository.save(student);
    }

    private void showMessage(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
    }

    private void editStudent() {
        repository.edit(student);
    }
}
