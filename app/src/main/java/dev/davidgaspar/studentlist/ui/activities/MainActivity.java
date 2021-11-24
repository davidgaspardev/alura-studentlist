package dev.davidgaspar.studentlist.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import dev.davidgaspar.studentlist.R;
import dev.davidgaspar.studentlist.helper.Repository;
import dev.davidgaspar.studentlist.model.Student;
import dev.davidgaspar.studentlist.repository.StudentRepo;

public class MainActivity extends AppActivity {
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
        registerForContextMenu(listView);
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

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();
        Student student = studentListAdapter.getItem(menuInfo.position);

        if (student != null) {
            switch (item.getItemId()) {
                case R.id.menu_edit:
                    openStudentFormInEditMode(student);
                    break;
                case R.id.menu_remove:
                    removeStudent(student);
                    break;
            }
        }
        return super.onContextItemSelected(item);
    }

    private void removeStudent(Student student) {
        studentRepo.delete(student.getId());
        studentListAdapter.remove(student);
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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
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
