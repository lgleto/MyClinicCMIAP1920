package ipca.example.myclinic.ui.login

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import ipca.example.myclinic.MainActivity

import ipca.example.myclinic.R
import ipca.example.myclinic.VolleyHelper

class LoginActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.login)
        val loading = findViewById<ProgressBar>(R.id.loading)


        username.setText( "joao")
        password.setText("12345teste")
        loginButton.setOnClickListener {
            VolleyHelper.instance.userLogin(
                this@LoginActivity,
                username.text.toString(),
                password.text.toString()){
                if (it){
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LoginActivity, this@LoginActivity.getString(R.string.login_failed), Toast.LENGTH_LONG).show()
                }
            }
        }

    }


}
