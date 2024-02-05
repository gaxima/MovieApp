package br.com.movieapp.core.utils

import br.com.movieapp.core.network.BuildConfig


fun String?.toPostUrl() = "${BuildConfig.BASE_URL_IMAGE}$this"

fun String?.toBackdropUrl() = "${BuildConfig.BASE_URL_IMAGE}$this"