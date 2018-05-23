package kazufusa.countersample.ui.counter

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kazufusa.countersample.R
import kazufusa.countersample.di.Injectable
import kazufusa.countersample.di.ViewModelFactory
import kazufusa.countersample.vo.Counter
import kotlinx.android.synthetic.main.counter_fragment.*
import javax.inject.Inject

class CounterFragment : Fragment(), Injectable {
    @Inject lateinit var viewModelFactory: ViewModelFactory
    lateinit var counterViewModel: CounterViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.counter_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        counterViewModel = ViewModelProviders.of(this, viewModelFactory).get(CounterViewModel::class.java)
        counterViewModel.getCounter().observe(this, changeObserver)
        lifecycle.addObserver(counterViewModel)

        my_container.setOnClickListener {
            counterViewModel.increment()
        }
    }

    private val changeObserver = Observer<Counter> {
        counter -> counter?.let { incrementCount(counter.count) }
    }

    private fun incrementCount(count: Int) {
        counter_text.text = (count).toString()
    }
}