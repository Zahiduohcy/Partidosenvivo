package com.envivo.futbolenvivoyendirectopartidos.ui.leagues

import com.envivo.futbolenvivoyendirectopartidos.rest.model.League

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