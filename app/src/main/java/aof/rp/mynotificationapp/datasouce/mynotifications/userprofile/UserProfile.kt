package aof.rp.mynotificationapp.datasouce.mynotifications.userprofile


data class UserProfile(
    val userId: String? = "",
    val avatar: String? = "",
    val firstName: String? = "",
    val lastName: String? = "",
    val likes: Int? = 0,
    val followers: Int? = 0,
    val following: Int? = 0
)