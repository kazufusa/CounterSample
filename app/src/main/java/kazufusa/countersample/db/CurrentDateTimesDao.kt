package kazufusa.countersample.db

import android.arch.persistence.room.*
import kazufusa.countersample.vo.CurrentDateTime
import java.util.*

@Dao
@TypeConverters(DataTypeConverters::class)
abstract class CurrentDateTimesDao {
    @Query("select * from current_date_times order by gid desc limit 1")
    abstract fun getCurrent(): CurrentDateTime?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(currentDateTime: CurrentDateTime)

    @Query("update current_date_times set date = :date where gid = (select max(gid) from current_date_times)")
    abstract fun updateLatest(date: Date)
}