package kazufusa.countersample

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val viewModel: MyViewModel by lazy {
        ViewModelProviders.of(this).get(MyViewModel::class.java)
    }

    private val changeObserver =
            Observer<Int> {
                value -> value?.let { incrementCount(value) }
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.changeNotifier.observe(this, changeObserver)
        my_container.setOnClickListener { viewModel.increment() }
    }

    private fun incrementCount(value: Int) {
        my_text.text = (value).toString()
    }
}

class MyViewModel(private var count: Int = 0) : ViewModel() {
    val changeNotifier = MutableLiveData<Int>()
    fun increment() { changeNotifier.value = ++count }
}
