package kazufusa.countersample.db

import android.arch.persistence.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*


class DataTypeConverters {
    val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.ROOT)

    @TypeConverter
    fun fromDateString(dateString: String?): Date? {
        return dateString?.let { df.parse(it) }
    }

    @TypeConverter
    fun toDateString(date: Date?): String? {
        return date?.let { df.format(it) }
    }
}
