package com.info.footballlive.ui.leagues

import com.info.footballlive.rest.model.League

interface LeaguesContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun display(leagueList: List<League>)
    }

    interface Presenter {
        fun getData(countryCode: String)
        fun onDestroyPresenter()
    }
}