package kazufusa.countersample

import android.app.Application
import android.arch.lifecycle.*
import android.arch.persistence.room.*
import kotlinx.coroutines.experimental.launch
import android.content.Context
import javax.inject.Inject

class CounterViewModel @Inject constructor(val dataSource: CountersDao) : ViewModel(), LifecycleObserver {
    private val notifier = MutableLiveData<Counter>()

    fun getCounter() : LiveData<Counter> {
        return notifier
    }

    fun increment() {
        launch {
            val counter = dataSource.getCounterById(ID)?: Counter(ID, 0)
            counter.count++
            dataSource.insertCounter(counter)
            notifier.postValue(counter)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() { increment() }

    companion object {
        const val ID = 1
    }
}

@Entity(tableName = "counters")
data class Counter (
        @PrimaryKey
        var id : Int,
        var count: Int = 0
)


@Database(entities = [Counter::class], version = 1)
abstract class CountersDatabase : RoomDatabase() {

    abstract fun countersDao(): CountersDao

    companion object {
        @Volatile private var INSTANCE: CountersDatabase? = null

        fun getInstance(context: Context): CountersDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        CountersDatabase::class.java, "counter.db")
                        .build()

        private fun buildInMemoryDatabase(context: Context) =
                Room.inMemoryDatabaseBuilder(context.applicationContext,
                        CountersDatabase::class.java).build()
    }
}

@Dao
abstract class CountersDao {
    @Query("select * from counters where id = :id")
    abstract fun getCounterById(id: Int): Counter?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCounter(counter: Counter)
}