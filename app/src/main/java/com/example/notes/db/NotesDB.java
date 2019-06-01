package com.example.notes.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.notes.model.Note;

@Database(entities = Note.class, version = 1, exportSchema = false)
public abstract class NotesDB extends RoomDatabase {
    public abstract NotesDao notesDao();
    private static NotesDB instance;
    public static final String DATABASE_NAME = "notes_db";

    public static NotesDB getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context, NotesDB.class, DATABASE_NAME)
                    .allowMainThreadQueries().build();
        }
        return instance;
    }
}
