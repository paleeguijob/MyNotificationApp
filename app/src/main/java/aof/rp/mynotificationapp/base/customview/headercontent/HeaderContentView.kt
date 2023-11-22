package aof.rp.mynotificationapp.base.customview.headercontent

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import aof.rp.mynotificationapp.R
import aof.rp.mynotificationapp.base.extensions.loadUrl
import aof.rp.mynotificationapp.base.extensions.orZero
import aof.rp.mynotificationapp.databinding.HeaderContentViewBinding
import aof.rp.mynotificationapp.ui.landing.uimodel.LandingNotificationsUi

class HeaderContentView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding by lazy {
        HeaderContentViewBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }

    fun setContentView(headerContentUi: LandingNotificationsUi.ContentUi.Header) {
        with(headerContentUi) {
            profileImage?.apply {
                setImageProfile(profileImage)
            } ?: setImageUrl(avatar)

            setName(name)
            setLike(like)
            setFollower(follower)
            setFollow(follow)
        }
    }

    fun setImageProfile(image: Int?) {
        binding.imageViewHeaderProfileImage.setImageDrawable(
            ContextCompat.getDrawable(context, image ?: R.drawable.ic_launcher_foreground)
        )
    }

    fun setImageUrl(url: String?) {
        binding.imageViewHeaderProfileImage.loadUrl(url)
    }

    fun setName(name: String?) {
        binding.textViewHeaderName.text = name.orEmpty()
    }

    fun setFollower(follower: Int?) {
        binding.textViewFollowerValue.text = follower.orZero().toString()
    }

    fun setLike(like: Int?) {
        binding.textViewLikeValue.text = like.orZero().toString()
    }

    fun setFollow(follow: Int?) {
        binding.textViewFollowValue.text = follow.orZero().toString()
    }
}