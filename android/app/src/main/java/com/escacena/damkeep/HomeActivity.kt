package com.escacena.damkeep

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.escacena.damkeep.common.Constants
import com.escacena.damkeep.common.MyApp
import com.escacena.damkeep.common.MySharedPreferencesManager

import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_logout, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.logout){
            MySharedPreferencesManager().removeStringValue(Constants.SHARED_PREFERENCES_TOKEN_KEYWORD)
            Log.d("REMOVE TOKEN",MySharedPreferencesManager().getSharedPreferences().getString(Constants.SHARED_PREFERENCES_TOKEN_KEYWORD,"nohay"))
            val exit :Intent = Intent(MyApp.instance, MainActivity::class.java).apply{
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            startActivity(exit)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        addNote.setOnClickListener { view ->
            val add: Intent = Intent(MyApp.instance, CreateEditNoteActivity::class.java).apply{
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(add)
            finish()

        }
    }

}
