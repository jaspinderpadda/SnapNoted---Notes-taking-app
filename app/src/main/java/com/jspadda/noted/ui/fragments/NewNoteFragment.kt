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
import com.jspadda.noted.R
import com.jspadda.noted.databinding.FragmentNewNoteBinding
import com.jspadda.noted.model.Notes
import com.jspadda.noted.viewmodel.NotesViewModel
import java.util.Date


class NewNoteFragment : Fragment() {

    lateinit var binding: FragmentNewNoteBinding
    var priroty = "1"
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewNoteBinding.inflate(layoutInflater, container, false )

        binding.pGreen.setImageResource(R.drawable.done_logo)

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


        binding.btnSaveNotes.setOnClickListener{
            createNotes(it)
        }

        return binding.root
    }

    private fun createNotes(it: View?) {
        val title = binding.etTitle.text.toString()
        val subTitle = binding.etSubTitle.text.toString()
        val notes = binding.etNotes.text.toString()

        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)

        val note = Notes(null, title, subTitle, notes, notesDate.toString(), priroty)
        viewModel.addNotes(note)

        Toast.makeText(requireActivity(), "Note created successfully", Toast.LENGTH_LONG).show()

        Navigation.findNavController(it!!).navigate(R.id.action_newNoteFragment_to_homeFragment)

    }

}