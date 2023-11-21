package aof.rp.mynotificationapp.domain.mapper.mynotification

import android.os.Build
import androidx.annotation.RequiresApi
import aof.rp.mynotificationapp.R
import aof.rp.mynotificationapp.base.extensions.toLocalDateTime
import aof.rp.mynotificationapp.base.extensions.toLocalDateTimeBySplit
import aof.rp.mynotificationapp.datasouce.mynotifications.notifications.NotificationItem
import aof.rp.mynotificationapp.datasouce.mynotifications.userprofile.UserProfile
import aof.rp.mynotificationapp.ui.landing.uimodel.LandingNotificationsUi
import javax.inject.Inject

class MyNotificationMapperImp @Inject constructor() : MyNotificationMapper {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun notificationProfileMap(
        userProfileResponse: UserProfile,
        notificationListResponse: List<NotificationItem>
    ): LandingNotificationsUi.ContentUi = LandingNotificationsUi.ContentUi(
        profile = mapHeader(userProfileResponse),
        notifications = mapNotification(notificationListResponse)
    )

    private fun mapHeader(response: UserProfile): LandingNotificationsUi.ContentUi.Header =
        LandingNotificationsUi.ContentUi.Header(
            name = "${response.firstName} ${response.lastName}",
            avatar = response.avatar,
            like = response.likes,
            follower = response.followers,
            follow = response.following
        )

    @RequiresApi(Build.VERSION_CODES.O)
    private fun mapNotification(response: List<NotificationItem>): List<LandingNotificationsUi.ContentUi.Notification> =
        response.mapIndexed { index, notification ->
            LandingNotificationsUi.ContentUi.Notification(
                title = notification.text,
                timestamp = transformStringFormat(notification.created.orEmpty()),
                bgColor = if (index % 2 == 0) R.color.light_gray_color else R.color.white_color
            )
        }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun transformStringFormat(dateTime: String): String {
        //Date format 2019-05-29T03:25:00.000Z
        //Handle Wrong format to YYYY-MM-DD
        val dateFilter = dateTime.split("T")
        val dateFilterResult = dateFilter[0].toLocalDateTimeBySplit("-")
        val dateResult = "${dateFilterResult}T${dateFilter[1]}"

        val date = dateResult.toLocalDateTime("dd MMM yyyy").lowercase()
        val time = dateResult.toLocalDateTime("HH:mm a").uppercase()

        return "$date   $time"
    }
}