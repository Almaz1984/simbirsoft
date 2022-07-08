package com.almaz.task1.utils.extensions

import io.reactivex.Observable
import io.reactivex.disposables.Disposable

fun <T : Any> Observable<T>.subscribe(
    onError: (Throwable) -> Unit,
    onComplete: () -> Unit,
    onNext: (T) -> Unit
): Disposable = subscribe(onNext, onError, onComplete)
