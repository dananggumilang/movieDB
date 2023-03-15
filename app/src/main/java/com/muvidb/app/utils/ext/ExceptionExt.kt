package com.muvidb.app.utils.ext

import android.content.Context
import com.muvidb.app.R
import com.muvidb.app.base.exception.ApiErrorException
import com.muvidb.app.base.exception.NoInternetConnectionException

fun Context.getErrorMessage(exception : Exception): String {
    return when (exception) {
        is NoInternetConnectionException -> getString(R.string.app_name)
        is ApiErrorException -> exception.message.orEmpty()
        else -> getString(R.string.app_name)
    }
}