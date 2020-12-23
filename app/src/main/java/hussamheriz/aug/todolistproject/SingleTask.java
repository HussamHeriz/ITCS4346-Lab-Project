package hussamheriz.aug.todolistproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SingleTask extends AppCompatActivity {

    TextView title_txt, description_txt;
    EditText title_edt, description_edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);

        title_txt = findViewById(R.id.title_txt);
        description_txt = findViewById(R.id.description_txt);
        title_edt = findViewById(R.id.title_edt);
        description_edt = findViewById(R.id.description_edt);

        title_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title_txt.setVisibility(View.GONE);
                title_edt.setVisibility(View.VISIBLE);
                title_edt.requestFocus();
            }
        });

        description_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                description_txt.setVisibility(View.GONE);
                description_edt.setVisibility(View.VISIBLE);
                description_edt.requestFocus();
            }
        });
    }
}