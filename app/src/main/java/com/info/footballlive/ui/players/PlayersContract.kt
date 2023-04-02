package com.info.footballlive.ui.players

import com.info.footballlive.rest.model.Player

interface PlayersContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun display(playerList: List<Player>)
    }

    interface Presenter {
        fun getData(teamId: Int)
        fun onDestroyPresenter()
    }
}