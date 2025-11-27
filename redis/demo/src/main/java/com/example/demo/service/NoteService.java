package com.example.demo.service;

import com.example.demo.model.Note;

import java.util.List;

public interface NoteService {

    //save
     Note save(Note note);

    //get
     Note get(String id);

     //get All
    List<Note> getAll();

    //update
    Note update(String id , Note note);

    //delete
    void delete(String id);
}
