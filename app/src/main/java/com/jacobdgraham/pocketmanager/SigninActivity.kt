package com.jacobdgraham.pocketmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.jacobdgraham.pocketmanager.databinding.ActivitySigninBinding

class SigninActivity : AppCompatActivity() {

    private lateinit var activitySigninBinding: ActivitySigninBinding

    /*
    When a user successfully logs into an account that is registered with PocketManager, we immediately want to redirect them to
    another activity. Preferably, the main activity will be launched as that is the application landing page.
     */
    private val activityLauncherForSignin = registerForActivityResult(FirebaseAuthUIActivityResultContract()) {
        signinActivityResponse -> this.onSignInResult(signinActivityResponse)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySigninBinding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(activitySigninBinding.root)

        val userLoginProviders = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
        )

        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder().setAvailableProviders(userLoginProviders).build()

        activityLauncherForSignin.launch(signInIntent)
    }

    private fun onSignInResult(firebaseUserAuthenticationResult: FirebaseAuthUIAuthenticationResult) {
        val firebaseUserSigninResponse = firebaseUserAuthenticationResult.idpResponse
        val firebaseUserSigninResultCode = firebaseUserAuthenticationResult.resultCode
        if (firebaseUserSigninResultCode == RESULT_OK) {
            val currentApplicationUser = FirebaseAuth.getInstance().currentUser
            val explicitMainActivityIntent = Intent(this, MainActivity::class.java)
            explicitMainActivityIntent.putExtra("currentApplicationUser", currentApplicationUser)
            startActivity(explicitMainActivityIntent)
        } else {
            Toast.makeText(this, "Your signin attempt has failed, please try again later, use different sign in credentials, or use another sign in method",
            Toast.LENGTH_LONG).show()
            startActivity(Intent(this, SigninActivity::class.java))
        }
    }
}