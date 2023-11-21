package aof.rp.mynotificationapp.domain.repositories.mynotification

import aof.rp.mynotificationapp.base.BaseUnitTest
import aof.rp.mynotificationapp.base.model.NetworkResponse
import aof.rp.mynotificationapp.datasouce.mynotifications.notifications.NotificationItem
import aof.rp.mynotificationapp.datasouce.mynotifications.userprofile.UserProfile
import aof.rp.mynotificationapp.service.MyNotificationsService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

@ExperimentalCoroutinesApi
class MyNotificationRepositoryTest : BaseUnitTest() {


    private val myNotificationService: MyNotificationsService = mockk()

    private lateinit var repository: MyNotificationRepository

    @ExperimentalCoroutinesApi
    @Before
    override fun setup() {
        super.setup()

        repository = MyNotificationRepositoryImp(myNotificationService)
    }

    @Test
    fun get_user_profile() = runTest {
        //Given
        val userProfile = UserProfile(
            "userId",
            "avatar",
            "Real AOF",
            "Real HON",
            1,
            1,
            1
        )

        //When
        coEvery { myNotificationService.getUserProfile() } returns NetworkResponse.Success(
            userProfile
        )

        //Then
        assertEquals(userProfile, repository.getUserProfile())

    }

    @Test
    fun get_notification_list() = runTest {
        //Given
        val notificationList = listOf(
            NotificationItem(
                text = "Real AOF Love Real HON",
                created = "2023-11-5T18:55:00.000Z"
            ),
            NotificationItem(
                text = "Real HON Love Real AOF",
                created = "2023-12-4T18:55:00.000Z"
            ),
            NotificationItem(
                text = "Real AOF Love Real HON",
                created = "2024-1-16T18:55:00.000Z"
            )
        )
        val userId = "userId"

        //When
        coEvery { myNotificationService.getNotificationList(userId) } returns NetworkResponse.Success(
            notificationList
        )

        //Then
        assertEquals(notificationList, repository.getNotificationList(userId))
    }
}