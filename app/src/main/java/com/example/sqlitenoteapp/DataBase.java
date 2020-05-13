package com.example.sqlitenoteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.sql.SQLTransactionRollbackException;
import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {
    public static final String NOTE_APP = "NOTE_APP";
    public static final String ID = "ID";
    public static final String NOTE_TITLE = "NOTE_TITLE";
    public static final String NOTE_CONTENT = "NOTE_CONTENT";

    private Context context;

    public DataBase(@Nullable Context context) {
        super(context, "Note.db", null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + NOTE_APP + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + NOTE_TITLE + " TEXT , " + NOTE_CONTENT + " TEXT) ";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public  boolean addOne (NoteModel noteModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NOTE_TITLE, noteModel.getTitle());
        cv.put(NOTE_CONTENT, noteModel.getContent());

        long insert =db.insert(NOTE_APP,null,cv);
        if (insert == -1){
            return false;
        }else {
            return true;
        }
    }
    public void deleteNote (String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result =  db.delete(NOTE_APP , "ID=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Deleted  ", Toast.LENGTH_SHORT).show();
        }

    }
    public void deleteAllNotes (){
        SQLiteDatabase db = this.getWritableDatabase();
        long result =  db.delete(NOTE_APP , null, null);
        if (result == -1){
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "All notes are deleted  ", Toast.LENGTH_SHORT).show();
        }

    }


    public List<NoteModel> getAllNotes (){
        List<NoteModel> returnedList = new ArrayList<>();
        //Get data from database
        String queryString = " SELECT * FROM " + NOTE_APP ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String content = cursor.getString(2);

                NoteModel noteModel = new NoteModel(id ,title,content);
                returnedList.add(noteModel);

            }while (cursor.moveToNext());
        }else{

        }
        cursor.close();
        db.close();
        return returnedList;
    }

    void updateDATA (String row_id , String title , String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NOTE_TITLE, title);
        cv.put(NOTE_CONTENT, content);

        long result = db.update(NOTE_APP , cv , "ID=?", new String[]{row_id});

        if (result == -1){
            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated ", Toast.LENGTH_SHORT).show();
        }

    }
}
