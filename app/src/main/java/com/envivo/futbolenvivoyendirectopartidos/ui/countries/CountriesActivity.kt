package com.envivo.futbolenvivoyendirectopartidos.ui.countries

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.envivo.futbolenvivoyendirectopartidos.R
import com.envivo.futbolenvivoyendirectopartidos.ui.adapter.CountryAdapter
import com.envivo.futbolenvivoyendirectopartidos.extensions.hide
import com.envivo.futbolenvivoyendirectopartidos.extensions.show
import com.envivo.futbolenvivoyendirectopartidos.ui.leagues.LeaguesActivity
import com.envivo.futbolenvivoyendirectopartidos.rest.model.Country
import com.envivo.futbolenvivoyendirectopartidos.ui.main.AboutActivity
import kotlinx.android.synthetic.main.activity_countries.*

class CountriesActivity : AppCompatActivity(), CountriesContract.View {

    private lateinit var mPresenter: CountriesPresenter
    private lateinit var mAdapter: CountryAdapter
    private var mCountryList: MutableList<Country> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countries)

        // action bar
        supportActionBar?.title = "Countries"

        // admob
        val adRequest = AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build()
        adView.loadAd(adRequest)

        // recycler view
        rvCountryList.layoutManager = GridLayoutManager (this,2, RecyclerView.VERTICAL, false)
        mAdapter = CountryAdapter(mCountryList) {
            val intent = Intent(this, LeaguesActivity::class.java).apply {
                putExtra(LeaguesActivity.ARG_COUNTRY_CODE, it.code)
            }
            startActivity(intent)
        }
        rvCountryList.adapter = mAdapter

        // presenter
        mPresenter = CountriesPresenter(this)
        mPresenter.getData()
    }

    override fun showLoading() {
        mainProgressBar.show()
        rvCountryList.hide()
    }

    override fun hideLoading() {
        mainProgressBar.hide()
        rvCountryList.show()
    }

    override fun display(countryList: List<Country>) {
        if (countryList.isEmpty()) {
//            Toast.makeText(this, "Can not pull the country list", Toast.LENGTH_SHORT).show()
        }
        mCountryList.clear()

        // world
        val world = Country("World", "World", "World")
        mCountryList.add(world)

        for (item in countryList) {
            if (item.name != "World")
                mCountryList.add(item)
        }

        mAdapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item?.itemId == R.id.menu_about) {
            startActivity(Intent(this,AboutActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroyPresenter()
    }
}
