package aof.rp.mynotificationapp.ui.landing.uimodel

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import aof.rp.mynotificationapp.base.model.BaseCommonError

class LandingNotificationsUi(val uiState: UiState = UiState.Loading) {

    sealed class UiState {
        data object Loading : UiState()
        data class Success(val notificationData: ContentUi) : UiState()
        data class Error(val error: BaseCommonError) : UiState()
    }

    data class ContentUi(
        val profile: Header,
        val notifications: List<Notification>
    ) {
        data class Header(
            val name: String? = "",
            @DrawableRes val profileImage: Int? = null,
            val avatar: String? = "",
            val like: Int? = 0,
            val follow: Int? = 0,
            val follower: Int? = 0
        )

        data class Notification(
            val title: String? = "",
            val timestamp: String? = "",
            @ColorRes val bgColor: Int? = null
        )
    }
}