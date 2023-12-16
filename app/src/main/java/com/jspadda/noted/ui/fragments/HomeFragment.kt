package com.jspadda.noted.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.jspadda.noted.R
import com.jspadda.noted.databinding.FragmentHomeBinding
import com.jspadda.noted.ui.adapters.NotesAdapter
import com.jspadda.noted.viewmodel.NotesViewModel


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false )

        viewModel.getAllNotes().observe(viewLifecycleOwner,{ notesList ->
            binding.rvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rvAllNotes.adapter = NotesAdapter(requireContext(), notesList)
        } )


        binding.btnAddNotes.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_newNoteFragment)

        }
        return binding.root
    }

}