package com.example.to_do_list;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    public interface OnTaskActionListener {
        void onDeleteClicked(Task task);
        void onCompletedToggled(Task task, boolean isChecked);
    }

    private List<Task> taskList;
    private OnTaskActionListener listener;

    public TaskAdapter(List<Task> taskList, OnTaskActionListener listener) {
        this.taskList = taskList;
        this.listener = listener;
    }
    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);

        holder.tvTaskName.setText(task.getTaskName());

        if (task.getNotes() == null || task.getNotes().isEmpty()) {
            holder.tvTaskNotes.setVisibility(View.GONE);
        } else {
            holder.tvTaskNotes.setVisibility(View.VISIBLE);
            holder.tvTaskNotes.setText(task.getNotes());
        }

        holder.cbCompleted.setOnCheckedChangeListener(null);
        holder.cbCompleted.setChecked(task.isCompleted());
        applyStrikeThrough(holder.tvTaskName, task.isCompleted());

        holder.cbCompleted.setOnCheckedChangeListener((buttonView, isChecked) -> {
            task.setCompleted(isChecked);
            applyStrikeThrough(holder.tvTaskName, isChecked);
            listener.onCompletedToggled(task, isChecked);
        });

        holder.btnDelete.setOnClickListener(v -> listener.onDeleteClicked(task));
    }

    private void applyStrikeThrough(TextView textView, boolean strike) {
        if (strike) {
            textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            textView.setPaintFlags(textView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView tvTaskName, tvTaskNotes;
        CheckBox cbCompleted;
        ImageButton btnDelete;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTaskName = itemView.findViewById(R.id.tvTaskName);
            tvTaskNotes = itemView.findViewById(R.id.tvTaskNotes);
            cbCompleted = itemView.findViewById(R.id.cbCompleted);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }


}