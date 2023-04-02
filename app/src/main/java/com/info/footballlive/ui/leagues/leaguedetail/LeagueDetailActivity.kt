package com.info.footballlive.ui.leagues.leaguedetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.AdRequest
import com.info.footballlive.R
import com.info.footballlive.ui.fixtures.FixturesFragment
import com.info.footballlive.ui.main.AboutActivity
import com.info.footballlive.ui.standing.StandingFragment
import com.info.footballlive.ui.teams.TeamsFragment
import kotlinx.android.synthetic.main.activity_league_detail.*
import org.jetbrains.anko.startActivity

class LeagueDetailActivity : AppCompatActivity() {

    companion object {
        const val ARG_LEAGUE_ID = "ARG_LEAGUE_ID"
        const val ARG_LEAGUE_NAME = "ARG_LEAGUE_NAME"
        const val ARG_SEASON = "ARG_SEASON"
    }

    private var mLeagueId : Int = 0
    private var mLeagueName = ""
    private var mSeason : Int = 2023

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league_detail)

        // get arguments
        mLeagueId = intent.getIntExtra(ARG_LEAGUE_ID, 0)
        mSeason = intent.getIntExtra(ARG_SEASON, 2022)
        mLeagueName = intent.getStringExtra(ARG_LEAGUE_NAME)

        // action bar
        supportActionBar?.title = mLeagueName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // admob
        val adRequest = AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build()
        adView.loadAd(adRequest)

        // bottom navigation
        bottomNavigation.setOnNavigationItemSelectedListener {item ->
            when (item.itemId) {
                R.id.fixtures -> {
                    showFragment(FixturesFragment.newInstance(mLeagueId,mSeason))
                }
                R.id.standing -> {
                    showFragment(StandingFragment.newInstance(mLeagueId, mSeason))
                }
                R.id.teams -> {
                    showFragment(TeamsFragment.newInstance(mLeagueId))
                }
            }
            true
        }
        bottomNavigation.selectedItemId = R.id.fixtures
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.mainContainer, fragment)
                .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                supportFinishAfterTransition()
                onBackPressed()
                true
            }
            R.id.menu_about -> {
                startActivity<AboutActivity>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
