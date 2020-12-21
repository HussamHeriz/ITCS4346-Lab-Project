package hussamheriz.aug.todolistproject.Adapters;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import hussamheriz.aug.todolistproject.Models.Category;
import hussamheriz.aug.todolistproject.Models.Task;
import hussamheriz.aug.todolistproject.R;
import hussamheriz.aug.todolistproject.SampleData;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {

    private Task[] tasks;
    private Context context;
    private boolean withCategory;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView category;
        private final CheckBox checkbox;

        public ViewHolder(View view) {
            super(view);
            checkbox = view.findViewById(R.id.checkbox);
            category = view.findViewById(R.id.category);
        }

        public CheckBox getCheckbox() {
            return checkbox;
        }

        public TextView getCategory() {
            return category;
        }
    }

    public TasksAdapter(Context context, Task[] tasks, boolean withCategory) {
        this.tasks = tasks;
        this.context = context;
        this.withCategory = withCategory;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.single_task_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        CheckBox checkbox = viewHolder.getCheckbox();
        checkbox.setText(tasks[position].getTitle());

        TextView category = viewHolder.getCategory();
        if(withCategory) {
            int categoryId = tasks[position].getCategoryId();
            String categoryStr = SampleData.getCategories()[categoryId].getName();
            category.setText(categoryStr);
            category.setVisibility(View.VISIBLE);
        } else {
            category.setVisibility(View.GONE);
        }

        if(tasks[position].isDone()) {
            checkbox.setPaintFlags(checkbox.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            checkbox.setChecked(true);
        }

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    checkbox.setPaintFlags(checkbox.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    checkbox.setPaintFlags(checkbox.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return tasks.length;
    }
}