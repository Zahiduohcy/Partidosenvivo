package com.info.footballlive.ui.teams.teamdetail

interface TeamDetailContract {
    interface View {
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        fun onDestroyPresenter()
    }
}