package com.example.sqlitenoteapp;

public class NoteModel {
    String title , content ;
    int id ;

    public NoteModel() {

    }
    public NoteModel(int id, String content ,String title) {
        this.title = title;
        this.content = content;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

//    @Override
//    public String toString() {
//        return "NoteModel{" +
//                "title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                ", id=" + id +
//                '}';
//    }
}
