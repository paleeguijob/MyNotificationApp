package aof.rp.mynotificationapp.domain.repositories.mynotification

import aof.rp.mynotificationapp.datasouce.mynotifications.notifications.NotificationItem
import aof.rp.mynotificationapp.datasouce.mynotifications.userprofile.UserProfile

interface MyNotificationRepository {

    suspend fun getUserProfile(): UserProfile
    suspend fun getNotificationList(userId: String): List<NotificationItem>
}