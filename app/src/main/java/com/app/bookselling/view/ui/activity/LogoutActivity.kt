package com.app.bookselling.view.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.app.bookselling.R
import com.facebook.*
import com.facebook.AccessToken.*
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.GoogleApiClient

class LogoutActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.btn_logout -> {
                logoutFacebookApp()
                logoutGoogleApp()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)

        val btnLogout = findViewById<Button>(R.id.btn_logout)

        Toast.makeText(applicationContext,"Logout Screen", Toast.LENGTH_LONG).show()

        btnLogout.setOnClickListener(this)

    }

    private fun logoutFacebookApp() {
        Toast.makeText(applicationContext,"Button Logout", Toast.LENGTH_LONG).show()
        if (getCurrentAccessToken() != null) {
            GraphRequest(getCurrentAccessToken(), "me/permissions/", null, HttpMethod.DELETE, GraphRequest.Callback {
                setCurrentAccessToken(null)
                LoginManager.getInstance().logOut()
                finish()
            }).executeAsync()
            Toast.makeText(applicationContext,"OK", Toast.LENGTH_LONG).show()
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        }
    }

    private fun logoutGoogleApp() {
//        Auth.GoogleSignInApi.signOut()

    }

}
