package de.dhbw.mobapp.briefnote.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// version increasen für automatisches update der DB -> Migration
@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    // register all daos here
    abstract val noteDao: NoteDao

    // companion für statische methoden
    companion object {
        @Volatile // immer vom speicher geholt werden, daher volatile
        private var INSTANCE: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDatabase::class.java,
                        "note_database",
                    ).fallbackToDestructiveMigration().build() // fallbackToDestructiveMigration gibt an, wenn version sich updated, dass dann alle Daten weg und DB neu aufgestzt werden
                }
                return instance
            }
        }
    }
}