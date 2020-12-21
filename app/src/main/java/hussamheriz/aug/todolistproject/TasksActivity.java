package hussamheriz.aug.todolistproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import hussamheriz.aug.todolistproject.Adapters.CategoriesAdapter;
import hussamheriz.aug.todolistproject.Adapters.TasksAdapter;
import hussamheriz.aug.todolistproject.Models.Category;
import hussamheriz.aug.todolistproject.Models.Task;

public class TasksActivity extends AppCompatActivity {

    RecyclerView tasks_rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        tasks_rv = findViewById(R.id.tasks_rv);
        tasks_rv.setLayoutManager(new LinearLayoutManager(this));

        Task[] tasks = SampleData.getTasks();

        TasksAdapter tasksAdapter = new TasksAdapter(getApplicationContext(), tasks);
        tasks_rv.setAdapter(tasksAdapter);
    }
}