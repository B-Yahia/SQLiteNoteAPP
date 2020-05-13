package com.example.sqlitenoteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button add , delete_all;
    EditText title,content;
    ListView lv ;
    DataBase dataBase ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.add_btn);
        delete_all = findViewById(R.id.delete_all_btn);
        title= findViewById(R.id.titleView);
        content= findViewById(R.id.contentView);
        lv = findViewById(R.id.note_list);

        dataBase = new DataBase(MainActivity.this);
        displayNotes(dataBase);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NoteModel noteModel = new NoteModel( 1,title.getText().toString(),content.getText().toString());
                DataBase dataBase = new DataBase(MainActivity.this);
                boolean success = dataBase.addOne(noteModel);
                Toast.makeText(MainActivity.this, "Added"+success, Toast.LENGTH_SHORT).show();
                displayNotes(dataBase);

            }

        });
        delete_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBase.deleteAllNotes();
                displayNotes(dataBase);
            }
        });

        displayNotes(dataBase);


    }

    private void displayNotes(DataBase dataBase) {
        List<NoteModel> allNotes = dataBase.getAllNotes();
        NoteListAdapter adapter = new NoteListAdapter(this,R.layout.note_item,allNotes);
        lv.setAdapter(adapter);

    }

}
