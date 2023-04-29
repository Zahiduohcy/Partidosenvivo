package com.info.footballlive.ui.players

import com.info.footballlive.rest.FootballApi
import com.info.footballlive.rest.model.Player
import com.info.footballlive.rest.model.fixturedetail.PlayerModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*
import kotlin.collections.ArrayList

class PlayersPresenter(private val mView: PlayersContract.View) : PlayersContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getData(teamId: Int) {
        mView.showLoading()
        compositeDisposable.add(FootballApi.retrofitService.getPlayers(teamId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : ResourceSubscriber<PlayerModel>() {
                    override fun onComplete() {
                        mView.hideLoading()
                    }

                    override fun onNext(t: PlayerModel?) {
                        t?.let {
                            if(it.response?.isNotEmpty() == true)
                            it.response?.get(0)?.players?.let { it1 -> mView.display(it1) }
                        }
                    }

                    override fun onError(t: Throwable?) {
                        mView.hideLoading()
                        mView.display(ArrayList())
                    }
                })
        )
    }

    override fun onDestroyPresenter() {
        compositeDisposable.dispose()
    }
}