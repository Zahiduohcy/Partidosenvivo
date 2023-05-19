package com.envivo.futbolenvivoyendirectopartidos.ui.teams.teamdetail

interface TeamDetailContract {
    interface View {
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        fun onDestroyPresenter()
    }
}