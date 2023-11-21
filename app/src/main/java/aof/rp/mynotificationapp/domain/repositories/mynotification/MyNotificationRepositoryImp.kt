package aof.rp.mynotificationapp.domain.repositories.mynotification

import aof.rp.mynotificationapp.base.model.toDataOrThrow
import aof.rp.mynotificationapp.datasouce.mynotifications.notifications.NotificationItem
import aof.rp.mynotificationapp.datasouce.mynotifications.userprofile.UserProfile
import aof.rp.mynotificationapp.service.MyNotificationsService
import javax.inject.Inject


class MyNotificationRepositoryImp @Inject constructor(
    private val myNotificationService: MyNotificationsService
) : MyNotificationRepository {

    override suspend fun getUserProfile(): UserProfile =
        myNotificationService.getUserProfile().toDataOrThrow()

    override suspend fun getNotificationList(userId: String): List<NotificationItem> =
        myNotificationService.getNotificationList(userId).toDataOrThrow()
}