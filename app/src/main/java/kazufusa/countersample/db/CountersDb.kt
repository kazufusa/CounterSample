package kazufusa.countersample.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import kazufusa.countersample.vo.Counter

@Database(entities = [Counter::class], version = 1)
abstract class CountersDb : RoomDatabase() {

    abstract fun countersDao(): CountersDao

    companion object {
        @Volatile
        private var INSTANCE: CountersDb? = null

        fun getInstance(context: Context): CountersDb =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        CountersDb::class.java, "counter.db")
                        .build()
    }
}
