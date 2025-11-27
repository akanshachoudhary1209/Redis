package com.example.demo.service;

import com.example.demo.model.Note;
import com.example.demo.repository.NoteRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService{

    private final NoteRepo noteRepo;


    @CachePut( value = "notes" , key = "#note.id")
    @Override
    public Note save(Note note){
        note.setId(UUID.randomUUID().toString());
        return noteRepo.save(note);
    }

//name of the cache is value
    @Cacheable(value = "notes" , key = "#noteId")
    @Override
    public Note get(String noteId){
        return noteRepo.findById(noteId).orElseThrow(()-> new RuntimeException("Note with given Id not found"));
    }

    @Override
    public List<Note> getAll(){
        return noteRepo.findAll();
    }

    @Override
    public Note update(String noteId, Note note){
        Note note1 = noteRepo.findById(noteId).orElseThrow(() -> new RuntimeException("Note with given Id not found"));
        note1.setTitle(note.getTitle());
        note1.setContent(note.getContent());
        note1.setLive(note.isLive());

        return noteRepo.save(note1);
    }

    @CacheEvict(value = "notes", key = "#noteId")
    @Override
    public void delete(String noteId ){
        Note note1 = noteRepo.findById(noteId).orElseThrow(()-> new RuntimeException(("Note with given Id not found")));
        noteRepo.delete(note1);
    }
}

