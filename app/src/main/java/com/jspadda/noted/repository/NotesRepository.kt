package com.jspadda.noted.repository

import androidx.lifecycle.LiveData
import com.jspadda.noted.model.Notes
import com.jspadda.noted.model.NotesDao

class NotesRepository(val dao: NotesDao) {

    fun getAllNotes(): LiveData<List<Notes>> {
        return dao.getNotes()
    }

    fun insertNotes(notes: Notes){
        dao.insertNotes(notes)
    }

    fun deleteNotes(id: Int){
        dao.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        dao.updateNotes(notes)
    }


}