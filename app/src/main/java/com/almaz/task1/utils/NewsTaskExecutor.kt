package com.almaz.task1.utils

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import com.almaz.task1.ui.news.NewsFragment
import java.lang.ref.WeakReference
import java.util.concurrent.Executors.newCachedThreadPool

class NewsTaskExecutor(
    private var fragment: WeakReference<Fragment>?
) {
    private var executor = newCachedThreadPool()

    private fun postOnUiThread(runnable: Runnable) {
        val mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post(runnable)
    }

    fun execute() {
        executor.execute {
            try {
                Thread.sleep(5000)
                val newsList = fragment?.get()?.context?.let { JsonHelper.getNews(it) }
                postOnUiThread { (fragment?.get() as NewsFragment).updateNews(newsList) }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    fun shutdown() {
        executor.shutdownNow()
        instance = null
    }

    companion object {
        private var instance: NewsTaskExecutor? = null

        fun getInstance(fragment: WeakReference<Fragment>): NewsTaskExecutor {
            when (instance) {
                null -> instance = NewsTaskExecutor(fragment)
                else -> instance?.fragment = fragment
            }
            return instance as NewsTaskExecutor
        }
    }
}
