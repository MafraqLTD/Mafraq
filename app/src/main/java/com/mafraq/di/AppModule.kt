package com.mafraq.di

import android.content.Context
import android.content.SharedPreferences
import com.altaie.gls.GLSManager
import com.altaie.gls.di.GLSInitializer
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideGlsManager(@ApplicationContext context: Context): GLSManager =
        GLSInitializer(context).create()

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideUserPreference(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(
            USER_PREFERENCES,
            Context.MODE_PRIVATE
        )

    private const val USER_PREFERENCES = "user_preferences"
}
