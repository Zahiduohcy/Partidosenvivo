package com.envivo.futbolenvivoyendirectopartidos.ui.fixtures

import com.envivo.futbolenvivoyendirectopartidos.rest.model.Fixture

interface FixturesContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun display(fixtureList: List<Fixture>)
    }

    interface Presenter {
        fun getData(leagueId : Int, season: Int)
        fun onDestroyPresenter()
    }
}