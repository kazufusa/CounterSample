package kazufusa.countersample.ui.clock

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
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
    private var updating = false

    fun getClock() : LiveData<Date> {
        return notifier
    }

    fun update() {
        dataSource.updateCurrentDateTime()
        notifier.postValue(dataSource.loadCurrentDateTime())
    }

    fun startAutoUpdate(){
        updating = true
        job = launch {
            while(updating) {
                update()
                delay(1, TimeUnit.SECONDS)
                Log.i("ClockFragment", "UPDATE CLOCK")
            }
        }
    }

    fun stopAutoUpdate(){
        updating = false
        Log.i("ClockFrragment", "STOP UPDATE LOOP")
    }
}