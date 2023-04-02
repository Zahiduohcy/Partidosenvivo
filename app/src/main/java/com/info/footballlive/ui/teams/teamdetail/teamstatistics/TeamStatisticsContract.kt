package com.info.footballlive.ui.teams.teamdetail.teamstatistics

import com.info.footballlive.rest.model.TeamStatistics

interface TeamStatisticsContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun display(teamStatistics: TeamStatistics?)
    }

    interface Presenter {
        fun getData(leagueId: Int, teamId: Int)
        fun onDestroyPresenter()
    }
}