package dev.davidgaspar.studentlist.ui.activities;

import android.os.Bundle;
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

    final Repository<Student> repository = new StudentRepo();

    private EditText edtName;
    private EditText edtPhone;
    private EditText edtEmail;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_student);

        edtName = findViewById(R.id.edt_name);
        edtPhone = findViewById(R.id.edt_phone);
        edtEmail = findViewById(R.id.edt_email);

        Button btnSave = findViewById(R.id.btn_save_student);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_save_student) {
            String name = edtName.getText().toString();
            String phone = edtPhone.getText().toString();
            String email = edtEmail.getText().toString();

            Student student = new Student(name, phone, email);
            repository.save(student);
            finish();
        }
    }
}
