package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;

class SQL extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "COMP4200.db";
    private static final int DATABASE_VERSION = 1;

    private static final String table = "todo";
    private static final String s_no = "id";
    private static final String task = "task";
    private static final String desc = "desciption";
    private static final String dueby = "dueby";
    private static final String priority = "priority";

    SQL(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + table +
                " (" + s_no + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                task + " TEXT, " +
                desc + " TEXT, " +
                priority + " INTEGER, " +
                dueby + " TEXT);";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + table);
        onCreate(db);
    }

    void addTask(String t, String d, int p, String dt){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(task, t);
        cv.put(desc, d);
        cv.put(priority, p);
        cv.put(dueby, dt);
        long result = db.insert(table,null, cv);
        if(result == -1){
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Task added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + table;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String Tasku, String Descu, String prioru, String dateu){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(task, Tasku);
        cv.put(desc, Descu);
        cv.put(priority, prioru);
        cv.put(dueby, dateu);

        long result = db.update(table, cv, "task LIKE ?", new String[]{task});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRow(String task){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(table, "task LIKE ?", new String[]{task});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Congratulations!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + table);
    }

}
