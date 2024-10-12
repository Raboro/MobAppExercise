package de.dhbw.mobapp.briefnote.database

class NoteRepository(private val noteDao: NoteDao) {
    val allNotes = noteDao.getAllNotesSortedByName()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun deleteAll() {
        noteDao.deleteAll()
    }
}