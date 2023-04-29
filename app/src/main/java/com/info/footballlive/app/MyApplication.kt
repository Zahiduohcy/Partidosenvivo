package com.info.footballlive.app

import android.app.Application
import android.content.Context
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.info.footballlive.R

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // init the admob
        MobileAds.initialize(this, getString(R.string.admob_app_id))
        loadAd(applicationContext)
    }

    // create static method to load and show the ad

    companion object {
        private lateinit var mInterstitialAd: InterstitialAd

        fun loadAd(context: Context){
            mInterstitialAd = InterstitialAd(context)
            mInterstitialAd.adUnitId = context.resources.getString(R.string.interstitial_full_screen)
            mInterstitialAd.loadAd(AdRequest.Builder().build())
            mInterstitialAd.adListener = object : com.google.android.gms.ads.AdListener() {
                override fun onAdLoaded() {
                    // Code to be executed when an ad finishes loading.
                }

                override fun onAdFailedToLoad(errorCode: Int) {
                    // Code to be executed when an ad request fails.
                }

                override fun onAdOpened() {
                    // Code to be executed when the ad is displayed.
                }

                override fun onAdLeftApplication() {
                    // Code to be executed when the user has left the app.
                }

                override fun onAdClosed() {
                    // Code to be executed when when the interstitial ad is closed.
                    loadAd(context)
                }
            }
        }

        fun showAd(context: Context) {
            if (mInterstitialAd.isLoaded) {
                mInterstitialAd.show()
            }else{
                loadAd(context)

            }
        }
    }

}