package com.example.notes.callbacks;

import com.example.notes.model.Note;

public interface NoteEventListener {

    public void onNoteClick(Note note);

    public void onNoteLongClick(Note note);
}
