package com.info.footballlive.ui.players

import com.info.footballlive.rest.FootballApi
import com.info.footballlive.rest.model.Player
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class PlayersPresenter(private val mView: PlayersContract.View) : PlayersContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getData(teamId: Int) {
        mView.showLoading()
        compositeDisposable.add(FootballApi.retrofitService.getPlayers(teamId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : ResourceSubscriber<List<Player>>() {
                    override fun onComplete() {
                        mView.hideLoading()
                    }

                    override fun onNext(t: List<Player>?) {
                        t?.let {
                            mView.display(it)
                        }
                    }

                    override fun onError(t: Throwable?) {
                        mView.hideLoading()
                        mView.display(Collections.emptyList())
                    }
                })
        )
    }

    override fun onDestroyPresenter() {
        compositeDisposable.dispose()
    }
}