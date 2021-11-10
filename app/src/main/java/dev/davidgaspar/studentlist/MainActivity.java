package dev.davidgaspar.studentlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MainActivity extends Activity {

    private LinearLayout container;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        container = findViewById(R.id.main_activity_container);

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

        for (int i = 0; i < students.size(); i++) {
            TextView studentView = new TextView(this);
            studentView.setText(students.get(i));
            container.addView(studentView);
        }
    }
}
