package kazufusa.countersample

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject



class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {
    private lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var infoMessenger : InfoMessenger
    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = androidInjector
    private val counterViewModel: CounterViewModel by lazy {
        viewModelFactory = Injection.provideViewModelFactory(this)
        ViewModelProviders.of(this, viewModelFactory).get(CounterViewModel::class.java)
    }

    private val changeObserver = Observer<Counter> {
        counter -> counter?.let { incrementCount(counter.count) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        counterViewModel.getCounter().observe(this, changeObserver)
        lifecycle.addObserver(counterViewModel)
        my_container.setOnClickListener { counterViewModel.increment() }
        infoMessenger.show()
    }

    private fun incrementCount(count: Int) {
        my_text.text = (count).toString()
    }
}


@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityModule::class
])
interface AppComponent : AndroidInjector<App> {
    // fun build(app: MainActivity)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    override fun inject(app: App)
}

@Module
class AppModule {
    @Provides
    open fun providesInfo(): Info {
        return Info("Love Dagger 2")
    }
}

@Module
internal abstract class ActivityModule {
    @ContributesAndroidInjector(modules = arrayOf(MainModule::class))
    internal abstract fun contributeMainActivity(): MainActivity

}

@Module
internal abstract class MainModule {
    // @ContributesAndroidInjector
    // abstract fun contributeMainFragment(): MainFragment
}

class InfoMessenger @Inject constructor() {
    @Inject lateinit var info: Info

    fun show(){
        println(info.text)
    }
}

data class Info(val text: String)
