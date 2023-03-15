package com.muvidb.app.base.exception

class ApiErrorException(
    override val message: String? = null,
    val httpCode: Int? = null
) : Exception()