package com.envivo.futbolenvivoyendirectopartidos.ui.teams.teamdetail

import io.reactivex.disposables.CompositeDisposable

class TeamDetailPresenter(private val mView: TeamDetailContract.View) : TeamDetailContract.Presenter {
    val compositeDisposable = CompositeDisposable()

    override fun onDestroyPresenter() {
        compositeDisposable.dispose()
    }
}