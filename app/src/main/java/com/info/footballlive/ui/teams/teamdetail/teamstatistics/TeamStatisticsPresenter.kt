package com.info.footballlive.ui.teams.teamdetail.teamstatistics

import com.info.footballlive.rest.FootballApi
import com.info.footballlive.rest.model.TeamStatistics
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

class TeamStatisticsPresenter(private val mView: TeamStatisticsContract.View) : TeamStatisticsContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getData(leagueId: Int, teamId: Int) {
        mView.showLoading()
        compositeDisposable.add(FootballApi.retrofitService.getTeamStatistics(leagueId, teamId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : ResourceSubscriber<TeamStatistics>() {
                    override fun onComplete() {
                        mView.hideLoading()
                    }

                    override fun onNext(t: TeamStatistics?) {
                        mView.display(t)
                    }

                    override fun onError(t: Throwable?) {
                        mView.hideLoading()
                        mView.display(null)
                    }
                })
        )
    }

    override fun onDestroyPresenter() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}