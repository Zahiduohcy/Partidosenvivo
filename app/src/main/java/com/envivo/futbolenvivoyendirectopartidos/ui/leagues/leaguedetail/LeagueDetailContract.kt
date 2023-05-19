package com.envivo.futbolenvivoyendirectopartidos.ui.leagues.leaguedetail

interface LeagueDetailContract {
    interface View {
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        fun onDestroyPresenter()
    }
}