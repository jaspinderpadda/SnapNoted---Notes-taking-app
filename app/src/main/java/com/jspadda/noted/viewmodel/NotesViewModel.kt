package com.jspadda.noted.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.jspadda.noted.model.NoteDatabase
import com.jspadda.noted.model.Notes
import com.jspadda.noted.repository.NotesRepository

class NotesViewModel(application: Application) :
    AndroidViewModel(application) {

    val repository: NotesRepository

        init {
            val dao = NoteDatabase.getDatabaseInstance(application).myNotesDao()
            repository = NotesRepository(dao)
        }

    fun addNotes(notes: Notes){
        repository.insertNotes(notes)
    }

    fun getAllNotes(): LiveData<List<Notes>> = repository.getAllNotes()

    fun deleteNotes(id: Int){
        repository.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        repository.updateNotes(notes)
    }

}