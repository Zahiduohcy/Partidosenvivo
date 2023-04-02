package com.info.footballlive.ui.countries

import com.info.footballlive.rest.model.Country

interface CountriesContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun display(countryList: List<Country>)
    }

    interface Presenter {
        fun getData()
        fun onDestroyPresenter()
    }
}