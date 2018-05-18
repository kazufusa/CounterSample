package kazufusa.countersample.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import kazufusa.countersample.vo.Counter

@Dao
abstract class CountersDao {
    @Query("select * from counters where id = :id")
    abstract fun getCounterById(id: Int): Counter?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCounter(counter: Counter)
}