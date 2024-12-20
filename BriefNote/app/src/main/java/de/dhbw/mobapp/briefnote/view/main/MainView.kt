package de.dhbw.mobapp.briefnote.view.main

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.dhbw.mobapp.briefnote.database.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(viewModel: MainViewModel) {
    val notes by viewModel.notes.collectAsState(initial = emptyList())

    Scaffold(
        topBar = { TopAppBar(title = { Text("BriefNoteCompose") }) }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            InputSection(
                noteText = viewModel.noteText,
                notes = notes,
                updateNoteText = { viewModel.noteText = it },
                onInertNote = viewModel::insert,
                onDeleteAll = viewModel::deleteAll
            )
            NoteList(notes = notes, onDeleteNote = viewModel::deleteNote)
        }
    }
}

@Composable
fun InputSection(
    noteText: String,
    notes: List<Note>,
    updateNoteText: (String) -> Unit,
    onInertNote: () -> Unit,
    onDeleteAll: () -> Unit
) {
    val buttonModifier = Modifier.padding(top = 10.dp)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = noteText,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { updateNoteText(it) })
        Button(
            modifier = buttonModifier,
            onClick = onInertNote
        ) {
            Text(text = "Neue Notiz hinzufügen")
        }
        AnimatedVisibility(visible = notes.isNotEmpty()) {
            Button(
                modifier = buttonModifier,
                onClick = onDeleteAll
            ) {
                Text(text = "Alle Notizen löschen")
            }
        }
    }
}

@Composable
fun NoteList(notes: List<Note>, onDeleteNote: (Note) -> Unit) {
    LazyColumn {
        items(notes) { note ->
            Text(text = note.note,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .clickable {
                        Log.i("BRIEFNOTE", "Auf Item ${note.note} wurde geclickt")
                        onDeleteNote(note)
                    }
                    .background(MaterialTheme.colorScheme.primary))
        }
    }
}