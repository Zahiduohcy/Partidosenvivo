package com.envivo.futbolenvivoyendirectopartidos.ui.countries

import android.util.Log
import com.envivo.futbolenvivoyendirectopartidos.rest.FootballApi
import com.envivo.futbolenvivoyendirectopartidos.rest.model.CountryModel
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
                        t?.localizedMessage?.let { Log.e("Error", it) }
                        mView.display(Collections.emptyList())
                    }
                })
        )
    }

    override fun onDestroyPresenter() {
        compositeDisposable.dispose()
    }
}