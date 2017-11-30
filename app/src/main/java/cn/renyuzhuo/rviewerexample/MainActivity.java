package cn.renyuzhuo.rviewerexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import io.objectbox.Box;

public class MainActivity extends AppCompatActivity {

    TextView notes;
    Button addNote;
    EditText noteText;
    Box<Note> noteBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteBox = App.get().boxStore().boxFor(Note.class);

        notes = (TextView) findViewById(R.id.notes);
        addNote = (Button) findViewById(R.id.note_add);
        noteText = (EditText) findViewById(R.id.note_text);

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
                reloadNotes();
            }
        });

        reloadNotes();
    }

    private void addNote() {
        for (int i = 0; i < 100; i++) {
            Note note = new Note();
            note.setDate(new Date());
            note.setText(noteText.getText().toString());
            noteBox.put(note);
        }
        noteText.setText("");
    }

    private void reloadNotes() {
        this.notes.setText("");
        List<Note> notes = noteBox.getAll();
        for (Note note : notes) {
            this.notes.append(note.toString() + "\n");
        }
    }

}
