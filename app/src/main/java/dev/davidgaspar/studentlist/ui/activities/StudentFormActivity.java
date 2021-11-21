package dev.davidgaspar.studentlist.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import dev.davidgaspar.studentlist.R;
import dev.davidgaspar.studentlist.helper.Repository;
import dev.davidgaspar.studentlist.model.Student;
import dev.davidgaspar.studentlist.repository.StudentRepo;

public class StudentFormActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String APPBAR_TITLE = "New student";

    final Repository<Student> repository = new StudentRepo();

    private EditText edtName;
    private EditText edtPhone;
    private EditText edtEmail;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(APPBAR_TITLE);
        setContentView(R.layout.activity_form_student);
        initEditsText();
        settingSaveButton();

        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra("student");
        if (student != null) {
            edtName.setText(student.getName());
            edtPhone.setText(student.getPhone());
            edtEmail.setText(student.getEmail());
        }
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
        Student student = createStudent();
        saveStudent(student);
    }

    private Student createStudent() {
        String name = edtName.getText().toString();
        String phone = edtPhone.getText().toString();
        String email = edtEmail.getText().toString();

        return new Student(name, phone, email);
    }

    private void saveStudent(Student student) {
        repository.save(student);
        finish();
    }
}
