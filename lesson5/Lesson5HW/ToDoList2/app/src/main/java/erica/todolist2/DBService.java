package erica.todolist2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import layout.TaskDbSchema;

/**
 * Created by erica on 9/20/16.
 */
public class DBService {
    TaskDbSchema db;

    public DBService(Context context) {
        db = new TaskDbSchema(context);
    }

    public void addToDo(ToDo task) {
        SQLiteDatabase sql = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskDbSchema.NAME_TITLE, task.getTaskName());
        values.put(TaskDbSchema.STATUS_TITLE, task.getStatus());
        try {
            sql.insertOrThrow(TaskDbSchema.TABLE_NAME, TaskDbSchema.NAME_TITLE, values);
        } finally {
            sql.close();
        }
    }

    public void deleteToDo (ToDo task) {
        SQLiteDatabase sql = db.getWritableDatabase();
        String selection = TaskDbSchema.NAME_TITLE + "LIKE ?";
        String[] selectionArgs = {String.valueOf(task.getId())};
        sql.delete(TaskDbSchema.TABLE_NAME, selection, selectionArgs);
        sql.close();
    }

    public ArrayList<ToDo> getAll(ToDo task) {
        ArrayList<ToDo> taskArray = new ArrayList<>();
        SQLiteDatabase sql = db.getWritableDatabase();
        String[] projection = {
                TaskDbSchema.ID_TITLE,
                TaskDbSchema.NAME_TITLE,
                TaskDbSchema.STATUS_TITLE
        };

        String selection = TaskDbSchema.NAME_TITLE + " = ?";
        String[] selectionArgs = {String.valueOf(task.getId())};

        String sortOrder = TaskDbSchema.NAME_TITLE + " DESC";

        Cursor c = sql.query(
                TaskDbSchema.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        c.moveToLast();
        long itemID = c.getLong(c.getColumnIndexOrThrow(TaskDbSchema.ID_TITLE));
        c.moveToFirst();
        while(c.getLong(c.getColumnIndexOrThrow(TaskDbSchema.ID_TITLE)) < itemID) {
            long readID = c.getLong(0);
            String readName = c.getString(1);
            int readStatus = c.getInt(2);
            ToDo taskInput = new ToDo(readName, readStatus);
            taskArray.add(taskInput);

            c.moveToNext();
        }

        sql.close();

        return taskArray;
    }


}
