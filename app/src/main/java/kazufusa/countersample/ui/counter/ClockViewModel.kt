package kazufusa.countersample.ui.counter

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import kazufusa.countersample.repository.ClockRepository
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ClockViewModel @Inject constructor(val dataSource: ClockRepository) : ViewModel(), LifecycleObserver {
    private val notifier = MutableLiveData<Date>()
    private lateinit var job: Job

    fun getClock() : LiveData<Date> {
        return notifier
    }

    fun update() {
        dataSource.updateCurrentDateTime()
        notifier.postValue(dataSource.loadCurrentDateTime())
    }

    fun autoUpdate(){
        job = launch {
            while(true) {
                update()
                delay(10, TimeUnit.SECONDS)
            }
        }
    }

    fun stopUpdate(){
        job.cancel()
    }
}