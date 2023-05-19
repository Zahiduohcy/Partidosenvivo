package com.envivo.futbolenvivoyendirectopartidos.ui.leagues

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.envivo.futbolenvivoyendirectopartidos.R
import com.envivo.futbolenvivoyendirectopartidos.app.MyApplication
import com.envivo.futbolenvivoyendirectopartidos.ui.adapter.LeagueAdapter
import com.envivo.futbolenvivoyendirectopartidos.extensions.hide
import com.envivo.futbolenvivoyendirectopartidos.extensions.show
import com.envivo.futbolenvivoyendirectopartidos.rest.model.League
import com.envivo.futbolenvivoyendirectopartidos.ui.leagues.leaguedetail.LeagueDetailActivity
import com.envivo.futbolenvivoyendirectopartidos.ui.main.AboutActivity
import kotlinx.android.synthetic.main.activity_leagues.*

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
        MyApplication.showAd(this)
        // admob
        val adRequest = AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build()
        adView.loadAd(adRequest)

        // get arguments
        mCountryCode = intent.getStringExtra(ARG_COUNTRY_CODE).toString()

        // recycler view
        rvLeagueList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        mAdapter = LeagueAdapter(mLeagueList) {
            val intent = Intent(this, LeagueDetailActivity::class.java).apply {
                putExtra(LeagueDetailActivity.ARG_LEAGUE_ID, it.league?.id)
                putExtra(LeagueDetailActivity.ARG_LEAGUE_NAME, it.league?.name)
                putExtra(LeagueDetailActivity.ARG_SEASON, it.seasons?.get(it.seasons!!.size-1)?.year)
            }
            startActivity(intent)
//            startActivity<LeagueDetailActivity>(
//                    LeagueDetailActivity.ARG_LEAGUE_ID to it.league?.id,
//                    LeagueDetailActivity.ARG_LEAGUE_NAME to it.league?.name,
//                    LeagueDetailActivity.ARG_SEASON to it.seasons?.get(it.seasons!!.size-1)?.year
//            )
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
//            Toast.makeText(this, "Can not pull the league list", Toast.LENGTH_SHORT).show()
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                supportFinishAfterTransition()
                onBackPressed()
                true
            }
            R.id.menu_about -> {
//                startActivity<AboutActivity>()
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
