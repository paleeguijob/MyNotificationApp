package aof.rp.mynotificationapp.base.extensions

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun String.toLocalDateTime(formatter: String): String =
    LocalDateTime.parse(this, DateTimeFormatter.ISO_DATE_TIME)
        .format(DateTimeFormatter.ofPattern(formatter))

@RequiresApi(Build.VERSION_CODES.O)
fun String.toLocalDateTimeBySplit(split: String): String {
    val date = this.split(split)
    val year = date[0]
    val month = transformToCorrectFormat(date[1])
    val day = transformToCorrectFormat(date[2])
    return "${year}${split}${month}${split}${day}"
}

private fun transformToCorrectFormat(dateValue: String): String {
    return if (dateValue.toInt() in 1..9) {
        "0${dateValue.toInt()}"
    } else {
        dateValue
    }
}
