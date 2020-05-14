package com.escacena.damkeep

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.escacena.damkeep.common.MyApp
import com.escacena.damkeep.model.Note
import com.escacena.damkeep.viewmodel.NoteViewModel
import javax.inject.Inject

class NoteFragment : Fragment() {
    @Inject
    lateinit var noteViewModel: NoteViewModel
    private lateinit var noteAdapter: MyNoteRecyclerViewAdapter
    private var notes: List<Note> = ArrayList()
    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as MyApp).getAppCompontent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_note_list, container, false)
        noteAdapter = MyNoteRecyclerViewAdapter()
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = noteAdapter
            }
        }

        noteViewModel.getAll().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                notes = it
                noteAdapter.setData(notes)
            }
        })
        return view
    }


}
