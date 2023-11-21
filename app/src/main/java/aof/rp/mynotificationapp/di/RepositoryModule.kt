package aof.rp.mynotificationapp.di

import aof.rp.mynotificationapp.domain.mapper.mynotification.MyNotificationMapper
import aof.rp.mynotificationapp.domain.mapper.mynotification.MyNotificationMapperImp
import aof.rp.mynotificationapp.domain.repositories.mynotification.MyNotificationRepository
import aof.rp.mynotificationapp.domain.repositories.mynotification.MyNotificationRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindMyNotificationRepository(repositoryImp: MyNotificationRepositoryImp): MyNotificationRepository

    @Binds
    abstract fun bindMyNotificationMapper(mapper: MyNotificationMapperImp): MyNotificationMapper
}