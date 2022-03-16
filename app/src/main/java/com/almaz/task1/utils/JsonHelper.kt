package com.almaz.task1.utils

import android.content.Context
import com.almaz.task1.data.mapper.NewsMapper
import com.almaz.task1.data.model.HelpCategory
import com.almaz.task1.data.model.News
import com.almaz.task1.data.model.NewsRaw
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

private const val NEWS_FILE = "news.json"
private const val CATEGORIES_FILE = "categories.json"

object JsonHelper {
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    fun getNews(context: Context): List<News> {
        val typeNews =
            Types.newParameterizedType(List::class.java, NewsRaw::class.java)
        val newsAdapter = moshi.adapter<List<NewsRaw>>(typeNews)
        val jsonNews = getJsonFromAssets(context, NEWS_FILE)
        val newsRaw = newsAdapter.fromJson(jsonNews) ?: listOf()
        val newsMapper = NewsMapper()
        return newsRaw.map { newsMapper.transform(it) }
    }

    fun getCategories(context: Context): List<HelpCategory> {
        val typeCategory =
            Types.newParameterizedType(List::class.java, HelpCategory::class.java)
        val categoryAdapter = moshi.adapter<List<HelpCategory>>(typeCategory)
        val jsonCategory = getJsonFromAssets(context, CATEGORIES_FILE)
        return categoryAdapter.fromJson(jsonCategory) ?: listOf()
    }

    private fun getJsonFromAssets(context: Context, fileName: String) =
        context.assets
            .open(fileName)
            .bufferedReader()
            .use { it.readText() }
}
