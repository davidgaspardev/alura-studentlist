package dev.davidgaspar.studentlist.ui.activities;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import dev.davidgaspar.studentlist.R;
import dev.davidgaspar.studentlist.model.Student;
import dev.davidgaspar.studentlist.repository.StudentRepo;

public class MainActivity extends Dactivity {
    private ListView listView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.app_name);
        setContentView(R.layout.activity_main);
        initListView();
        settingAddStudentButton();
    }

    private void initListView() {
        listView = findViewById(R.id.main_list_view);
    }

    private void settingAddStudentButton() {
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStudentFormInSaveMode();
            }
        });
    }

    private void openStudentFormInSaveMode() {
        Intent intent = new Intent(this, StudentFormActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadListStudent();
    }

    private void loadListStudent() {
        final ArrayList<Student> students = StudentRepo.getAllStudents();
        settingListAdapter(students);
        settingListOnItemClick();
    }

    private void settingListAdapter(ArrayList<Student> students) {
        listView.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                students));
    }

    private void settingListOnItemClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long id) {
                openStudentFormInEditMode(adapterView);
            }
        });
    }

    private void openStudentFormInEditMode(AdapterView<?> adapterView) {
        Intent intent = new Intent(MainActivity.this, StudentFormActivity.class);
        intent.putExtra(EXTRA_STUDENT, (Student) adapterView.getSelectedItem());
        startActivity(intent);
    }
}
