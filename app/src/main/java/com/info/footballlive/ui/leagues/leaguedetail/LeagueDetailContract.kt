package com.info.footballlive.ui.leagues.leaguedetail

interface LeagueDetailContract {
    interface View {
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        fun onDestroyPresenter()
    }
}