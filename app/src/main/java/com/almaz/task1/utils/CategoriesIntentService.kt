@file:Suppress("DEPRECATION")

package com.almaz.task1.utils

import android.app.IntentService
import android.content.Intent
import android.os.Parcelable

class CategoriesIntentService : IntentService("CategoriesIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        Thread.sleep(SLEEP_TIME)
        val categories = this.let { JsonHelper.getCategories(it) }
        val responseIntent = Intent()
        responseIntent.apply {
            action = ACTION_CATEGORIES_UPDATE
            addCategory(Intent.CATEGORY_DEFAULT)
            putExtra(CATEGORIES_KEY_OUT, categories as ArrayList<out Parcelable>)
        }
        sendBroadcast(responseIntent)
    }

    companion object {
        const val SLEEP_TIME = 5000L
        const val ACTION_CATEGORIES_UPDATE = "ACTION_CATEGORIES_UPDATE"
        const val CATEGORIES_KEY_OUT = "CATEGORIES_KEY_OUT"
    }
}
