package de.dhbw.mobapp.briefnote.database

// in der Realität mit dependency injection und nicht im constructor
class NoteRepository(private val noteDao: NoteDao) {
    val allNotes = noteDao.getAllNotesSortedByName()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun deleteAll() {
        noteDao.deleteAll()
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }
}