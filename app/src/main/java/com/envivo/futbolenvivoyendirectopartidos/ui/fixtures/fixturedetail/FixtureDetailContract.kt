package com.envivo.futbolenvivoyendirectopartidos.ui.fixtures.fixturedetail

import com.envivo.futbolenvivoyendirectopartidos.rest.model.fixturedetail.FixtureDetail

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