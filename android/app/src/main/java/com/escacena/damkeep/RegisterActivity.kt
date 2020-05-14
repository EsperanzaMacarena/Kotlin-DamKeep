package com.escacena.damkeep

import android.content.Intent
import android.media.Image
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.Observer
import butterknife.BindView
import butterknife.ButterKnife
import coil.api.load
import com.escacena.damkeep.api.request.LoginRequest
import com.escacena.damkeep.common.MyApp
import com.escacena.damkeep.viewmodel.UserViewModel
import javax.inject.Inject

class RegisterActivity : AppCompatActivity() {
    @Inject
    lateinit var userViewModel: UserViewModel

    @BindView(R.id.register_logo)
    lateinit var logo:ImageView

    @BindView(R.id.register_signup)
    lateinit var signup: Button

    @BindView(R.id.register_username)
    lateinit var username: EditText

    @BindView(R.id.register_password)
    lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        (applicationContext as MyApp).getAppCompontent().inject(this)
        ButterKnife.bind(this)

        logo.load(
            Uri.parse("file:///android_asset/damkeep.png")
        )

        signup.setOnClickListener(View.OnClickListener {
            userViewModel.signup(LoginRequest(username.text.toString(),password.text.toString())).observe(this, Observer {
                val login: Intent = Intent(MyApp.instance, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                startActivity(login)
                finish()
            })
        })
    }
}
