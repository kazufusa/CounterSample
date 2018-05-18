package kazufusa.countersample.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kazufusa.countersample.MainActivity
import kazufusa.countersample.di.module.MainModule

@Module
internal abstract class UiModule {
    @ContributesAndroidInjector(modules = arrayOf(MainModule::class))
    internal abstract fun contributeMainActivity(): MainActivity
}
