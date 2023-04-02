package com.info.footballlive.ui.standing

import com.info.footballlive.rest.FootballApi
import com.info.footballlive.rest.model.Standing
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

class StandingPresenter(private val mView: StandingContract.View) : StandingContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getData(leagueId: Int, season: Int) {
        mView.showLoading()
        compositeDisposable.add(FootballApi.retrofitService.getStandings(leagueId, season)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : ResourceSubscriber<List<Standing>>() {
                    override fun onComplete() {
                        mView.hideLoading()
                    }

                    override fun onNext(t: List<Standing>?) {
                        t?.let {
                            if (it.isNotEmpty())
                                mView.display(it[0])
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