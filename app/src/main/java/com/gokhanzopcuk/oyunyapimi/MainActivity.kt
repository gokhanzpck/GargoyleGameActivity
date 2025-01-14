package com.gokhanzopcuk.oyunyapimi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gokhanzopcuk.oyunyapimi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.buttonBasla.setOnClickListener {
            val intent=Intent(this@MainActivity,OyunEkraniActivity::class.java)
            startActivity(intent)
        }
    }

}