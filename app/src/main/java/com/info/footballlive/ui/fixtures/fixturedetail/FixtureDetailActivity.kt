package com.info.footballlive.ui.fixtures.fixturedetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.bumptech.glide.request.RequestOptions
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.info.footballlive.R
import com.info.footballlive.rest.model.Fixture
import com.info.footballlive.rest.model.fixturedetail.FixtureDetail
import com.info.footballlive.utils.GlideApp
import kotlinx.android.synthetic.main.activity_fixture_detail.*
import kotlinx.android.synthetic.main.item_fixture_event.view.*

class FixtureDetailActivity : AppCompatActivity(), FixtureDetailContract.View {

    companion object {
        const val ARG_FIXTURE_ID = "FIXTURE_ID"
    }

    private var mFixtureId: Int? = null

    private lateinit var mPresenter: FixtureDetailPresenter

    private lateinit var mInterstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fixture_detail)

        // app bar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        // admob
        val adRequest = AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build()
        adView.loadAd(adRequest)

        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = getString(R.string.interstitial_full_screen)
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        // get arguments
        mFixtureId = intent.getIntExtra(ARG_FIXTURE_ID, 0)

        // presenter
        mPresenter = FixtureDetailPresenter(this)
        mFixtureId?.let {
            mPresenter.getData(it)
        }
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun display(fixtureDetail: FixtureDetail.Fixture?) {
        Log.e("test", "test1")
        fixtureDetail?.let {
            // home team
            txtHomeTeam.text = fixtureDetail.teams?.home?.name
            txtHomeScore.text = fixtureDetail.goals?.home?.toString()
            GlideApp.with(this)
                    .load(fixtureDetail.teams?.home?.logo)
                    .apply(RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_broken_image))
                    .into(imgHomeLogo)

            // away team
            txtAwayTeam.text = fixtureDetail.teams?.away?.name
            txtAwayScore.text = fixtureDetail.goals?.away?.toString()
            GlideApp.with(this)
                    .load(fixtureDetail.teams?.away?.logo)
                    .apply(RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_broken_image))
                    .into(imgAwayLogo)

            // fixture
            txtRound.text = fixtureDetail.league?.round
            txtStatus.text = fixtureDetail.fixture?.status?.long
            txtVenue.text = fixtureDetail.fixture?.venue?.name
            if(fixtureDetail.lineups.size>0)
            fixtureDetail.lineups[0].let {
                // Formation
                homeFormation.text = it?.formation

                // Goal Keeper, Defense, Midfield, Forward
                homeGoalKeeper.text = it?.startXI?.filter { it?.player?.pos == "G" }?.joinToString("\n") { it?.player?.name!! }
                homeDefense.text = it?.startXI?.filter { it?.player?.pos == "D" }?.joinToString("\n") { it?.player?.name!! }
                homeMidfield.text = it?.startXI?.filter { it?.player?.pos == "M" }?.joinToString("\n") { it?.player?.name!! }
                homeForward.text = it?.startXI?.filter { it?.player?.pos == "F" }?.joinToString("\n") { it?.player?.name!! }

                // substitutes
                homeSubstitutes.text = it?.substitutes?.joinToString("\n") { it?.player?.name!! }
            }
            if(fixtureDetail.lineups.size>0)
            fixtureDetail.lineups[1].let{
                // Formation
                awayFormation.text = it?.formation

                // Goal Keeper, Defense, Midfield, Forward
                awayGoalKeeper.text = it?.startXI?.filter { it?.player?.pos == "G" }?.joinToString("\n") { it?.player?.name!! }
                awayDefense.text = it?.startXI?.filter { it?.player?.pos == "D" }?.joinToString("\n") { it?.player?.name!! }
                awayMidfield.text = it?.startXI?.filter { it?.player?.pos == "M" }?.joinToString("\n") { it?.player?.name!! }
                awayForward.text = it?.startXI?.filter { it?.player?.pos == "F" }?.joinToString("\n") { it?.player?.name!! }

                // substitutes
                awaySubstitutes.text = it?.substitutes?.joinToString("\n") { it?.player?.name!! }
            }

            // events
            fixtureDetail.events?.forEach {
                val event = layoutInflater.inflate(R.layout.item_fixture_event, null).apply {
                    this.elapsed.text = it?.time?.elapsed.toString()
                    this.teamName.text = it?.team?.name
                    this.player.text = it?.player?.name
                    this.type.text = it?.type
                }

                layout_fixture_events.addView(event)
            }
        }
    }

    override fun onDestroy() {
        if (mInterstitialAd.isLoaded) {
            mInterstitialAd.show()
        }

        super.onDestroy()
        mPresenter.onDestroyPresenter()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
