package hussamheriz.aug.todolistproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import hussamheriz.aug.todolistproject.Adapters.CategoriesAdapter;
import hussamheriz.aug.todolistproject.Adapters.TasksAdapter;
import hussamheriz.aug.todolistproject.Models.Category;
import hussamheriz.aug.todolistproject.Models.Task;

public class CategoriesActivity extends AppCompatActivity {

    EditText create,search;
    RecyclerView categories_rv;
    TextView lists, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        create = findViewById(R.id.create);
        categories_rv = findViewById(R.id.categories_rv);
        lists = findViewById(R.id.lists);
        search = findViewById(R.id.search);
        logout = findViewById(R.id.logout);

        /* Recycler View */
        categories_rv.setLayoutManager(new LinearLayoutManager(this));
        Category[] categories = SampleData.getCategories();
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(getApplicationContext(), categories);
        categories_rv.setAdapter(categoriesAdapter);

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
                    Toast.makeText(CategoriesActivity.this, "Entered a new Line", Toast.LENGTH_SHORT).show();
                    create.setText("");
                    // Hide KeyBoard
                    InputMethodManager imm = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        imm = (InputMethodManager) CategoriesActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    }
                    imm.hideSoftInputFromWindow(create.getWindowToken(),0);
                }
            }
        });

        /* search for task */
        search.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean isEmptySearch = search.getText().toString().isEmpty();
                showOrHideViews(isEmptySearch);
                if(!isEmptySearch){
                    Task[] tasksMatchSearch = SampleData.getSearchTasks(search.getText().toString().toLowerCase());
                    TasksAdapter tasksMatchSearchAdapter = new TasksAdapter(getApplicationContext(), tasksMatchSearch, !isEmptySearch);
                    categories_rv.setAdapter(tasksMatchSearchAdapter);
                } else {
                    Category[] categories = SampleData.getCategories();
                    CategoriesAdapter categoriesAdapter = new CategoriesAdapter(getApplicationContext(), categories);
                    categories_rv.setAdapter(categoriesAdapter);
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(CategoriesActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void showOrHideViews(boolean isShown) {
        if(isShown) {
            lists.setText("Lists:");
            create.setVisibility(View.VISIBLE);
        } else {
            lists.setText("Results:");
            create.setVisibility(View.GONE);
        }
    }
}