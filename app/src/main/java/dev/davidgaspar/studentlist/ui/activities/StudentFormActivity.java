package dev.davidgaspar.studentlist.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import dev.davidgaspar.studentlist.R;
import dev.davidgaspar.studentlist.helper.Repository;
import dev.davidgaspar.studentlist.model.Student;
import dev.davidgaspar.studentlist.repository.StudentRepo;

public class StudentFormActivity extends AppActivity {
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

        loadStudent();

        setAppbarTitle();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_save) {
            onClickSave();
        }
        return super.onOptionsItemSelected(item);
    }

    private void onClickSave() {
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

    private void setAppbarTitle() {
        setTitle(student == null ? APPBAR_TITLE_NEW_STUDENT : APPBAR_TITLE_EDIT_STUDENT);
    }

    private void loadStudent() {
        Intent intent = getIntent();
        student = (Student) intent.getSerializableExtra(EXTRA_STUDENT);
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
