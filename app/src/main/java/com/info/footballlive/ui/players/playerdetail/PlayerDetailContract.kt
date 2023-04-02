package com.info.footballlive.ui.players.playerdetail

import com.info.footballlive.rest.model.PlayerDetail

interface PlayerDetailContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun display(playerDetail: PlayerDetail?)
    }

    interface Presenter {
        fun getData(playerId: Int)
        fun onDestroyPresenter()
    }
}