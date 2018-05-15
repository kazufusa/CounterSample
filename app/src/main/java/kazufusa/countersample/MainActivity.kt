package kazufusa.countersample

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val counterViewModel: CounterViewModel by lazy {
        ViewModelProviders.of(this).get(CounterViewModel::class.java)
    }

    private val changeObserver = Observer<Int> {
        value -> value?.let { incrementCount(value) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        counterViewModel.changeNotifier.observe(this, changeObserver)
        lifecycle.addObserver(counterViewModel)
        my_container.setOnClickListener { counterViewModel.increment() }
    }

    private fun incrementCount(value: Int) {
        my_text.text = (value).toString()
    }
}
