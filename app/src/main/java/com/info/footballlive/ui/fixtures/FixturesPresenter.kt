package com.info.footballlive.ui.fixtures

import com.info.footballlive.rest.FootballApi
import com.info.footballlive.rest.model.Fixture
import com.info.footballlive.rest.model.fixturedetail.FixtureModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class FixturesPresenter(private val mView: FixturesContract.View) : FixturesContract.Presenter {
    val compositeDisposable = CompositeDisposable()
    override fun getData(leagueId: Int, season: Int) {
        mView.showLoading()
        compositeDisposable.add(FootballApi.retrofitService.getFixtures(leagueId,season)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : ResourceSubscriber<FixtureModel>() {
                    override fun onComplete() {
                        mView.hideLoading()
                    }

                    override fun onNext(t: FixtureModel?) {
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