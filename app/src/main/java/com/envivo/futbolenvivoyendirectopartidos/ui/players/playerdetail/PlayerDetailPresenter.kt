package com.envivo.futbolenvivoyendirectopartidos.ui.players.playerdetail

import com.envivo.futbolenvivoyendirectopartidos.rest.FootballApi
import com.envivo.futbolenvivoyendirectopartidos.rest.model.PlayerDetail
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

class PlayerDetailPresenter(private val mView: PlayerDetailContract.View) : PlayerDetailContract.Presenter {
    val compositeDisposable = CompositeDisposable()

    override fun getData(playerId: Int) {
        mView.showLoading()
        compositeDisposable.add(FootballApi.retrofitService.getPlayerDetail(playerId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : ResourceSubscriber<List<PlayerDetail>>() {
                    override fun onComplete() {
                        mView.hideLoading()
                    }

                    override fun onNext(t: List<PlayerDetail>?) {
                        if (t != null && !t.isEmpty()) {
                            mView.display(t.get(0))
                        }
                    }

                    override fun onError(t: Throwable?) {
                        mView.hideLoading()
                        mView.display(null)
                    }
                })
        )
    }

    override fun onDestroyPresenter() {
        compositeDisposable.dispose()
    }
}