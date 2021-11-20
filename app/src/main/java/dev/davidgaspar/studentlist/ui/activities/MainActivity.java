package dev.davidgaspar.studentlist.ui.activities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import dev.davidgaspar.studentlist.R;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Student List");
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.main_list_view);

        List<String> students = new ArrayList<>(Arrays.asList(
                "David Gaspar",
                "Luiz Gustavo",
                "Julia Herman",
                "Julia Eirrof",
                "Raul Zerefino",
                "Eugênio Sousa",
                "Ana Paula Martins",
                "Renan Silveira",
                "Jessica Sousa"
        ));

        listView.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                students));
    }
}
