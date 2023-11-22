package aof.rp.mynotificationapp.base.extensions

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.loadUrl(url: String?) {
    Glide.with(this)
        .load(url)
        .into(this)
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}