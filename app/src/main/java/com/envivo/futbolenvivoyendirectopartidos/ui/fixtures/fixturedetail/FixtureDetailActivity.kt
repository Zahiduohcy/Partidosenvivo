package com.envivo.futbolenvivoyendirectopartidos.ui.fixtures.fixturedetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.envivo.futbolenvivoyendirectopartidos.R
import com.envivo.futbolenvivoyendirectopartidos.app.MyApplication
import com.envivo.futbolenvivoyendirectopartidos.rest.model.fixturedetail.FixtureDetail
import kotlinx.android.synthetic.main.activity_fixture_detail.*
import kotlinx.android.synthetic.main.item_fixture_event.view.*
import kotlinx.android.synthetic.main.item_lineup.view.*

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
            Glide.with(this)
                    .load(fixtureDetail.teams?.home?.logo)
                    .apply(RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_broken_image))
                    .into(imgHomeLogo)

            // away team
            txtAwayTeam.text = fixtureDetail.teams?.away?.name
            txtAwayScore.text = fixtureDetail.goals?.away?.toString()
            Glide.with(this)
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
                it?.startXI?.filter { it?.player?.pos == "G" }?.forEachIndexed{ index, itt ->
                    val event = layoutInflater.inflate(R.layout.item_lineup, null).apply {
                        this.player_name.text = itt?.player?.name
                    }
                    if(index%2==0){
                        event.setBackgroundResource(R.drawable.divider)
                    }else{
                        event.setBackgroundResource(R.drawable.white_bg)
                    }
                    homeGoalKeeper.addView(event)
                }
                it?.startXI?.filter { it?.player?.pos == "D" }?.forEachIndexed{ index, itt ->
                    val event = layoutInflater.inflate(R.layout.item_lineup, null).apply {
                        this.player_name.text = itt?.player?.name
                    }
                    if(index%2==0){
                        event.setBackgroundResource(R.drawable.divider)
                    }else{
                        event.setBackgroundResource(R.drawable.white_bg)
                    }
                    homeDefense.addView(event)
                }
                it?.startXI?.filter { it?.player?.pos == "M" }?.forEachIndexed{ index, itt ->
                    val event = layoutInflater.inflate(R.layout.item_lineup, null).apply {
                        this.player_name.text = itt?.player?.name
                    }
                    if(index%2==0){
                        event.setBackgroundResource(R.drawable.divider)
                    }else{
                        event.setBackgroundResource(R.drawable.white_bg)
                    }
                    homeMidfield.addView(event)
                }

                it?.startXI?.filter { it?.player?.pos == "F" }?.forEachIndexed{ index, itt ->
                    val event = layoutInflater.inflate(R.layout.item_lineup, null).apply {
                        this.player_name.text = itt?.player?.name
                    }
                    if(index%2==0){
                        event.setBackgroundResource(R.drawable.divider)
                    }else{
                        event.setBackgroundResource(R.drawable.white_bg)
                    }
                    homeForward.addView(event)
                }
                // substitutes
                it?.substitutes?.forEachIndexed{ index, itt ->
                    val event = layoutInflater.inflate(R.layout.item_lineup, null).apply {
                        this.player_name.text = itt?.player?.name
                    }
                    if(index%2==0){
                        event.setBackgroundResource(R.drawable.divider)
                    }else{
                        event.setBackgroundResource(R.drawable.white_bg)
                    }
                    homeSubstitutes.addView(event)
                }
            }
            if(fixtureDetail.lineups.size>0)
            fixtureDetail.lineups[1].let{
                // Formation
                awayFormation.text = it?.formation

                // Goal Keeper, Defense, Midfield, Forward
                it?.startXI?.filter { it?.player?.pos == "G" }?.forEachIndexed{ index, itt ->
                    val event = layoutInflater.inflate(R.layout.item_lineup, null).apply {
                        this.player_name.text = itt?.player?.name
                    }
                    if(index%2==1){
                        event.setBackgroundResource(R.drawable.divider)
                    }else{
                        event.setBackgroundResource(R.drawable.white_bg)
                    }
                    awayGoalKeeper.addView(event)
                }
                it?.startXI?.filter { it?.player?.pos == "D" }?.forEachIndexed{ index, itt ->
                    val event = layoutInflater.inflate(R.layout.item_lineup, null).apply {
                        this.player_name.text = itt?.player?.name
                    }
                    if(index%2==1){
                        event.setBackgroundResource(R.drawable.divider)
                    }else{
                        event.setBackgroundResource(R.drawable.white_bg)
                    }
                    awayDefense.addView(event)
                }
                it?.startXI?.filter { it?.player?.pos == "M" }?.forEachIndexed{ index, itt ->
                    val event = layoutInflater.inflate(R.layout.item_lineup, null).apply {
                        this.player_name.text = itt?.player?.name
                    }
                    if(index%2==1){
                        event.setBackgroundResource(R.drawable.divider)
                    }else{
                        event.setBackgroundResource(R.drawable.white_bg)
                    }
                    awayMidfield.addView(event)
                }

                it?.startXI?.filter { it?.player?.pos == "F" }?.forEachIndexed{ index, itt ->
                    val event = layoutInflater.inflate(R.layout.item_lineup, null).apply {
                        this.player_name.text = itt?.player?.name
                    }
                    if(index%2==1){
                        event.setBackgroundResource(R.drawable.divider)
                    }else{
                        event.setBackgroundResource(R.drawable.white_bg)
                    }
                    awayForward.addView(event)
                }
                // substitutes
                it?.substitutes?.forEachIndexed{ index, itt ->
                    val event = layoutInflater.inflate(R.layout.item_lineup, null).apply {
                        this.player_name.text = itt?.player?.name
                    }
                    if(index%2==1){
                        event.setBackgroundResource(R.drawable.divider)
                    }else{
                        event.setBackgroundResource(R.drawable.white_bg)
                    }
                    awaySubstitutes.addView(event)
                }
            }

            // events
            fixtureDetail.events?.forEachIndexed { index, it ->
                val event = layoutInflater.inflate(R.layout.item_fixture_event, null).apply {
                    this.elapsed.text = it?.time?.elapsed.toString()
                    this.teamName.text = it?.team?.name
                    this.player.text = it?.player?.name
                    this.type.text = it?.type
                }
                if(index%2==1){
                    event.setBackgroundResource(R.drawable.divider)
                }else{
                    event.setBackgroundResource(R.drawable.white_bg)
                }

                layout_fixture_events.addView(event)
            }
        }
    }

    override fun onDestroy() {
        if (mInterstitialAd.isLoaded) {
//            mInterstitialAd.show()
        }

        super.onDestroy()
        mPresenter.onDestroyPresenter()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
