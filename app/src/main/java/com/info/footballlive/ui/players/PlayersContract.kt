package com.info.footballlive.ui.players

import com.info.footballlive.rest.model.Player
import com.info.footballlive.rest.model.fixturedetail.PlayerModel

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