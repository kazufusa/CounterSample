package kazufusa.countersample.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import kazufusa.countersample.vo.CurrentDateTime

@Database(entities = [CurrentDateTime::class], version = 1)
abstract class CurrentDateTimesDb : RoomDatabase() {

    abstract fun currentDateTimesDao(): CurrentDateTimesDao

    companion object {
        @Volatile private var INSTANCE: CurrentDateTimesDb? = null

        fun getInstance(context: Context): CurrentDateTimesDb =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        CurrentDateTimesDb::class.java, "current_date_times.db")
                        .build()

        private fun buildInMemoryDatabase(context: Context) =
                Room.inMemoryDatabaseBuilder(context.applicationContext,
                        CurrentDateTimesDb::class.java).build()
    }
}
