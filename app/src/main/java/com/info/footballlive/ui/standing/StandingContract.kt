package com.info.footballlive.ui.standing

import com.info.footballlive.rest.model.StandingModel

interface StandingContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun display(standingList: StandingModel.Response?)
    }

    interface Presenter {
        fun getData(leagueId: Int, season: Int)
        fun onDestroyPresenter()
    }
}