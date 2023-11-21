package aof.rp.mynotificationapp.domain.mapper.mynotification

import aof.rp.mynotificationapp.datasouce.mynotifications.notifications.NotificationItem
import aof.rp.mynotificationapp.datasouce.mynotifications.userprofile.UserProfile
import aof.rp.mynotificationapp.ui.landing.uimodel.LandingNotificationsUi

interface MyNotificationMapper {

    fun notificationProfileMap(
        userProfileResponse: UserProfile,
        notificationListResponse: List<NotificationItem>
    ): LandingNotificationsUi.ContentUi
}