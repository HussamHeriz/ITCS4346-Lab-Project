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

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {

    private Task[] tasks;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox checkbox;

        public ViewHolder(View view) {
            super(view);
            checkbox = (CheckBox) view.findViewById(R.id.checkbox);
        }

        public CheckBox getCheckbox() {
            return checkbox;
        }
    }

    public TasksAdapter(Context context, Task[] tasks) {
        this.tasks = tasks;
        this.context = context;
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