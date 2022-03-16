package com.almaz.task1.ui.news

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.almaz.task1.BottomNavigationViewHelper
import com.almaz.task1.R
import com.almaz.task1.data.model.News

class NewsDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val news = arguments?.getParcelable<News>(NEWS_KEY)
        news?.let { bindNews(view, it) } ?: return
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        BottomNavigationViewHelper.hideBottomNavigationView()
    }

    override fun onDetach() {
        BottomNavigationViewHelper.showBottomNavigationView()
        super.onDetach()
    }

    private fun bindNews(view: View, news: News) {
        view.apply {
            findViewById<androidx.appcompat.widget.Toolbar>(R.id.news_detail_toolbar).apply {
                setNavigationOnClickListener { (context as FragmentActivity).onBackPressed() }
            }
            findViewById<TextView>(R.id.news_detail_toolbar_title).apply {
                text = news.title
                isSelected = true
            }
            findViewById<TextView>(R.id.news_detail_title).apply {
                text = news.title
            }
            findViewById<TextView>(R.id.news_detail_date).apply {
                text = news.date
            }
            findViewById<TextView>(R.id.news_detail_fund_name).apply {
                text = news.fundName
            }
            findViewById<TextView>(R.id.news_detail_address).apply {
                text = news.address
            }
            findViewById<TextView>(R.id.news_detail_phones).apply {
                text = news.phone
            }
            findViewById<ImageView>(R.id.news_detail_main_image).apply {
                setImageDrawable(context.getDrawable(news.image))
            }
            findViewById<TextView>(R.id.news_detail_description).apply {
                text = news.description
            }
        }
    }

    private fun Context.getDrawable(image: String): Drawable? {
        val id = resources.getIdentifier(image, "drawable", packageName)
        return ContextCompat.getDrawable(this, id)
    }

    companion object {
        private const val NEWS_KEY = "News_fragment_key"

        @JvmStatic
        fun newInstance(news: News): NewsDetailFragment {
            return NewsDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(NEWS_KEY, news)
                }
            }
        }
    }
}
