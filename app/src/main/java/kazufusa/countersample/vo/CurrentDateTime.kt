package kazufusa.countersample.vo

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import kazufusa.countersample.db.DataTypeConverters
import java.util.*

@Entity(tableName = "current_date_times")
@TypeConverters(DataTypeConverters::class)
data class CurrentDateTime(
    @PrimaryKey(autoGenerate = true)
    val gid: Int?,
    val id: String,
    val it: Double,
    val st: Double,
    val leap: Int,
    val next: Int,
    val step: Int,
    val date: Date?
)
