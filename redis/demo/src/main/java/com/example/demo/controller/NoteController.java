package com.example.demo.controller;

import com.example.demo.model.Note;
import com.example.demo.service.NoteService;
import com.example.demo.service.NoteServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/create")
    public ResponseEntity<Note> create(@RequestBody Note note){
        return ResponseEntity.status(HttpStatus.CREATED).body(noteService.save(note));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Note>> getAll(){
        return ResponseEntity.ok(noteService.getAll());
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<Note> getSingle(@PathVariable String noteId){
        return ResponseEntity.ok(noteService.get(noteId));
    }


}
