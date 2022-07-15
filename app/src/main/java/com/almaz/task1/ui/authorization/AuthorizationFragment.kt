package com.almaz.task1.ui.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.almaz.task1.BottomNavigationViewHelper
import com.almaz.task1.R
import com.almaz.task1.databinding.FragmentAuthorizationBinding
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class AuthorizationFragment : Fragment() {

    private lateinit var binding: FragmentAuthorizationBinding
    private lateinit var credentialsDisposable: Disposable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthorizationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLoginButton()
        setupAuthorizationToolbar()
        setupObservables()
    }

    override fun onStop() {
        super.onStop()
        credentialsDisposable.dispose()
    }

    private fun setupAuthorizationToolbar() {
        binding.authorizationToolbar.setNavigationOnClickListener {
            (context as FragmentActivity).onBackPressed()
        }
    }

    private fun setupLoginButton() {
        binding.loginButton.apply {
            isEnabled = false
            isClickable = false
            setBackgroundColor(resources.getColor(R.color.grey, context.theme))
            setOnClickListener { BottomNavigationViewHelper.init() }
        }
    }

    private fun setupObservables() {
        val emailObservable: Observable<String> =
            RxTextView.textChangeEvents(binding.inputEmail)
                .skipInitialValue()
                .map { it.text().toString() }
                .debounce(DEBOUNCE_TIMEOUT, TimeUnit.MILLISECONDS)

        val passwordObservable: Observable<String> =
            RxTextView.textChangeEvents(binding.inputPassword)
                .skipInitialValue()
                .map { it.text().toString() }
                .debounce(DEBOUNCE_TIMEOUT, TimeUnit.MILLISECONDS)

        credentialsDisposable =
            Observable.combineLatest(emailObservable, passwordObservable) { email, password ->
                email.length >= MIN_CREDENTIALS_LENGTH && password.length >= MIN_CREDENTIALS_LENGTH
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { updateButtonState(it) }
    }

    private fun updateButtonState(it: Boolean) {
        binding.loginButton.apply {
            isEnabled = it
            isClickable = it
            when (it) {
                true -> setBackgroundColor(resources.getColor(R.color.leaf, context.theme))
                false -> setBackgroundColor(resources.getColor(R.color.grey, context.theme))
            }
        }
    }

    companion object {
        private const val DEBOUNCE_TIMEOUT = 200L
        private const val MIN_CREDENTIALS_LENGTH = 6
    }
}
