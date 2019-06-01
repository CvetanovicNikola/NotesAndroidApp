package com.example.notes.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.notes.R;
import com.example.notes.callbacks.NoteEventListener;
import com.example.notes.model.Note;
import com.example.notes.utils.NoteUtils;

import java.util.ArrayList;

public class NotesAdapters extends RecyclerView.Adapter<NotesAdapters.NoteHolder>{

    private ArrayList<Note> notes;
    private Context context;
    private NoteEventListener listener;


    public NotesAdapters(Context context, ArrayList<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }


    @Override
    public NoteHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.note_layout, viewGroup, false);
        return new NoteHolder(v);
    }

    @Override
    public void onBindViewHolder( NoteHolder noteHolder, int position) {
        final Note note = getNote(position);
        if(note != null){
            noteHolder.noteText.setText(note.getNoteText());
            noteHolder.noteDate.setText(NoteUtils.dateFromLong(note.getNoteDate()));
            noteHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onNoteClick(note);
                }
            });
            noteHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onNoteLongClick(note);
                    return false;

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
    private Note getNote(int position){
        return notes.get(position);
    }
    class NoteHolder extends RecyclerView.ViewHolder{

       TextView noteText, noteDate;
        public NoteHolder(View itemView){
            super(itemView);
            noteDate = itemView.findViewById(R.id.note_date);
            noteText = itemView.findViewById(R.id.note_text);

        }

    }

    public void setListener(NoteEventListener listener) {
        this.listener = listener;
    }
}
