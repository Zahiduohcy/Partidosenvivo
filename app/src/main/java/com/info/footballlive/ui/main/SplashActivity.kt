package com.info.footballlive.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.info.footballlive.ui.countries.CountriesActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({

          val intent = Intent(this, CountriesActivity::class.java)
            // add single top flag to prevent multiple instances of the same activity
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
            finish()

//            startActivity(intentFor<CountriesActivity>().singleTop())
//            finish()
        }, 1000)
    }
}
