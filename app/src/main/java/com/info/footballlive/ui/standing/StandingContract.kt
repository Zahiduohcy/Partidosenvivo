package com.info.footballlive.ui.standing

import com.info.footballlive.rest.model.Standing

interface StandingContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun display(standingList: Standing?)
    }

    interface Presenter {
        fun getData(leagueId: Int, season: Int)
        fun onDestroyPresenter()
    }
}