package hussamheriz.aug.todolistproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hussamheriz.aug.todolistproject.Adapters.CategoriesAdapter;
import hussamheriz.aug.todolistproject.Adapters.TasksAdapter;
import hussamheriz.aug.todolistproject.Models.Category;
import hussamheriz.aug.todolistproject.Models.Task;

public class TasksActivity extends AppCompatActivity {

    TextView category,delete;
    RecyclerView tasks_rv;
    EditText search, create;
    String mainCategoryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        tasks_rv = findViewById(R.id.tasks_rv);
        search = findViewById(R.id.search);
        category = findViewById(R.id.category);
        delete = findViewById(R.id.delete);
        create = findViewById(R.id.create);

        /* Maintain Main Category to Retrieve after search */
        mainCategoryText = category.getText().toString();

        /* Tasks RecyclerView */
        tasks_rv.setLayoutManager(new LinearLayoutManager(this));
        Task[] tasks = SampleData.getTasks();
        TasksAdapter tasksAdapter = new TasksAdapter(getApplicationContext(), tasks, false);
        tasks_rv.setAdapter(tasksAdapter);

        /* Search */
        search.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean isEmptySearch = search.getText().toString().isEmpty();
                showOrHideViews(isEmptySearch);
                Task[] tasksMatchSearch = SampleData.getSearchTasks(search.getText().toString().toLowerCase());
                TasksAdapter tasksMatchSearchAdapter = new TasksAdapter(getApplicationContext(), tasksMatchSearch, !isEmptySearch);
                tasks_rv.setAdapter(tasksMatchSearchAdapter);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        /* create a new category */
        create.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if( -1 != s.toString().indexOf("\n") ){
                    create.clearFocus();
                    Toast.makeText(TasksActivity.this, "Entered a new Line", Toast.LENGTH_SHORT).show();
                    create.setText("");
                    // Hide KeyBoard
                    InputMethodManager imm = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        imm = (InputMethodManager) TasksActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    }
                    imm.hideSoftInputFromWindow(create.getWindowToken(),0);
                }
            }
        });
    }

    private void showOrHideViews(boolean isShown) {
        int showOrHide = 0;
        if(isShown) {
            category.setText(mainCategoryText);
            showOrHide = View.VISIBLE;
        } else {
            category.setText("Results:");
            showOrHide = View.GONE;
        }
        delete.setVisibility(showOrHide);
        create.setVisibility(showOrHide);
    }
}