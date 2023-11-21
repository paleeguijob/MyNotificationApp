package aof.rp.mynotificationapp.domain.usecase.notifications

import aof.rp.mynotificationapp.base.BaseUnitTest
import aof.rp.mynotificationapp.datasouce.mynotifications.notifications.NotificationItem
import aof.rp.mynotificationapp.domain.repositories.mynotification.MyNotificationRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.jupiter.api.Assertions.*


@ExperimentalCoroutinesApi
class GetNotificationListUseCaseTest : BaseUnitTest() {

    private val repository: MyNotificationRepository = mockk()

    private lateinit var getNotificationListUseCase: GetNotificationListUseCase

    override fun setup() {
        super.setup()

        getNotificationListUseCase = GetNotificationListUseCase(repository)
    }

    @Test
    fun get_notification_list_use_case_test() = runTest {
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

        coEvery { repository.getNotificationList(userId) } returns notificationList

        //When
        val response = getNotificationListUseCase.execute(
            GetNotificationListUseCase.Input(userId)
        )

        //Then
        assertEquals(notificationList, response.getOrNull())

    }
}