package com.info.footballlive.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.info.footballlive.ui.countries.CountriesActivity
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            startActivity(intentFor<CountriesActivity>().singleTop())
            finish()
        }, 1000)
    }
}
