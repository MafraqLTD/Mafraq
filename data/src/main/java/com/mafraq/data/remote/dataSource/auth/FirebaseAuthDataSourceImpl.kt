package com.mafraq.data.remote.dataSource.auth

import androidx.core.net.toUri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest
import com.mafraq.data.entities.login.AuthUser
import com.mafraq.data.entities.login.LoginBody
import com.mafraq.data.entities.register.RegisterBody
import com.mafraq.data.local.session.SessionLocalDataSource
import com.mafraq.data.remote.mappers.UserAuthFromRemoteMapper
import com.mafraq.data.utils.awaitBoolean
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class FirebaseAuthDataSourceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val userAuthFromRemoteMapper: UserAuthFromRemoteMapper,
    private val sessionLocalDataSource: SessionLocalDataSource
) : FirebaseAuthDataSource {
    override val currentUser: AuthUser?
        get() = auth.currentUser?.let(userAuthFromRemoteMapper::map)

    override suspend fun login(body: LoginBody): Boolean {
        val isSuccess = auth.signInWithEmailAndPassword(body.email, body.password)
            .awaitBoolean("signInWithEmailAndPassword()")

        if (isSuccess)
            sessionLocalDataSource.save(
                email = body.email,
                subscriptionId = null,
                driverId = null
            )
        return isSuccess
    }

    override suspend fun register(body: RegisterBody): Boolean {
        val isSuccess = auth.createUserWithEmailAndPassword(body.email, body.password)
            .awaitBoolean("createUserWithEmailAndPassword()")

        if (isSuccess) {
            auth.currentUser?.sendEmailVerification()
                ?.awaitBoolean("sendEmailVerification()")
            sessionLocalDataSource.save(
                email = body.email,
                subscriptionId = null,
                driverId = null
            )
        }

        return isSuccess
    }

    override suspend fun logout(): Boolean = suspendCoroutine { continuation ->
        auth.signOut()
        FirebaseAuth.AuthStateListener {
            continuation.resume(auth.currentUser == null)
        }
    }

    override suspend fun updateUser(user: AuthUser): Boolean {
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
        return sessionLocalDataSource.get() != null
    }
}
