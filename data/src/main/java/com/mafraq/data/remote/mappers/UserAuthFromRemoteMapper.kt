package com.mafraq.data.remote.mappers

import com.altaie.prettycode.core.mapper.base.Mapper
import com.google.firebase.auth.FirebaseUser
import com.mafraq.data.entities.login.AuthUser
import javax.inject.Inject


class UserAuthFromRemoteMapper @Inject constructor() :
    Mapper<FirebaseUser, AuthUser> {

    override fun map(from: FirebaseUser) = from.run {
        AuthUser(
            id = uid,
            email = email,
            name = displayName,
            phoneNumber = phoneNumber,
            photoUrl = photoUrl?.toString(),
            isEmailVerified = isEmailVerified,
        )
    }
}
