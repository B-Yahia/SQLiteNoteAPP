package com.example.sqlitenoteapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewNoteActivity extends AppCompatActivity {
    private EditText title,content;
    private Button updateNote,deleteNote;

    DataBase dataBase ;

    String updatedContent,updatedID,updatedTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        updateNote = findViewById(R.id.update_note_btn);
        deleteNote = findViewById(R.id.delete_note);
        title= findViewById(R.id.note_title);
        content= findViewById(R.id.note_desc);
        getIntentData();

        updateNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBase = new DataBase(NewNoteActivity.this);
                dataBase.updateDATA(updatedID,updatedTitle,updatedContent);
            }
        });
        deleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog ();
            }
        });


    }
    void getIntentData (){
        if (getIntent().hasExtra("id")){
            Toast.makeText(this, "We found the ID ", Toast.LENGTH_SHORT).show();
            updatedID = getIntent().getStringExtra("id");
            updatedTitle = getIntent().getStringExtra("title");
            updatedContent = getIntent().getStringExtra("content");


            title.setText(updatedTitle);
            content.setText(updatedContent);


        }else {
            Toast.makeText(this, "We cant found the ID ", Toast.LENGTH_SHORT).show();
        }

    }
    void confirmDialog (){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to delete "+updatedTitle);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dataBase = new DataBase(NewNoteActivity.this);
                dataBase.deleteNote(updatedID);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}
