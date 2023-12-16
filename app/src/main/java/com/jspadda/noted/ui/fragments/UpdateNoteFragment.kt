package com.jspadda.noted.ui.fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.jspadda.noted.R
import com.jspadda.noted.databinding.FragmentHomeBinding
import com.jspadda.noted.databinding.FragmentUpdateNoteBinding
import com.jspadda.noted.model.Notes
import com.jspadda.noted.viewmodel.NotesViewModel
import java.util.Date

class UpdateNoteFragment : Fragment() {

    val oldNotes by navArgs<UpdateNoteFragmentArgs>()
    lateinit var binding: FragmentUpdateNoteBinding
    val viewModel: NotesViewModel by viewModels()
    var priroty = "1"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpdateNoteBinding.inflate(layoutInflater, container, false )

        binding.etTitle.setText(oldNotes.data.title)
        binding.etSubTitle.setText(oldNotes.data.subTitle)
        binding.etNotes.setText(oldNotes.data.notes)

        when(oldNotes.data.priority){
            "1" ->{
                priroty = "1"
                binding.pGreen.setImageResource(R.drawable.done_logo)
                binding.pYellow.setImageResource(0)
                binding.pRed.setImageResource(0)
            }
            "2" ->{
                priroty = "2"
                binding.pGreen.setImageResource(0)
                binding.pYellow.setImageResource(R.drawable.done_logo)
                binding.pRed.setImageResource(0)
            }
            "3" ->{
                priroty = "3"
                binding.pGreen.setImageResource(0)
                binding.pYellow.setImageResource(0)
                binding.pRed.setImageResource(R.drawable.done_logo)
            }
        }
        binding.pGreen.setOnClickListener {
            priroty = "1"
            binding.pGreen.setImageResource(R.drawable.done_logo)
            binding.pYellow.setImageResource(0)
            binding.pRed.setImageResource(0)
        }

        binding.pYellow.setOnClickListener {
            priroty = "2"
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(R.drawable.done_logo)
            binding.pRed.setImageResource(0)
        }

        binding.pRed.setOnClickListener {
            priroty = "3"
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(0)
            binding.pRed.setImageResource(R.drawable.done_logo)
        }

        binding.btnUpdateNote.setOnClickListener{
            updateNotes(it)

        }

        return binding.root
    }

    private fun updateNotes(it: View?) {

        val title = binding.etTitle.text.toString()
        val subTitle = binding.etSubTitle.text.toString()
        val notes = binding.etNotes.text.toString()

        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)

        val note = Notes(oldNotes.data.id, title, subTitle, notes, notesDate.toString(), priroty)
        viewModel.updateNotes(note)

        Toast.makeText(requireActivity(), "Note updated successfully", Toast.LENGTH_LONG).show()

        Navigation.findNavController(it!!).navigate(R.id.action_updateNoteFragment_to_homeFragment)
    }

}