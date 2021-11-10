package dev.davidgaspar.studentlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

public class MainActivity extends Activity {

    private ListView listView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
