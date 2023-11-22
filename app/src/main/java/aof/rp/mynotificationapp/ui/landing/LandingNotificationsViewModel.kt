package aof.rp.mynotificationapp.ui.landing

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import aof.rp.mynotificationapp.base.model.toBaseCommonError
import aof.rp.mynotificationapp.datasouce.mynotifications.userprofile.UserProfile
import aof.rp.mynotificationapp.domain.mapper.mynotification.MyNotificationMapper
import aof.rp.mynotificationapp.domain.usecase.notifications.GetNotificationListUseCase
import aof.rp.mynotificationapp.domain.usecase.userprofile.GetUserProfileUseCase
import aof.rp.mynotificationapp.ui.landing.uimodel.LandingNotificationsUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandingNotificationsViewModel @Inject constructor(
    private val getUserProfile: GetUserProfileUseCase,
    private val getNotificationList: GetNotificationListUseCase,
    private val myNotificationMapper: MyNotificationMapper
) : ViewModel() {

    val landingNotificationsUi = MutableLiveData(LandingNotificationsUi())

    fun fetchUserId() {
        landingNotificationsUi.value =
            LandingNotificationsUi(LandingNotificationsUi.UiState.Loading)

        viewModelScope.launch {
            getUserProfile.execute(Unit)
                .onSuccess { userProfileResponse ->
                    fetchNotificationList(userProfileResponse = userProfileResponse)
                }
                .onFailure {
                    landingNotificationsUi.value = LandingNotificationsUi(
                        uiState = LandingNotificationsUi.UiState.Error(
                            error = it.toBaseCommonError()
                        )
                    )
                }
        }
    }

    private fun fetchNotificationList(userProfileResponse: UserProfile) {
        viewModelScope.launch {
            getNotificationList.execute(GetNotificationListUseCase.Input(userProfileResponse.userId.orEmpty()))
                .onSuccess { notificationListResponse ->
                    landingNotificationsUi.value = LandingNotificationsUi(
                        uiState = LandingNotificationsUi.UiState.Success(
                            myNotificationMapper.notificationProfileMap(
                                userProfileResponse = userProfileResponse,
                                notificationListResponse = notificationListResponse
                            )
                        )
                    )
                }
                .onFailure {
                    landingNotificationsUi.value = LandingNotificationsUi(
                        uiState = LandingNotificationsUi.UiState.Error(
                            error = it.toBaseCommonError()
                        )
                    )
                }
        }
    }
}