package com.mafraq.data.remote.mappers

import com.altaie.prettycode.core.mapper.base.Mapper
import com.google.firebase.auth.FirebaseUser
import com.mafraq.data.entities.login.User
import javax.inject.Inject


class UserAuthFromRemoteMapper @Inject constructor() :
    Mapper<FirebaseUser, User> {

    override fun map(from: FirebaseUser) = from.run {
        User(
            id = uid,
            email = email,
            name = displayName,
            phoneNumber = phoneNumber,
            photoUrl = photoUrl?.toString(),
            isEmailVerified = isEmailVerified,
        )
    }
}
