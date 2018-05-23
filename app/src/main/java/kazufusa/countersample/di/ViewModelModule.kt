package kazufusa.countersample.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kazufusa.countersample.ui.clock.ClockViewModel
import kazufusa.countersample.ui.counter.CounterViewModel

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CounterViewModel::class)
    abstract fun bindCounterViewModel(counterViewModel: CounterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ClockViewModel::class)
    abstract fun bindClockViewModel(clockViewModel: ClockViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}