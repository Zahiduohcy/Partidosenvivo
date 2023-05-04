package com.info.footballlive.ui.leagues

import com.info.footballlive.rest.FootballApi
import com.info.footballlive.rest.model.fixturedetail.LeagueModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class LeaguesPresenter(private val mView: LeaguesContract.View) : LeaguesContract.Presenter {
    val compositeDisposable = CompositeDisposable()

    override fun getData(countryCode: String) {
        mView.showLoading()
        if(countryCode != "World")
        compositeDisposable.add(FootballApi.retrofitService.getLeagues(countryCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : ResourceSubscriber<LeagueModel>() {
                    override fun onComplete() {
                        mView.hideLoading()
                    }

                    override fun onNext(t: LeagueModel?) {
                        t?.let {
                            // sort by league start date

                            mView.display(it.response.reversed())

                        }
                    }

                    override fun onError(t: Throwable?) {
                        mView.hideLoading()
                        mView.display(Collections.emptyList())
                    }
                })
        )
        else
            compositeDisposable.add(FootballApi.retrofitService.getGlobalLeagues()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : ResourceSubscriber<LeagueModel>() {
                    override fun onComplete() {
                        mView.hideLoading()
                    }

                    override fun onNext(t: LeagueModel?) {
                        t?.let {
                            mView.display(it.response)
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