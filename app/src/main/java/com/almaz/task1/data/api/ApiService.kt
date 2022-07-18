package com.almaz.task1.data.api

import com.almaz.task1.data.model.HelpCategory
import com.almaz.task1.data.model.News
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("/categories")
    fun loadHelpCategories(): Single<List<HelpCategory>>

    @GET("/news")
    fun loadNews(): Single<List<News>>
}
