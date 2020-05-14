package com.escacena.damkeep

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import butterknife.BindView
import butterknife.ButterKnife
import com.escacena.damkeep.api.request.NoteCreateRequest
import com.escacena.damkeep.common.Constants
import com.escacena.damkeep.common.MyApp
import com.escacena.damkeep.viewmodel.NoteViewModel
import javax.inject.Inject

class CreateEditNoteActivity : AppCompatActivity() {
    @Inject
    lateinit var noteViewModel: NoteViewModel

    @BindView(R.id.create_edit_title)
    lateinit var title: EditText

    @BindView(R.id.create_edit_body)
    lateinit var body: EditText

    @BindView(R.id.create_edit_save)
    lateinit var save: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_edit_note)
        (applicationContext as MyApp).getAppCompontent().inject(this)
        ButterKnife.bind(this)

        val edit = intent.getStringExtra(Constants.INTENT_EDIT_KEYWORD)
        if (!edit.isNullOrEmpty()) {
            noteViewModel.getNote(edit).observe(this, Observer {
                title.setText(it.title)
                body.setText(it.body)
            })

            save.setOnClickListener(View.OnClickListener {
                noteViewModel.editNote(edit, NoteCreateRequest(title.text.toString(),body.text.toString())).observe(this,
                    Observer {
                        createIntent()
                    })
            })
        }else{
            save.setOnClickListener(View.OnClickListener {
                noteViewModel.createNote(NoteCreateRequest(title.text.toString(),body.text.toString())).observe(this,
                    Observer {
                        createIntent()
                    })
            })
        }


    }

    fun createIntent(){
        val list: Intent = Intent(MyApp.instance, HomeActivity::class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(list)
        finish()
    }
}
