package com.example.islamic.ui.HadithDetails

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.islamic.databinding.ActivityHadithDeatilsActvityBinding
import com.example.islamic.ui.Constant
import model.Hadith

class HadithDetailsActivity : AppCompatActivity() {
   private lateinit var binding : ActivityHadithDeatilsActvityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHadithDeatilsActvityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        extraParams()

    }

    private fun initViews() {
        binding.hadithTitle.text = hadith?.title
        binding.contentHadeth.hadithContent.text = hadith?.content
        title = null
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private var hadith: Hadith ?= null
    private fun extraParams(){
         hadith = if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU){
            intent.getParcelableExtra(Constant.hadithExtra, Hadith::class.java)
        }else{
             intent.getParcelableExtra(Constant.hadithExtra)
        }
    }
}