@file:Suppress("DEPRECATION")

package com.almaz.task1.utils

import android.os.AsyncTask
import androidx.fragment.app.Fragment
import com.almaz.task1.data.model.News
import com.almaz.task1.ui.news.NewsFragment
import java.lang.ref.WeakReference

class NewsAsyncTask(
    private var fragment: WeakReference<Fragment>?
) : AsyncTask<Unit, Int, List<News>>() {

    override fun doInBackground(vararg params: Unit?): List<News>? {
        try {
            Thread.sleep(SLEEP_TIME)
            return fragment?.get()?.context?.let {
                JsonHelper.getNews(it)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Thread.currentThread().interrupt()
        }
        return null
    }

    override fun onPostExecute(result: List<News>?) {
        super.onPostExecute(result)
        result?.let {
            (fragment?.get() as NewsFragment).updateNews(it)
        }
        instance = null
    }

    companion object {
        private var instance: NewsAsyncTask? = null
        private const val SLEEP_TIME = 5000L

        fun getInstance(fragment: WeakReference<Fragment>): NewsAsyncTask {
            when (instance) {
                null -> instance = NewsAsyncTask(fragment)
                else -> instance?.fragment = fragment
            }
            return instance as NewsAsyncTask
        }
    }
}
