package com.jspadda.noted.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.jspadda.noted.R
import com.jspadda.noted.databinding.ItemNotesBinding
import com.jspadda.noted.model.Notes
import com.jspadda.noted.ui.fragments.HomeFragmentDirections

class NotesAdapter(val requireContext: Context,val notesList: List<Notes>) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = notesList[position]
        holder.binding.notesTitle.text = note.title
        holder.binding.notesSubtitle.text = note.subTitle
        holder.binding.notesDate.text = note.date

        when(note.priority){
            "1" ->{
                holder.binding.noteViewPriority.setBackgroundResource(R.drawable.green_dot)
            }
            "2" ->{
                holder.binding.noteViewPriority.setBackgroundResource(R.drawable.yellow_dot)
            }
            "3" ->{
                holder.binding.noteViewPriority.setBackgroundResource(R.drawable.red_dot)
            }
        }

        holder.binding.root.setOnClickListener{

            val action = HomeFragmentDirections.actionHomeFragmentToUpdateNoteFragment(data)
            Navigation.findNavController(it).navigate(action)
        }

    }

    class NotesViewHolder(val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root) {



    }
}