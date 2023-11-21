package aof.rp.mynotificationapp.di

import aof.rp.mynotificationapp.service.MyNotificationsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideMyNotificationService(retrofit: Retrofit): MyNotificationsService =
        retrofit.create(MyNotificationsService::class.java)
}