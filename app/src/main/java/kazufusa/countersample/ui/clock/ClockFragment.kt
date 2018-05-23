package kazufusa.countersample.ui.clock

import android.arch.lifecycle.ViewModelProviders
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kazufusa.countersample.R
import kazufusa.countersample.di.Injectable
import kazufusa.countersample.di.ViewModelFactory
import kotlinx.android.synthetic.main.clock_fragment.*
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

class ClockFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var clockViewModel: ClockViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.clock_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        clockViewModel = ViewModelProviders.of(this, viewModelFactory).get(ClockViewModel::class.java)
        clockViewModel.getClock().observe(this, changeClockObserver)
        clockViewModel.startAutoUpdate()
    }

    override fun onPause() {
        super.onPause()
        clockViewModel.stopAutoUpdate()
    }

    private val changeClockObserver = Observer<Date> {
        date -> date?.let {updateClock(date)}
    }

    private fun updateClock(date: Date) {
        val df = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        clock_text.text = df.format(date)
    }

}