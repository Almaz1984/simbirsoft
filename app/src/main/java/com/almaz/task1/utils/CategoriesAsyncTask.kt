@file:Suppress("DEPRECATION")

package com.almaz.task1.utils

import android.os.AsyncTask
import androidx.fragment.app.Fragment
import com.almaz.task1.data.model.HelpCategory
import com.almaz.task1.ui.help.HelpFragment
import java.lang.ref.WeakReference

class CategoriesAsyncTask(
    private var fragment: WeakReference<Fragment>?
) : AsyncTask<Unit, Int, List<HelpCategory>>() {

    override fun doInBackground(vararg params: Unit?): List<HelpCategory>? {
        try {
            Thread.sleep(SLEEP_TIME)
            return fragment?.get()?.context?.let {
                JsonHelper.getCategories(it)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Thread.currentThread().interrupt()
        }
        return null
    }

    override fun onPostExecute(result: List<HelpCategory>?) {
        super.onPostExecute(result)
        result?.let {
            (fragment?.get() as HelpFragment).updateCategories(it)
        }
        instance = null
    }

    companion object {
        private var instance: CategoriesAsyncTask? = null
        private const val SLEEP_TIME = 5000L

        fun getInstance(fragment: WeakReference<Fragment>): CategoriesAsyncTask {
            when (instance) {
                null -> instance = CategoriesAsyncTask(fragment)
                else -> instance?.fragment = fragment
            }
            return instance as CategoriesAsyncTask
        }
    }
}
