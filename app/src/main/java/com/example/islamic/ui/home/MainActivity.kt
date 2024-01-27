package com.example.islamic.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.islamic.R
import com.example.islamic.databinding.ActivityMainBinding
import com.example.islamic.ui.home.hadeth.HadethFragment
import com.example.islamic.ui.home.quran.QuranFragment
import com.example.islamic.ui.home.radio.RadioFragment
import com.example.islamic.ui.home.tasbeh.TasbehFragment
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
    lateinit var viewBinding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initViews()
    }

    private fun initViews() {
        viewBinding.content
            .navigationBar.setOnItemSelectedListener { item ->
              val fragment:Fragment =   when (item.itemId) {
                    R.id.nav_quran -> {
                      QuranFragment()
                    }

                    R.id.nav_hadeth -> {
                        HadethFragment()
                    }

                    R.id.nav_sebha -> {
                        TasbehFragment()
                    }

                    R.id.nav_radio -> {
                        RadioFragment()
                    }

                  else -> {QuranFragment()}
              }
                pushFragment(fragment)
                true
            }
        viewBinding.content.navigationBar.selectedItemId = R.id.nav_quran
    }
    private fun pushFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit()
    }
}