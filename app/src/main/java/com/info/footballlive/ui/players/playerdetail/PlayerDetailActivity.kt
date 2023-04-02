package com.info.footballlive.ui.players.playerdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.info.footballlive.R
import com.info.footballlive.rest.model.PlayerDetail
import kotlinx.android.synthetic.main.activity_player_detail.*

class PlayerDetailActivity : AppCompatActivity(), PlayerDetailContract.View {

    companion object {
        const val ARG_PLAYER_ID = "ARG_PLAYER_ID"
    }

    private var mPlayerId = 0

    private lateinit var mPresenter: PlayerDetailPresenter

    private lateinit var mRewardedVideoAd: RewardedVideoAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        // get arguments
        mPlayerId = intent.getIntExtra(ARG_PLAYER_ID, 0)

        // app bar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        // admob
        val adRequest = AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build()
        adView.loadAd(adRequest)

        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this)
        mRewardedVideoAd.loadAd(getString(R.string.rewarded_video), AdRequest.Builder().build())

        // presenter
        mPresenter = PlayerDetailPresenter(this)
        mPresenter.getData(mPlayerId)
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

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun display(playerDetail: PlayerDetail?) {
        playerDetail?.let {
            collapsing_toolbar.title = it.player_name

            txtPosition.text = it.position;
            txtAge.text = it.age?.toString()
            txtBirthDate.text = it.birth_date
            txtBirthPlace.text = it.birth_place
            txtBirthCountry.text = it.birth_country
            txtNationality.text = it.nationality
            txtWeight.text = it.weight
            txtHeight.text = it.height

            txtShotsTotal.text = it.shots?.total?.toString()
            txtShotsOn.text = it.shots?.on?.toString()

            txtGoalsTotal.text = it.goals?.total?.toString()
            txtGoalsConceded.text = it.goals?.conceded?.toString()
            txtGoalsAssists.text = it.goals?.assists?.toString()

            txtPassesTotal.text = it.passes?.total?.toString()
            txtPassesAccuracy.text = it.passes?.accuracy?.toString()

            txtTacklesTotal.text = it.tackles?.total?.toString()
            txtTacklesBlocks.text = it.tackles?.blocks?.toString()
            txtTacklesInterceptions.text = it.tackles?.interceptions?.toString()

            txtDuelsTotal.text = it.duels?.total?.toString()
            txtDuelsWon.text = it.duels?.won?.toString()

            txtDribblesAttempts.text = it.dribbles?.attempts?.toString()
            txtDribblesSuccess.text = it.dribbles?.success?.toString()

            txtFoulsDrawn.text = it.fouls?.drawn?.toString()
            txtFoulsCommitted.text = it.fouls?.committed?.toString()

            txtCardsYellow.text = it.cards?.yellow?.toString()
            txtCardsYellowred.text = it.cards?.yellowred?.toString()
            txtCardsRed.text = it.cards?.red?.toString()

            txtPenaltySuccess.text = it.penalty?.success?.toString()
            txtPenaltyMissed.text = it.penalty?.missed?.toString()
            txtPenaltySaved.text = it.penalty?.saved?.toString()

            txtGamesAppearences.text = it.games?.appearences?.toString()
            txtGamesMinutesPlayed.text = it.games?.minutes_played?.toString()
            txtGamesLineups.text = it.games?.lineups?.toString()

            txtSubstitutesIn.text = it.substitutes?.`in`?.toString()
            txtSubstitutesOut.text = it.substitutes?.out?.toString()
            txtSubstitutesBench.text = it.substitutes?.bench?.toString()
        }
    }

    override fun onDestroy() {
        if (mRewardedVideoAd.isLoaded) {
            mRewardedVideoAd.show()
        }

        super.onDestroy()
        mPresenter.onDestroyPresenter()
    }
}
