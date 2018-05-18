package kazufusa.countersample

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kazufusa.countersample.di.ViewModelFactory
import kazufusa.countersample.ui.counter.CounterViewModel
import kazufusa.countersample.vo.Counter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = androidInjector
    private val counterViewModel: CounterViewModel by lazy {
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
    }

    private fun incrementCount(count: Int) {
        my_text.text = (count).toString()
    }
}