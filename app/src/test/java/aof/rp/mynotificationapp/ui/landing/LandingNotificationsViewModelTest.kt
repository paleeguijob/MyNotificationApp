package aof.rp.mynotificationapp.ui.landing

import aof.rp.mynotificationapp.base.BaseUnitTest
import aof.rp.mynotificationapp.base.getOrAwaitValue
import aof.rp.mynotificationapp.base.model.toBaseCommonError
import aof.rp.mynotificationapp.datasouce.mynotifications.notifications.NotificationItem
import aof.rp.mynotificationapp.datasouce.mynotifications.userprofile.UserProfile
import aof.rp.mynotificationapp.domain.mapper.mynotification.MyNotificationMapperImp
import aof.rp.mynotificationapp.domain.usecase.notifications.GetNotificationListUseCase
import aof.rp.mynotificationapp.domain.usecase.userprofile.GetUserProfileUseCase
import aof.rp.mynotificationapp.ui.landing.uimodel.LandingNotificationsUi
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

@ExperimentalCoroutinesApi
class LandingNotificationsViewModelTest : BaseUnitTest() {

    private val getNotificationList: GetNotificationListUseCase = mockk()

    private val getUserProfile: GetUserProfileUseCase = mockk()

    private val myNotificationMapper = MyNotificationMapperImp()

    private lateinit var landingNotificationsViewModel: LandingNotificationsViewModel

    @ExperimentalCoroutinesApi
    override fun setup() {
        super.setup()

        landingNotificationsViewModel = LandingNotificationsViewModel(
            getUserProfile,
            getNotificationList,
            myNotificationMapper
        )
    }

    @Test
    fun fetch_my_notifications() = runTest {
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

        val expectedTestData = LandingNotificationsUi.UiState.Success(
            myNotificationMapper.notificationProfileMap(userProfile, notificationList)
        )

        coEvery { getUserProfile.execute(Unit) } returns Result.success(userProfile)
        coEvery {
            getNotificationList.execute(GetNotificationListUseCase.Input(userProfile.userId.orEmpty()))
        } returns Result.success(value = notificationList)

        //When
        landingNotificationsViewModel.fetchUserId()

        //Then
        val response = landingNotificationsViewModel.landingNotificationsUi.getOrAwaitValue()

        assertEquals(
            expectedTestData,
            response.uiState
        )
    }

    @Test
    fun fetch_my_notification_list_get_user_profile_failure() = runTest {
        //Given
        val error = Throwable(message = "Are you offline")
        val expectedErrorData = LandingNotificationsUi.UiState.Error(error.toBaseCommonError())

        coEvery { getUserProfile.execute(Unit) } returns Result.failure(exception = error)

        //When
        landingNotificationsViewModel.fetchUserId()

        //Then
        val errorResponse = landingNotificationsViewModel.landingNotificationsUi.getOrAwaitValue()

        assertEquals(
            expectedErrorData,
            errorResponse.uiState
        )
    }

    @Test
    fun fetch_my_notification_list_get_notification_list_failure() = runTest {
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
        val error = Throwable(message = "Are you offline")
        val expectedErrorData = LandingNotificationsUi.UiState.Error(error.toBaseCommonError())

        coEvery { getUserProfile.execute(Unit) } returns Result.success(value = userProfile)
        coEvery {
            getNotificationList.execute(GetNotificationListUseCase.Input(userProfile.userId.orEmpty()))
        } returns Result.failure(exception = error)

        //When
        landingNotificationsViewModel.fetchUserId()

        //Then
        val errorResponse = landingNotificationsViewModel.landingNotificationsUi.getOrAwaitValue()

        assertEquals(
            expectedErrorData,
            errorResponse.uiState
        )
    }
}