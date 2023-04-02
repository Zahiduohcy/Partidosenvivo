package com.info.footballlive.ui.fixtures

import com.info.footballlive.rest.model.Fixture

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