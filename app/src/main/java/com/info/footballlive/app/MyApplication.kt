package com.info.footballlive.app

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.info.footballlive.R

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // init the admob
        MobileAds.initialize(this, getString(R.string.admob_app_id))
    }
}