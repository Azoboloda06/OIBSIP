package com.example.to_do_list;

public class Task {
    private final int taskId;
    private final int userId;
    private final String taskName;
    private final String notes;
    private boolean completed;

    public Task(int taskId, int userId, String taskName, String notes, boolean completed) {
        this.taskId = taskId;
        this.userId = userId;
        this.taskName = taskName;
        this.notes = notes;
        this.completed = completed;
    }

    public int getTaskId() { return taskId; }
    public int getUserId() { return userId; }
    public String getTaskName() { return taskName; }
    public String getNotes() { return notes; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}
