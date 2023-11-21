package aof.rp.mynotificationapp.ui.landing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import aof.rp.mynotificationapp.R
import aof.rp.mynotificationapp.databinding.ItemNotificationBinding
import aof.rp.mynotificationapp.ui.landing.uimodel.LandingNotificationsUi

class LandingNotificationAdapter :
    RecyclerView.Adapter<LandingNotificationAdapter.NotificationViewHolder>() {

    val differ = AsyncListDiffer(this, NotificationsDiffCallBack())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder =
        NotificationViewHolder(
            ItemNotificationBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    inner class NotificationViewHolder(
        private val binding: ItemNotificationBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LandingNotificationsUi.ContentUi.Notification) {
            with(binding) {
                root.setBackgroundResource(item.bgColor ?: R.color.white_color)
                textViewNotificationTitle.text = item.title
                textViewNotificationTimestamp.text = item.timestamp
            }
        }
    }
}

class NotificationsDiffCallBack :
    DiffUtil.ItemCallback<LandingNotificationsUi.ContentUi.Notification>() {
    override fun areItemsTheSame(
        oldItem: LandingNotificationsUi.ContentUi.Notification,
        newItem: LandingNotificationsUi.ContentUi.Notification
    ): Boolean = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: LandingNotificationsUi.ContentUi.Notification,
        newItem: LandingNotificationsUi.ContentUi.Notification
    ): Boolean = oldItem.title == newItem.title && oldItem.timestamp == newItem.timestamp

}