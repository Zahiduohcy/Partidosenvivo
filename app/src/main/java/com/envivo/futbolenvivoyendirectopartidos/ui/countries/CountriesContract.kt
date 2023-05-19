package com.envivo.futbolenvivoyendirectopartidos.ui.countries

import com.envivo.futbolenvivoyendirectopartidos.rest.model.Country

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