package com.mafraq.data.errors

import com.altaie.prettycode.core.exceptions.base.BaseException
import org.apache.http.HttpStatus


data class ValidationException(
    override val message: String?,
    override val code: Int = HttpStatus.SC_UNPROCESSABLE_ENTITY,
) : BaseException()
