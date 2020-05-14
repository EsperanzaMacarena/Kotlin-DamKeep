package com.escacena.damkeep


import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.escacena.damkeep.common.Constants
import com.escacena.damkeep.common.MyApp
import com.escacena.damkeep.model.Note
import kotlinx.android.synthetic.main.fragment_note.view.*

class MyNoteRecyclerViewAdapter() : RecyclerView.Adapter<MyNoteRecyclerViewAdapter.ViewHolder>() {
    private var notes: List<Note> = ArrayList()
    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Note
            Log.d("ID NOTA",item.id.toString())
            var intent = Intent (MyApp.instance, NoteDetailActivity::class.java).apply {
                putExtra(Constants.INTENT_DETAIL_KEYWORD_ID, item.id.toString())
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }

            MyApp.instance.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_note, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = notes[position]
        holder.title.text= item.title
        holder.body.text=item.body
        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = notes.size

    fun setData(notes: List<Note>?) {
        this.notes = notes!!
        notifyDataSetChanged()
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val title: TextView = mView.list_note_title
        val body: TextView = mView.list_note_body

    }
}
