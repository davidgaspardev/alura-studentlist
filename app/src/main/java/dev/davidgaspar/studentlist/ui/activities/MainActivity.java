package dev.davidgaspar.studentlist.ui.activities;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import dev.davidgaspar.studentlist.R;
import dev.davidgaspar.studentlist.helper.Repository;
import dev.davidgaspar.studentlist.model.Student;
import dev.davidgaspar.studentlist.repository.StudentRepo;

public class MainActivity extends Dactivity {
    private static final String LOG_TAG = "MainActivity";

    private ListView listView;
    private ArrayAdapter<Student> studentListAdapter;
    private Repository<Student> studentRepo = new StudentRepo();

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

        settingListAdapter();
        settingListOnItemClick();
        settingListOnItemLongClick();
    }

    private void settingListAdapter() {
        studentListAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1);
        listView.setAdapter(studentListAdapter);
    }

    private void settingListOnItemClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                openStudentFormInEditMode((Student) adapterView.getItemAtPosition(position));
            }
        });
    }

    private void openStudentFormInEditMode(Student student) {
        Intent intent = new Intent(MainActivity.this, StudentFormActivity.class);
        intent.putExtra(EXTRA_STUDENT, student);
        startActivity(intent);
    }

    private void settingListOnItemLongClick() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                int studentId = ((Student) adapterView.getItemAtPosition(position)).getId();
                studentRepo.delete(studentId);
                syncStudentList();
                return true;
            }
        });
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

        syncStudentList();
    }

    private void syncStudentList() {
        studentListAdapter.clear();
        studentListAdapter.addAll(StudentRepo.getAllStudents());
    }
}
