package com.info.footballlive.ui.countries

import android.util.Log
import com.info.footballlive.rest.FootballApi
import com.info.footballlive.rest.model.Country
import com.info.footballlive.rest.model.CountryModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class CountriesPresenter(val mView: CountriesContract.View) : CountriesContract.Presenter {
    val compositeDisposable = CompositeDisposable()
    override fun getData() {
        mView.showLoading()
        compositeDisposable.add(FootballApi.retrofitService.getCountries()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : ResourceSubscriber<CountryModel>() {
                    override fun onComplete() {
                        mView.hideLoading()
                    }

                    override fun onNext(t: CountryModel?) {
                        t?.let {
                            mView.display(it.response)
                        }
                    }

                    override fun onError(t: Throwable?) {
                        mView.hideLoading()
                        Log.e("Error",t?.localizedMessage)
                        mView.display(Collections.emptyList())
                    }
                })
        )
    }

    override fun onDestroyPresenter() {
        compositeDisposable.dispose()
    }
}