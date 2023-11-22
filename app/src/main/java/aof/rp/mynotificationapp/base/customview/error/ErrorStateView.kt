package aof.rp.mynotificationapp.base.customview.error

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import aof.rp.mynotificationapp.R
import aof.rp.mynotificationapp.base.customview.error.model.ErrorStateUi
import aof.rp.mynotificationapp.databinding.ViewErrorStateBinding

class ErrorStateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding by lazy {
        ViewErrorStateBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }

    fun setView(errorStateUi: ErrorStateUi) {
        with(errorStateUi) {
            setAlertIcon(alertIcon)
            setMessage(message)
            setButtonText(buttonText)
            setupButtonListener(errorStateUi.onClickedButton)
        }
    }

    fun setAlertIcon(@DrawableRes icon: Int) {
        binding.imageViewErrorImage.setImageDrawable(ContextCompat.getDrawable(context, icon))
    }

    fun setButtonText(text: String?) {
        binding.buttonTryAgain.text = text ?: ContextCompat.getString(
            context,
            R.string.my_notification_common_try_again_button
        )
    }

    fun setMessage(message: String?) {
        binding.textViewMessageError.text = message ?: ContextCompat.getString(
            context,
            R.string.my_notification_common_error_offline_message
        )
    }

    private fun setupButtonListener(onClickedButton: () -> Unit) {
        binding.buttonTryAgain.setOnClickListener {
            onClickedButton.invoke()
        }
    }
}