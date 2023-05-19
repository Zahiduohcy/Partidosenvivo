package com.envivo.futbolenvivoyendirectopartidos.ui.teams

import com.envivo.futbolenvivoyendirectopartidos.rest.model.Team

interface TeamsContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun display(teamList: List<Team>)
    }

    interface Presenter {
        fun getData(leagueId: Int)
        fun onDestroyPresenter()
    }
}