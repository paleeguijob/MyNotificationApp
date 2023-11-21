package aof.rp.mynotificationapp.domain.usecase.notifications

import aof.rp.mynotificationapp.base.usecase.BaseSuspendUseCase
import aof.rp.mynotificationapp.datasouce.mynotifications.notifications.NotificationItem
import aof.rp.mynotificationapp.domain.repositories.mynotification.MyNotificationRepository
import javax.inject.Inject

class GetNotificationListUseCase @Inject constructor(private val repository: MyNotificationRepository) :
    BaseSuspendUseCase<GetNotificationListUseCase.Input, List<NotificationItem>>() {

    override suspend fun create(input: Input): List<NotificationItem> =
        repository.getNotificationList(input.userId)

    data class Input(val userId: String)
}