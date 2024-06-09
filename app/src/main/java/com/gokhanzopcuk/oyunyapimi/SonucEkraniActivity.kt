package com.gokhanzopcuk.oyunyapimi

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gokhanzopcuk.oyunyapimi.databinding.ActivitySonucEkraniBinding

class SonucEkraniActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySonucEkraniBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySonucEkraniBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val skor=intent.getIntExtra("skor",0)
        binding.textView5.text=skor.toString()
         val sp=getSharedPreferences("Sonuc",Context.MODE_PRIVATE)
        val enYuksekSkor=sp.getInt("enYüksekSkor",0)
        if (skor>enYuksekSkor){
            val editor=sp.edit()
            editor.putInt("enYüksekSkor",skor)
            editor.commit()
            binding.ENYKSEKSKOR.text=skor.toString()
        }else{
            binding.ENYKSEKSKOR.text=enYuksekSkor.toString()
        }
        binding.buttonTekrarDene.setOnClickListener {
            val intent=Intent(this@SonucEkraniActivity,MainActivity::class.java)
            startActivity(intent)
        }
    }
}