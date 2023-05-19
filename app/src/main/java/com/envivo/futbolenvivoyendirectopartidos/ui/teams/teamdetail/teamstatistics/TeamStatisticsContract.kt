package com.envivo.futbolenvivoyendirectopartidos.ui.teams.teamdetail.teamstatistics

import com.envivo.futbolenvivoyendirectopartidos.rest.model.TeamStatistics

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