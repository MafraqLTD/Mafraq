package com.mafraq.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.altaie.gls.GLSManager
import com.altaie.gls.di.GLSInitializer
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideGlsManager(@ApplicationContext context: Context): GLSManager =
        GLSInitializer(context).create()
}
