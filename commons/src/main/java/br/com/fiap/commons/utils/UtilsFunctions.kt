package br.com.movieapp.core.utils

import timber.log.Timber

object UtilsFunctions {

    fun logError(tag: String, message: String) {
        Timber.tag(tag).e("Error -> $message")
    }

    fun logInfo(tag: String, message: String) {
        Timber.tag(tag).e("Info -> $message")
    }
}