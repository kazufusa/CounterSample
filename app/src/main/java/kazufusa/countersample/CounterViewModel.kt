package kazufusa.countersample

import android.arch.lifecycle.*
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import kotlinx.coroutines.experimental.*

class CounterViewModel(private var count: Int = 0) : ViewModel(), LifecycleObserver {
    private val counter = MutableLiveData<Counter>()

    fun getCounter() : LiveData<Counter> {
        return counter
    }

    fun increment() {
        launch {
            counter.postValue(Counter(1, count++))
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() { increment() }
}

@Entity(tableName = "counters")
data class Counter (
        @PrimaryKey
        var id : Int,
        var count: Int = 0
)