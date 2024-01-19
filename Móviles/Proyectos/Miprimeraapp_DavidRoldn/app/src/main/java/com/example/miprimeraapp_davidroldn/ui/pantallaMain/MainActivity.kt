package com.example.miprimeraapp_davidroldn.ui.pantallaMain

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.miprimeraapp_davidroldn.R
import com.example.miprimeraapp_davidroldn.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)

            textView.setText("Hola, escriba algo")
            setContentView(R.layout.activity_main)
            buttontoast.setOnClickListener(View.OnClickListener {
                if (!textField.text.isNullOrEmpty()) {
                    Toast.makeText(this@MainActivity, textField.text, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity, R.string.INTRODUZCAUNTEXTO, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}