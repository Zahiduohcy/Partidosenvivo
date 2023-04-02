package com.info.footballlive.ui.teams

import com.info.footballlive.rest.model.Team

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