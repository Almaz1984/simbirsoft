package com.almaz.task1.utils

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import com.almaz.task1.ui.help.HelpFragment
import java.lang.ref.WeakReference
import java.util.concurrent.Executors.newCachedThreadPool

class CategoriesTaskExecutor(
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
                val categories = fragment?.get()?.context?.let { JsonHelper.getCategories(it) }
                postOnUiThread { (fragment?.get() as HelpFragment).updateCategories(categories) }
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
        private var instance: CategoriesTaskExecutor? = null

        fun getInstance(fragment: WeakReference<Fragment>): CategoriesTaskExecutor {
            when (instance) {
                null -> instance = CategoriesTaskExecutor(fragment)
                else -> instance?.fragment = fragment
            }
            return instance as CategoriesTaskExecutor
        }
    }
}
