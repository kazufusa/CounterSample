package kazufusa.countersample

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject



class MainActivity : AppCompatActivity() {
    private lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var infoMessenger : InfoMessenger

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

        DaggerMagicBox.create().inject(this)
        infoMessenger.show()
    }

    private fun incrementCount(count: Int) {
        my_text.text = (count).toString()
    }
}

@Component(modules = [Bag::class])
interface MagicBox {
    fun inject(app: MainActivity)
}

class InfoMessenger @Inject constructor() {
    @Inject lateinit var info: Info

    fun show(){
        println(info.text)
    }
}

data class Info(val text: String)

@Module
class Bag {
    @Provides
    open fun providesInfo(): Info {
        return Info("Love Dagger 2")
    }
}