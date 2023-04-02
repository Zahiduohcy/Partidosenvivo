package com.info.footballlive.ui.teams

import com.info.footballlive.rest.FootballApi
import com.info.footballlive.rest.model.Team
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class TeamsPresenter(private val mView: TeamsContract.View) : TeamsContract.Presenter {
    val compositeDisposable = CompositeDisposable()

    override fun getData(leagueId: Int) {
        mView.showLoading()
        compositeDisposable.add(FootballApi.retrofitService.getTeams(leagueId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : ResourceSubscriber<List<Team>>() {
                    override fun onComplete() {
                        mView.hideLoading()
                    }

                    override fun onNext(t: List<Team>?) {
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