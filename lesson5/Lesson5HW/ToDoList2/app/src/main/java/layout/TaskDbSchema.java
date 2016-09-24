package layout;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TaskDbSchema extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Task.db";
    public static final String TABLE_NAME = "Tasks";
    public static final String ID_TITLE = "ID";
    public static final String NAME_TITLE = "Name";
    public static final String STATUS_TITLE = "Status";

    private static final String TEXT_TYPE = " TEXT";
    private static final String STATUS_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID_TITLE + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    STATUS_TITLE + STATUS_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static final int DATABASE_VERSION = 1;                  //set the database version; if the database schema is changed increment version by one

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }
    public TaskDbSchema(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION+1);
    }

}

