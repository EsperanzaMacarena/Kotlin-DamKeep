package com.escacena.damkeep

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import butterknife.BindView
import butterknife.ButterKnife
import com.escacena.damkeep.common.Constants
import com.escacena.damkeep.common.MyApp
import com.escacena.damkeep.viewmodel.NoteViewModel
import javax.inject.Inject

class NoteDetailActivity : AppCompatActivity() {
    @Inject
    lateinit var noteViewModel: NoteViewModel

    @BindView(R.id.detail_title)
    lateinit var title: TextView

    @BindView(R.id.detail_body)
    lateinit var body: TextView

    @BindView(R.id.detail_created)
    lateinit var created: TextView

    @BindView(R.id.detail_modified)
    lateinit var modified: TextView

    @BindView(R.id.detail_remove)
    lateinit var remove: Button

    @BindView(R.id.detail_edit)
    lateinit var edit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)
        (applicationContext as MyApp).getAppCompontent().inject(this)
        ButterKnife.bind(this)

        val id:String = intent.getStringExtra(Constants.INTENT_DETAIL_KEYWORD_ID)

        noteViewModel.getNote(id).observe(this, Observer {
            title.text = it.title
            body.text = it.body
            created.text = it.createdDate
            modified.text = it.editedOn
        })

        remove.setOnClickListener(View.OnClickListener {
            noteViewModel.removeNote(id)
            val list: Intent = Intent(MyApp.instance, HomeActivity::class.java).apply{
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(list)
            finish()
        })

        edit.setOnClickListener(View.OnClickListener {
            val edit: Intent = Intent(MyApp.instance, CreateEditNoteActivity::class.java).apply{
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                putExtra(Constants.INTENT_EDIT_KEYWORD,id)
            }
            startActivity(edit)
        })

    }
}
