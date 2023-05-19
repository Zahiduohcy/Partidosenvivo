package com.envivo.futbolenvivoyendirectopartidos.ui.players

import com.envivo.futbolenvivoyendirectopartidos.rest.model.Player

interface PlayersContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun display(playerList: ArrayList<Player>)
    }

    interface Presenter {
        fun getData(teamId: Int)
        fun onDestroyPresenter()
    }
}