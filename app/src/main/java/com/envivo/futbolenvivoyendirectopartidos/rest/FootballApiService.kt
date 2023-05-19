package com.envivo.futbolenvivoyendirectopartidos.rest

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class FootballApiService {

    companion object {
        private const val API_KEY_VALUE = "ffc7cb9150msh0ace49dc23a5bcap1171c7jsn9f359211097a"
//        val API_KEY_VALUE = "7251606d19mshaa8083621fe197ep198df5jsn309582146336"
        private const val BASE_URL = "https://api-football-v1.p.rapidapi.com/"

        fun getClient() : Retrofit {

            // logging interceptor
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addInterceptor {chain ->
                        val newRequest = chain.request().newBuilder()
                                .addHeader("X-RapidAPI-Key", API_KEY_VALUE)
                                .addHeader("X-RapidAPI-Host","api-football-v1.p.rapidapi.com")
                                .build()
                        chain.proceed(newRequest)
                    }
                    .build()

            val gson = GsonBuilder()
                    .registerTypeAdapterFactory(ItemTypeAdapterFactory())
                    .create()

            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build()
        }
    }
}

object FootballApi {
    val retrofitService : FootballRest by lazy { FootballApiService.getClient().create(FootballRest::class.java) }
}