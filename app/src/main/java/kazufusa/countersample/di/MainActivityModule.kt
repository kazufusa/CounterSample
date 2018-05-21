package kazufusa.countersample.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kazufusa.countersample.MainActivity
import kazufusa.countersample.di.module.FragmentBuildersModule

@Module
internal abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    internal abstract fun contributeMainActivity(): MainActivity
}