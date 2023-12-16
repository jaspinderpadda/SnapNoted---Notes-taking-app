package com.jspadda.noted.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun myNotesDao(): NotesDao

    companion object{
        @Volatile
        private var INSTANCE: NoteDatabase?= null

        fun getDatabaseInstance(context: Context): NoteDatabase{

            val tempInstance = INSTANCE
            if( tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val roomDatabaseInstance =
                    Room.databaseBuilder(context, NoteDatabase::class.java,
                        "Notes_db").allowMainThreadQueries().build()
                INSTANCE = roomDatabaseInstance
                return roomDatabaseInstance
            }
        }

    }

}