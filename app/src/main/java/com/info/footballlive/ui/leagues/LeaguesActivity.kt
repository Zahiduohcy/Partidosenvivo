package com.info.footballlive.ui.leagues

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.info.footballlive.R
import com.info.footballlive.ui.adapter.LeagueAdapter
import com.info.footballlive.extensions.hide
import com.info.footballlive.extensions.show
import com.info.footballlive.rest.model.League
import com.info.footballlive.ui.leagues.leaguedetail.LeagueDetailActivity
import com.info.footballlive.ui.main.AboutActivity
import kotlinx.android.synthetic.main.activity_leagues.*
import org.jetbrains.anko.startActivity

class LeaguesActivity : AppCompatActivity(), LeaguesContract.View {

    companion object {
        const val ARG_COUNTRY_CODE = "ARG_COUNTRY_CODE"
    }

    private lateinit var mCountryCode : String

    private lateinit var mPresenter : LeaguesPresenter
    private lateinit var mAdapter : LeagueAdapter
    private var mLeagueList : MutableList<League> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leagues)

        // action bar
        supportActionBar?.title = "Leagues"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // admob
        val adRequest = AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build()
        adView.loadAd(adRequest)

        // get arguments
        mCountryCode = intent.getStringExtra(ARG_COUNTRY_CODE)

        // recycler view
        rvLeagueList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        mAdapter = LeagueAdapter(mLeagueList) {
            startActivity<LeagueDetailActivity>(
                    LeagueDetailActivity.ARG_LEAGUE_ID to it.league?.id,
                    LeagueDetailActivity.ARG_LEAGUE_NAME to it.league?.name,
                    LeagueDetailActivity.ARG_SEASON to it.seasons?.get(it.seasons!!.size-1)?.year
            )
        }
        rvLeagueList.adapter = mAdapter

        // presenter
        mPresenter = LeaguesPresenter(this)
        mPresenter.getData(mCountryCode)
    }

    override fun showLoading() {
        mainProgressBar.show()
        rvLeagueList.hide()
    }

    override fun hideLoading() {
        mainProgressBar.hide()
        rvLeagueList.show()
    }

    override fun display(leagueList: List<League>) {
        if (leagueList.isEmpty()) {
            Toast.makeText(this, "Can not pull the league list", Toast.LENGTH_SHORT).show()
        }
        mLeagueList.clear()
        mLeagueList.addAll(leagueList)
        mAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroyPresenter()
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
