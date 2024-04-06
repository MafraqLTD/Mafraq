package com.mafraq.data.remote.dataSource.user

import androidx.core.net.toUri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest
import com.mafraq.data.entities.login.LoginBody
import com.mafraq.data.entities.login.User
import com.mafraq.data.entities.register.RegisterBody
import com.mafraq.data.remote.mappers.UserAuthFromRemoteMapper
import com.mafraq.data.utils.awaitBoolean
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class FirebaseAuthDataSourceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val userAuthFromRemoteMapper: UserAuthFromRemoteMapper
) : FirebaseAuthDataSource {
    override val currentUser: User?
        get() = auth.currentUser?.let(userAuthFromRemoteMapper::map)

    override suspend fun login(body: LoginBody): Boolean =
        auth.signInWithEmailAndPassword(body.email, body.password)
            .awaitBoolean("signInWithEmailAndPassword()")

    override suspend fun register(body: RegisterBody): Boolean {
        val isSuccess = auth.createUserWithEmailAndPassword(body.email, body.password)
            .awaitBoolean("createUserWithEmailAndPassword()")

        if (isSuccess)
            auth.currentUser?.sendEmailVerification()
                ?.awaitBoolean("sendEmailVerification()")

        return isSuccess
    }

    override suspend fun logout(): Boolean = suspendCoroutine { continuation ->
        auth.signOut()
        FirebaseAuth.AuthStateListener {
            continuation.resume(auth.currentUser == null)
        }
    }

    override suspend fun updateUser(user: User): Boolean {
        var isProfileUpdated = false
        var isUpdatedSuccessful = false
        val firebaseUser: FirebaseUser = auth.currentUser ?: return false

        user.email?.let {
            isUpdatedSuccessful = firebaseUser.verifyBeforeUpdateEmail(it)
                .awaitBoolean("updateEmail()")
        }

        val profileUpdates = userProfileChangeRequest {
            user.name?.run {
                displayName = this
                isProfileUpdated = true
            }

            user.photoUrl?.toUri().run {
                photoUri = this
                isProfileUpdated = true
            }
        }

        if (isProfileUpdated)
            isUpdatedSuccessful = firebaseUser.updateProfile(profileUpdates)
                .awaitBoolean("updateProfile()")

        return isUpdatedSuccessful
    }

    override fun isAuthorized(): Boolean {
        auth.currentUser?.reload()
        return auth.currentUser != null
    }
}
