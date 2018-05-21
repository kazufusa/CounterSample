package kazufusa.countersample.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kazufusa.countersample.ui.counter.CounterFragment

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeRepoFragment(): CounterFragment
}
