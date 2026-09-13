package com.example.to_do_list;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TodoApp.db";
    private static final int DATABASE_VERSION = 1;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createUsersTable =
                "CREATE TABLE Users (" +
                        "userId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT, " +
                        "email TEXT UNIQUE, " +
                        "password TEXT)";

        String createTasksTable =
                "CREATE TABLE Tasks (" +
                        "taskId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "userId INTEGER, " +
                        "taskName TEXT, " +
                        "notes TEXT, " +
                        "completed INTEGER DEFAULT 0)";

        db.execSQL(createUsersTable);
        db.execSQL(createTasksTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Tasks");
        db.execSQL("DROP TABLE IF EXISTS Users");

        onCreate(db);
    }
    private String hashPassword(String password) {

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);

                if (hex.length() == 1) {
                    hexString.append('0');
                }

                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean registerUser(String name, String email, String password) {

        SQLiteDatabase db = this.getWritableDatabase();

        String hashedPassword = hashPassword(password);

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        values.put("password", hashedPassword);

        long result = db.insert("Users", null, values);

        return result != -1;
    }

    public int checkUser(String email, String password) {

        SQLiteDatabase db = this.getReadableDatabase();

        String hashedPassword = hashPassword(password);

        Cursor cursor = db.rawQuery(
                "SELECT userId FROM Users WHERE email = ? AND password = ?",
                new String[]{email, hashedPassword}
        );

        if (cursor.moveToFirst()) {
            int userId = cursor.getInt(0);
            cursor.close();
            return userId;
        }

        cursor.close();
        return -1;
    }

    public boolean addTask(int userId, String taskName, String notes) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("userId", userId);
        values.put("taskName", taskName);
        values.put("notes", notes);
        values.put("completed", 0);

        long result = db.insert("Tasks", null, values);

        return result != -1;
    }

    public Cursor getTasks(int userId) {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM Tasks WHERE userId = ?",
                new String[]{String.valueOf(userId)}
        );
    }

    public boolean updateTask(int taskId, boolean completed) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("completed", completed ? 1 : 0);

        int result = db.update(
                "Tasks",
                values,
                "taskId = ?",
                new String[]{String.valueOf(taskId)}
        );

        return result > 0;
    }

    public boolean deleteTask(int taskId) {

        SQLiteDatabase db = this.getWritableDatabase();

        int result = db.delete(
                "Tasks",
                "taskId = ?",
                new String[]{String.valueOf(taskId)}
        );

        return result > 0;
    }
    public String getUserName(int userId) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT name FROM Users WHERE userId = ?",
                new String[]{String.valueOf(userId)}
        );

        String name = "User"; // fallback default

        if (cursor.moveToFirst()) {
            name = cursor.getString(0);
        }

        cursor.close();
        return name;
    }


}
