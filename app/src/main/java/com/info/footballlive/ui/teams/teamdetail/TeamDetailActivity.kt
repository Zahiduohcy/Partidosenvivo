package com.info.footballlive.ui.teams.teamdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.request.RequestOptions
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.info.footballlive.R
import com.info.footballlive.app.MyApplication
import com.info.footballlive.rest.model.Team
import com.info.footballlive.ui.adapter.ViewPagerAdapter
import com.info.footballlive.ui.players.PlayersFragment
import com.info.footballlive.ui.teams.teamdetail.teamstatistics.TeamStatisticsFragment
import com.info.footballlive.utils.GlideApp
import kotlinx.android.synthetic.main.activity_team_detail.*

class TeamDetailActivity : AppCompatActivity() {

    companion object {
        const val ARG_LEAGUE_ID = "ARG_LEAGUE_ID"
        const val ARG_TEAM = "ARG_TEAM"
    }

    private var mLeagueId = 0
    private lateinit var mTeam: Team

    private lateinit var mInterstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        // app bar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        MyApplication.showAd(this)
        // admob
        val adRequest = AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build()
        adView.loadAd(adRequest)

        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = getString(R.string.interstitial_full_screen)
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        // get arguments
        mLeagueId = intent.getIntExtra(ARG_LEAGUE_ID, 0)
        mTeam = intent.getParcelableExtra(ARG_TEAM)

        // display team info
        displayTeamInfo()

        // view pager and tabs
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.populateFragment(TeamStatisticsFragment.newInstance(mLeagueId, mTeam), getString(R.string.team_statistics))
        adapter.populateFragment(PlayersFragment.newInstance(mTeam.team_id!!), getString(R.string.players))

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }

    private fun displayTeamInfo() {
        GlideApp.with(this)
                .load(mTeam.logo)
                .apply(RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                .into(team_logo)

        team_name.text = mTeam.name
        team_founded.text = mTeam.founded?.toString()
        team_venue.text = mTeam.venue_name
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                supportFinishAfterTransition()
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        if (mInterstitialAd.isLoaded) {
//            mInterstitialAd.show()
        }

        super.onDestroy()
    }
}
