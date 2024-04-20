package com.mafraq.data.entities.profile

import androidx.annotation.StringRes
import com.mafraq.data.R
import com.mafraq.data.utils.titlecase


enum class Gender(@StringRes val nameResId: Int) {
    Male(nameResId = R.string.male),
    Female(nameResId = R.string.female);

    companion object {
        fun fromString(value: String?): Gender =
            when (value?.titlecase()) {
                Male.name -> Male
                Female.name -> Female
                else -> throw IllegalArgumentException("Unknown value: $value")
            }
    }
}
