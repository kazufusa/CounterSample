package kazufusa.countersample.ui.counter

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import kazufusa.countersample.repository.ClockRepository
import kotlinx.coroutines.experimental.launch
import java.util.*
import javax.inject.Inject

class ClockViewModel @Inject constructor(val dataSource: ClockRepository) : ViewModel(), LifecycleObserver {
    private val notifier = MutableLiveData<Date>()

    fun getClock() : LiveData<Date> {
        return notifier
    }

    fun update() {
        launch {
            dataSource.updateCurrentDateTime()
            notifier.postValue(dataSource.loadCurrentDateTime())
        }
    }
}