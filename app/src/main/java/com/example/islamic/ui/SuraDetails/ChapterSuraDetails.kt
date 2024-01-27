package com.example.islamic.ui.SuraDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.islamic.R
import com.example.islamic.databinding.ActivityChapterSuraDetailsBinding
import com.example.islamic.ui.Constant
import kotlin.properties.Delegates

class ChapterSuraDetails : AppCompatActivity() {
    private var chapterIndex : Int = 0
    private lateinit var chapterTitle : String
    private lateinit var viewBinding: ActivityChapterSuraDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityChapterSuraDetailsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setSupportActionBar(viewBinding.toolBar)
        chapterIndex = intent.getIntExtra(Constant.ChapterIndex, 0)
        chapterTitle = intent.getStringExtra(Constant.ChapterTitle)?:""
        initView()
        readSuraFile()
    }

    private fun readSuraFile() {
        val inputStream = assets.open("$chapterIndex.txt")
        val fileContent = inputStream.bufferedReader().use { it.readText() }
        val lines = fileContent.trim().split("\n")
        initRecycleView(lines)
    }

    private fun initRecycleView(verses: List<String>) {
        val adapter = VersesRecycleAdapter(verses)
        viewBinding.content.suraRecycler.adapter = adapter

    }


    private fun initView() {
        viewBinding.suraTitle.text = chapterTitle
        title = null
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}