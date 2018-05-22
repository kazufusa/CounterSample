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
import kazufusa.countersample.di.TestService
import kazufusa.countersample.di.ViewModelFactory
import kazufusa.countersample.vo.Counter
import kotlinx.android.synthetic.main.counter_fragment.*
import kotlinx.coroutines.experimental.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CounterFragment : Fragment(), Injectable {
    @Inject lateinit var viewModelFactory: ViewModelFactory
    lateinit var counterViewModel: CounterViewModel
    @Inject lateinit var sss: TestService

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        launch {
            val response = sss.currenttime().execute()
            val body = response?.body()
            if (body == null || response.code() == 204) {
            } else {
                println("JSON " + (body.st*1000L).toLong())
                val date = Date(body.st.toLong()*1000L)
                val df = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                println("JSON " + df.format(date))
            }
        }
        val view = inflater.inflate(R.layout.counter_fragment, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        counterViewModel  = ViewModelProviders.of(this, viewModelFactory).get(CounterViewModel::class.java)
        counterViewModel.getCounter().observe(this, changeObserver)
        lifecycle.addObserver(counterViewModel)
        my_container.setOnClickListener { counterViewModel.increment() }
    }

    private val changeObserver = Observer<Counter> {
        counter -> counter?.let { incrementCount(counter.count) }
    }

    private fun incrementCount(count: Int) {
        my_text.text = (count).toString()
    }

}