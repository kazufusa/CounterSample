package kazufusa.countersample.vo

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "counters")
data class Counter (
        @PrimaryKey
        var id : Int,
        var count: Int = 0
)