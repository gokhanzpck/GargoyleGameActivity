package com.gokhanzopcuk.oyunyapimi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView.SmoothScroller.Action
import com.gokhanzopcuk.oyunyapimi.databinding.ActivityMainBinding
import com.gokhanzopcuk.oyunyapimi.databinding.ActivityOyunEkraniBinding
import java.util.Timer
import kotlin.concurrent.schedule
import kotlin.math.floor

class OyunEkraniActivity : AppCompatActivity() {
     private lateinit var binding: ActivityOyunEkraniBinding
     private var anakarakterX=0.0f
     private var anakarakterY=0.0f
    private var dokunmaKontrl=false
    private var baslangicKontrol=false
    private val timer=Timer()
    private var ekranGenisligi=0
    private var ekranYuksekligi=0
    private var anaKarakterGenisligi=0
    private var anaKarakterYuksekligi=0
    private var siyahKareX=0.0f
    private var siyahKareY=0.0f
    private var sariDaireX=0.0f
    private var sariDaireY=0.0f
    private var kirmiziUcgenX=0.0f
    private var kirmiziUcgenY=0.0f
    private var skor=0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOyunEkraniBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.siyahKare.x=-800.0f
        binding.siyahKare.y=-800.0f
        binding.sarDaire.x=-800.0f
        binding.sarDaire.y=-800.0f
        binding.kirmiziUcgen.x=-800.0f
        binding.kirmiziUcgen.y=-800.0f
        //burda görsellerin ilk acıldıgında ekranın dışına taşıdık




      binding.cl.setOnTouchListener(object :View.OnTouchListener{
          override fun onTouch(v: View?, event: MotionEvent?): Boolean {
              if (baslangicKontrol){
                  if (event?.action==MotionEvent.ACTION_DOWN){
                      Log.e("MotionEvent","ACTİON_DOWN : eKRANA DOKUNDU")
                      //ekrana dokundugumuzda burda işlem yapacaz
                      dokunmaKontrl=true
                  }
                  if (event?.action==MotionEvent.ACTION_UP){
                      Log.e("MotionEvent","ACTİON_UP : eKRANI BIRAKTI")
                      //ekranı bıraktıgımızda burda işlem yapacagız
                      dokunmaKontrl=false
                  }
              }else{
                  binding.textOyunBasla.visibility=View.INVISIBLE
                  baslangicKontrol=true

                  anaKarakterGenisligi=binding.anakarakter.width
                  anaKarakterYuksekligi=binding.anakarakter.height
                  ekranGenisligi=binding.cl.width
                  ekranYuksekligi=binding.cl.height

                  anakarakterX=binding.anakarakter.x
                  anakarakterY=binding.anakarakter.y
                  timer.schedule(0,20){
                      //detay gecikme period kac sn bi calışacagı
                      Handler(Looper.getMainLooper()).post{
                          //android sayfasında ki görsellerde degişik yapmamıza yardımcı oluyor handler

                       anakarakterHareketEttirme()
                          cisimleriHareketEttirme()
                          carpismaKontrol()

                      }
                  }
              }
              return true
          }
      })


    }
    fun anakarakterHareketEttirme(){
val anakarakterHiz=ekranYuksekligi/60.0f

        if (dokunmaKontrl){
            anakarakterY-=anakarakterHiz
        }else{
            anakarakterY+=anakarakterHiz
        }
        if (anakarakterY<=0.0f){
            anakarakterY=0.0f
        }
        if (anakarakterY>=ekranYuksekligi-anaKarakterYuksekligi){
            anakarakterY=(ekranYuksekligi-anaKarakterYuksekligi).toFloat()

        }
        binding.anakarakter.y=anakarakterY



    }
    fun cisimleriHareketEttirme(){
        siyahKareX-=ekranGenisligi/44.0f
        sariDaireX-=ekranGenisligi/54.0f
        kirmiziUcgenX-=ekranGenisligi/36.0f
        //hızını burda ayarladık
        if (siyahKareX<0.0f){
            //siyahkare en başa geldiginde sona attık tekrardan
            siyahKareX=ekranGenisligi+20.0f
            siyahKareY= floor(Math.random()*ekranYuksekligi).toFloat()
            //siyah kareye random bir yükseklik verdik


        }
        binding.siyahKare.x=siyahKareX
        binding.siyahKare.y=siyahKareY
       if (sariDaireX<0.0f){
           sariDaireX=ekranGenisligi+20.0f
           sariDaireY= floor(Math.random()*ekranYuksekligi).toFloat()

       }
        binding.sarDaire.x=sariDaireX
        binding.sarDaire.y=sariDaireY
        if (kirmiziUcgenX<0.0f){
            kirmiziUcgenX=ekranGenisligi+20.0f
            kirmiziUcgenY= floor(Math.random()*ekranYuksekligi).toFloat()

        }
        binding.kirmiziUcgen.x=kirmiziUcgenX
        binding.kirmiziUcgen.y=kirmiziUcgenY

    }
    fun carpismaKontrol(){
        val sariDaireMerkezX=sariDaireX+binding.sarDaire.width/2.0f
        val sariDaireMerkezY=sariDaireY+binding.sarDaire.height/2.0f
        if(0.0f<=sariDaireMerkezX && sariDaireMerkezX<=anaKarakterGenisligi
            && anakarakterY<=sariDaireMerkezY && sariDaireMerkezY<=anakarakterY+anaKarakterYuksekligi){
          skor+=20
            sariDaireX=-10.0f
        }
        val kirmiziUcgenMerkezX=kirmiziUcgenX+binding.kirmiziUcgen.width/2.0f
        val kirmiziUcgenMerkezY=kirmiziUcgenY+binding.kirmiziUcgen.height/2.0f
        if(0.0f<=kirmiziUcgenMerkezX && kirmiziUcgenMerkezX<=anaKarakterGenisligi
            && anakarakterY<=kirmiziUcgenMerkezY && kirmiziUcgenMerkezY<=anakarakterY+anaKarakterYuksekligi){
            skor+=50
            kirmiziUcgenX=-10.0f
        }
        val siyahKareMerkezX=siyahKareX+binding.siyahKare.width/2.0f
        val siyahKareMerkezY=siyahKareY+binding.siyahKare.height/2.0f
        if(0.0f<=siyahKareMerkezX && siyahKareMerkezX<=anaKarakterGenisligi
            && anakarakterY<=siyahKareMerkezY && siyahKareMerkezY<=anakarakterY+anaKarakterYuksekligi){
            sariDaireX=-10.0f
            timer.cancel()
         val intent=Intent(this@OyunEkraniActivity,SonucEkraniActivity::class.java)
            intent.putExtra("skor",skor)
            startActivity(intent)
            finish()

        }
        binding.textViewSkor.text=skor.toString()

    }

}