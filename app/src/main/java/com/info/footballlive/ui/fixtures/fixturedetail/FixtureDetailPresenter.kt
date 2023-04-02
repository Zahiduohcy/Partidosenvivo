package com.info.footballlive.ui.fixtures.fixturedetail

import android.util.Log
import com.info.footballlive.rest.FootballApi
import com.info.footballlive.rest.model.fixturedetail.FixtureDetail
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

class FixtureDetailPresenter(private val mView: FixtureDetailContract.View) : FixtureDetailContract.Presenter {
    val compositeDisposable = CompositeDisposable()

    override fun getData(fixtureId: Int) {
        mView.showLoading()
        compositeDisposable.add(FootballApi.retrofitService.getFixtureDetail(fixtureId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : ResourceSubscriber<FixtureDetail>() {
                    override fun onComplete() {
                        mView.hideLoading()
                    }

                    override fun onNext(t: FixtureDetail?) {
                        if (t != null) {
                            mView.display(t.response.get(0))
                        }
                    }

                    override fun onError(t: Throwable?) {
                        t?.printStackTrace()
                        Log.e("errror", t?.localizedMessage+"")
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