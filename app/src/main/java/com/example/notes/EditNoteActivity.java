package com.example.notes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.notes.db.NotesDB;
import com.example.notes.db.NotesDao;
import com.example.notes.model.Note;

import java.util.Date;

public class EditNoteActivity extends AppCompatActivity {



    private EditText inputNote;
    private NotesDao dao;
    private Note temp;
    public static final String NOTE_EXTRA_KEY = "note_id";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        inputNote = findViewById(R.id.input_note);
        dao = NotesDB.getInstance(this).notesDao();
        if(getIntent().getExtras() != null){

            int id = getIntent().getExtras().getInt(NOTE_EXTRA_KEY, 0);
            temp = dao.getNoteById(id);
            inputNote.setText(temp.getNoteText());
        }else {
            temp = new Note();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.edit_note_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.save_note){
            onSaveNote();
        }
        return super.onOptionsItemSelected(item);
    }
    private void onSaveNote(){

        String text = inputNote.getText().toString();
        if(!text.isEmpty()){
            long date = new Date().getTime();
            Note note = new Note(text, date);
            dao.insertNote(note);
            if(temp == null){
               temp = new Note(text,date);
                dao.insertNote(temp);
            }
            else{
               temp.setNoteText(text);
               temp.setNoteDate(date);
               dao.updateNote(temp);
            }
            finish();


        }

    }




}
