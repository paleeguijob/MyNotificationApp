package aof.rp.mynotificationapp.domain.usecase.userprofile

import aof.rp.mynotificationapp.base.BaseUnitTest
import aof.rp.mynotificationapp.datasouce.mynotifications.userprofile.UserProfile
import aof.rp.mynotificationapp.domain.repositories.mynotification.MyNotificationRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

@ExperimentalCoroutinesApi
class GetUserProfileUseCaseTest : BaseUnitTest() {

    private val myNotificationRepository: MyNotificationRepository = mockk()

    private lateinit var getUserProfile: GetUserProfileUseCase

    override fun setup() {
        super.setup()

        getUserProfile = GetUserProfileUseCase(myNotificationRepository)
    }

    @Test
    fun get_user_profile_use_case() = runTest {
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
        coEvery { myNotificationRepository.getUserProfile() } returns userProfile

        //When
        val response = getUserProfile.execute(Unit)

        //Then
        assertEquals(userProfile, response.getOrNull())
    }
}