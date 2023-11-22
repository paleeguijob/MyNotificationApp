package aof.rp.mynotificationapp.domain.usecase.userprofile

import aof.rp.mynotificationapp.base.usecase.BaseSuspendUseCase
import aof.rp.mynotificationapp.datasouce.mynotifications.userprofile.UserProfile
import aof.rp.mynotificationapp.domain.repositories.mynotification.MyNotificationRepository
import javax.inject.Inject


class GetUserProfileUseCase @Inject constructor(
    private val repository: MyNotificationRepository
) : BaseSuspendUseCase<Unit, UserProfile>() {

    override suspend fun create(input: Unit): UserProfile =
        repository.getUserProfile()
}