package com.example.notes.db;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.example.notes.model.Note;
import java.util.List;

@Dao
public interface NotesDao {

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    void insertNote(Note note);

    @Delete
    void deleteNote(Note note);

    @Update
    void updateNote(Note note);

    @Query("select * from notes")
    List<Note> getNotes();

    @Query("select * from notes where id = :noteId")
    Note getNoteById(int noteId);

    @Query("delete from notes where id = :noteId")
    void deletNote(int noteId);
}
