package br.com.movieapp.core.utils

import android.util.Log
import br.com.movieapp.BuildConfig
import timber.log.Timber

object UtilsFunctions {

    fun logError(tag: String, message: String) {
        Timber.tag(tag).e("Error -> $message")
    }

    fun logInfo(tag: String, message: String) {
        Timber.tag(tag).e("Info -> $message")
    }
}