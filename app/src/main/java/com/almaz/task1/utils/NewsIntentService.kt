@file:Suppress("DEPRECATION")

package com.almaz.task1.utils

import android.app.IntentService
import android.content.Intent
import android.os.Parcelable

class NewsIntentService : IntentService("NewsIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        Thread.sleep(SLEEP_TIME)
        val newsList = this.let { JsonHelper.getNews(it) }
        val responseIntent = Intent()
        responseIntent.apply {
            action = ACTION_NEWS_UPDATE
            addCategory(Intent.CATEGORY_DEFAULT)
            putExtra(NEWS_KEY_OUT, newsList as ArrayList<out Parcelable>)
        }
        sendBroadcast(responseIntent)
    }

    companion object {
        const val ACTION_NEWS_UPDATE = "ACTION_NEWS_UPDATE"
        const val NEWS_KEY_OUT = "NEWS_KEY_OUT"
    }
}
