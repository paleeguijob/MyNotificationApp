package aof.rp.mynotificationapp.service

import aof.rp.mynotificationapp.base.model.BaseCommonError
import aof.rp.mynotificationapp.base.model.NetworkResponse
import aof.rp.mynotificationapp.datasouce.mynotifications.notifications.NotificationItem
import aof.rp.mynotificationapp.datasouce.mynotifications.userprofile.UserProfile
import retrofit2.http.GET
import retrofit2.http.Path

interface MyNotificationsService {

    @GET("user/profile")
    suspend fun getUserProfile(): NetworkResponse<UserProfile, BaseCommonError>

    @GET("users/{userId}/notifications")
    suspend fun getNotificationList(
        @Path("userId") userId: String
    ): NetworkResponse<List<NotificationItem>, BaseCommonError>

}