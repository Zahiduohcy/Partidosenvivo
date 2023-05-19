package com.envivo.futbolenvivoyendirectopartidos.ui.players.playerdetail

import com.envivo.futbolenvivoyendirectopartidos.rest.model.PlayerDetail

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