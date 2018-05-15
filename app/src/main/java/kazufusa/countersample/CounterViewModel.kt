package kazufusa.countersample

import android.arch.lifecycle.*


class CounterViewModel(private var count: Int = 0) : ViewModel(), LifecycleObserver {
    val changeNotifier = MutableLiveData<Int>()
    fun increment() { changeNotifier.value = ++count }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() { increment() }
}
