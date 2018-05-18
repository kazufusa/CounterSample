package kazufusa.countersample.ui.counter

import android.arch.lifecycle.*
import kotlinx.coroutines.experimental.launch
import kazufusa.countersample.db.CountersDao
import kazufusa.countersample.vo.Counter
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