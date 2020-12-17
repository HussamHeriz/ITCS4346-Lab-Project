package hussamheriz.aug.todolistproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import hussamheriz.aug.todolistproject.Adapters.CategoriesAdapter;
import hussamheriz.aug.todolistproject.Models.Category;

public class CategoriesActivity extends AppCompatActivity {

    RecyclerView categories_rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        categories_rv = findViewById(R.id.categories_rv);
        categories_rv.setLayoutManager(new LinearLayoutManager(this));

        Category[] categories = new Category[4];
        categories[0] = new Category("Home", 3);
        categories[1] = new Category("Personal", 3);
        categories[2] = new Category("Work", 3);
        categories[3] = new Category("University", 3);

        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(getApplicationContext(), categories);
        categories_rv.setAdapter(categoriesAdapter);
    }
}