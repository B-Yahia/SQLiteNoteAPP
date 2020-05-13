package com.example.sqlitenoteapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

public class NoteListAdapter extends ArrayAdapter <NoteModel> {
    private Context context;
    int resource;


    public NoteListAdapter(@NonNull Context context, int resource, @NonNull List<NoteModel> objects) {
        super(context, resource, objects);
        this.context =context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final String title = getItem(position).getTitle();
        final String content = getItem(position).getContent();
        final int id = getItem(position).getId();

        //NoteModel noteModel = new NoteModel(id,content,title);

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource,parent,false);

        TextView vTitle = convertView.findViewById(R.id.view_title);
        TextView vContent = convertView.findViewById(R.id.view_content);
        TextView vId = convertView.findViewById(R.id.view_id);
        ConstraintLayout ml = convertView.findViewById(R.id.mainLayout);

        vContent.setText(content);
        vTitle.setText(title);
        vId.setText(Integer.toString(id));
        ml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent updateIntent = new Intent(context,NewNoteActivity.class);
                updateIntent.putExtra("id" , id);
                updateIntent.putExtra("title" , title);
                updateIntent.putExtra("content" , content);
                context.startActivity(updateIntent);
            }
        });

        return convertView;
    }
}
