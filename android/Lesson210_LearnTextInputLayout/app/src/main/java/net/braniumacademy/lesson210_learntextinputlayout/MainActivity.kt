package net.braniumacademy.lesson210_learntextinputlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editEmail = findViewById<TextInputEditText>(R.id.text_input_email)
        val editPassword = findViewById<TextInputEditText>(R.id.text_input_password)
        val btnLogin = findViewById<Button>(R.id.btn_login)

        btnLogin.setOnClickListener {
            Toast.makeText(
                this,
                "${editEmail.text.toString()}\n${editPassword.text.toString()}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}