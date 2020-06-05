package com.wwm.trackappsyncapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.amplifyframework.AmplifyException
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.api.aws.ApiAuthProviders
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.AWSDataStorePlugin
import com.wwm.trackappsyncapp.auth.AuthenticationServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val loginButton =
            findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {
            cpcLogin()
        }
    }

    private fun cpcLogin() {
        lifecycleScope.launch(Dispatchers.Main) {
            AuthenticationServiceImpl.login("sbstg2napp", "Happy123!")
            Timber.d("${AuthenticationServiceImpl.accessToken} ${AuthenticationServiceImpl.refreshToken}")
            if (AuthenticationServiceImpl.idToken == null) {
                Toast.makeText(this@LoginActivity, "Loging Fail", Toast.LENGTH_LONG).show()
                return@launch
            }

            try {
                val authProviders = ApiAuthProviders.builder().oidcAuthProvider(AuthenticationServiceImpl).build()
                Amplify.addPlugin(AWSDataStorePlugin())
                Amplify.addPlugin(AWSApiPlugin(authProviders)) // If using remote model synchronization
                Amplify.configure(applicationContext)
                Log.i("MyAmplifyApp", "Initialized Amplify")
            } catch (error: AmplifyException) {
                Log.e("MyAmplifyApp", "Could not initialize Amplify", error)
            }

            goToMainActivity(AuthenticationServiceImpl.idToken!!)
        }
    }

    private fun goToMainActivity(idToken: String) {
        val intent =
            Intent(this@LoginActivity, MainActivity::class.java)
        intent.putExtra(
            EXTRA_ID_TOKEN,
            idToken
        )
        startActivity(intent)
        finish()
    }

    companion object {
        const val EXTRA_ID_TOKEN = "ID_TOKEN"
    }
}