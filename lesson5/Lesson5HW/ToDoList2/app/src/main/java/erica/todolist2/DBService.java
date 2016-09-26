package erica.todolist2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import layout.TaskDbSchema;


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
        String selection = TaskDbSchema.ID_TITLE + " =?";
        String[] selectionArgs = {String.valueOf(task.getId())};
        sql.delete(TaskDbSchema.TABLE_NAME, selection, selectionArgs);
        sql.close();
    }

    public ArrayList<ToDo> getAll() {
        ArrayList<ToDo> taskArray = new ArrayList<>();
        SQLiteDatabase sql = db.getReadableDatabase();


        Cursor c = sql.rawQuery("select * from " + TaskDbSchema.TABLE_NAME, null);

        c.moveToFirst();

        while(!c.isAfterLast()) {
            long readID = c.getLong(0);
            String readName = c.getString(1);
            int readStatus = c.getInt(2);
            ToDo taskInput = new ToDo(readName, readStatus);
            taskInput.setId(readID);
            taskArray.add(taskInput);

            c.moveToNext();
        }

        sql.close();
        return taskArray;
    }

    public ArrayList<ToDo> updateToDo(ToDo task) {
        ArrayList<ToDo> taskArray = new ArrayList<>();
        SQLiteDatabase sql = db.getWritableDatabase();

        StringBuilder sb = new StringBuilder();
        sb.append("update ");
        sb.append(TaskDbSchema.TABLE_NAME);
        sb.append(" set ");
        sb.append(TaskDbSchema.NAME_TITLE);
        sb.append("='");
        sb.append(task.getTaskName());
        sb.append("' and ");
        sb.append(TaskDbSchema.STATUS_TITLE);
        sb.append("=");
        sb.append(task.getStatus());
        sb.append(" where ");
        sb.append(TaskDbSchema.ID_TITLE);
        sb.append("=");
        sb.append(task.getId());

//        String query = "update " + TaskDbSchema.TABLE_NAME + "set " + TaskDbSchema.NAME_TITLE +  "= " + task.getTaskName()
//                + "and " + TaskDbSchema.STATUS_TITLE + "= " + task.getStatus() + "where " + TaskDbSchema.ID_TITLE + "= " + task.getId();
        sql.rawQuery(sb.toString(), null);



        return taskArray;
    }




}
