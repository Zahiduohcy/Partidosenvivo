package com.info.footballlive.ui.fixtures.fixturedetail

import com.info.footballlive.rest.model.fixturedetail.FixtureDetail

interface FixtureDetailContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun display(fixtureDetail: FixtureDetail.Fixture?)
    }

    interface Presenter {
        fun getData(fixtureId: Int)
        fun onDestroyPresenter()
    }
}