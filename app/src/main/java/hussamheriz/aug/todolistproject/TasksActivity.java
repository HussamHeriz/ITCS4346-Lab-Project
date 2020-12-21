package hussamheriz.aug.todolistproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
                Task[] tasksMatchSearch = getSearchTasks();
                TasksAdapter tasksMatchSearchAdapter = new TasksAdapter(getApplicationContext(), tasksMatchSearch, !isEmptySearch);
                tasks_rv.setAdapter(tasksMatchSearchAdapter);


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    private Task[] getSearchTasks() {

        Task[] allTasks = SampleData.getTasks();
        ArrayList<Task> tasksMatchSearch = new ArrayList<>();
        for(Task task: allTasks) {
            String newSearch = search.getText().toString().toLowerCase();
            if(task.getTitle().toLowerCase().contains(newSearch)) {
                tasksMatchSearch.add(task);
            }
        }

        Task[] tasksMatchSearchArray = new Task[tasksMatchSearch.size()];
        tasksMatchSearch.toArray(tasksMatchSearchArray);

        return tasksMatchSearchArray;

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