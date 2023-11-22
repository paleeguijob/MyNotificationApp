package aof.rp.mynotificationapp.base.customview.error.model

import androidx.annotation.DrawableRes

data class ErrorStateUi(
    @DrawableRes val alertIcon: Int,
    val message: String? = "",
    val buttonText: String? = "",
    val onClickedButton: () -> Unit = {}
)