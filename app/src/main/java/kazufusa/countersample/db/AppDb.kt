package kazufusa.countersample.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import kazufusa.countersample.vo.Counter
import kazufusa.countersample.vo.CurrentDateTime

@Database(entities = [Counter::class, CurrentDateTime::class], version = 1)
abstract class AppDb : RoomDatabase() {

    abstract fun countersDao(): CountersDao
    abstract fun currentDateTimesDao(): CurrentDateTimesDao

    companion object {
        @Volatile
        private var INSTANCE: AppDb? = null

        fun getInstance(context: Context): AppDb =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        AppDb::class.java, "app.db")
                        .build()
    }
}
