package com.escacena.damkeep

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import butterknife.BindView
import butterknife.ButterKnife
import coil.api.load
import com.escacena.damkeep.api.request.LoginRequest
import com.escacena.damkeep.common.Constants
import com.escacena.damkeep.common.MyApp
import com.escacena.damkeep.common.MySharedPreferencesManager
import com.escacena.damkeep.viewmodel.UserViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var userViewModel: UserViewModel

    @BindView(R.id.login_username)
    lateinit var username: EditText

    @BindView(R.id.login_password)
    lateinit var password: EditText

    @BindView(R.id.login_button)
    lateinit var button: Button

    @BindView(R.id.login_join)
    lateinit var join: Button

    @BindView(R.id.logo)
    lateinit var logo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (applicationContext as MyApp).getAppCompontent().inject(this)
        ButterKnife.bind(this)

        val token: String? = MySharedPreferencesManager().getSharedPreferences()
            .getString(Constants.SHARED_PREFERENCES_TOKEN_KEYWORD, "")

        if (!token.isNullOrEmpty()) {
            val autologin: Intent = Intent(MyApp.instance, HomeActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(autologin)
            finish()
        }

        logo.load(
            Uri.parse("file:///android_asset/damkeep.png")
        )

        button.setOnClickListener(View.OnClickListener { v ->
            userViewModel.doLogin(
                LoginRequest(
                    username = username.text.toString(),
                    password = password.text.toString()
                )
            )
                .observe(this, Observer {
                   if(it==null){
                      Log.d("LOG IN","INCORRECTO")
                   }else{
                       Log.d("LOG IN","CORRECTO")
                       val success: Intent = Intent(MyApp.instance, HomeActivity::class.java).apply {
                           flags = Intent.FLAG_ACTIVITY_NEW_TASK
                       }
                       startActivity(success)
                       finish()
                   }
                })
        })

        join.setOnClickListener(View.OnClickListener {
            val register: Intent = Intent(MyApp.instance, RegisterActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(register)
            finish()
        })


    }


}
