package de.dhbw.mobapp.briefnote.view.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.dhbw.mobapp.briefnote.database.Note
import de.dhbw.mobapp.briefnote.database.NoteDatabase
import de.dhbw.mobapp.briefnote.database.NoteRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private lateinit var repository: NoteRepository // in reality dependency injection

    var noteText by mutableStateOf("")

    fun initialize(database: NoteDatabase) {
        repository = NoteRepository(database.noteDao)
    }

    fun insert() = viewModelScope.launch {
        repository.insert(Note(0, noteText)) // id 0 -> auto generated
        noteText = "";
    }
}