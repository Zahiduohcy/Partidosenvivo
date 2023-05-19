package com.envivo.futbolenvivoyendirectopartidos.ui.standing

import com.envivo.futbolenvivoyendirectopartidos.rest.model.StandingModel

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