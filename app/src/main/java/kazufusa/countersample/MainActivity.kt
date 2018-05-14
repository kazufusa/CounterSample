package kazufusa.countersample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        my_container.setOnClickListener { incrementCount() }
    }

    override fun onResume() {
        super.onResume()
        incrementCount()
    }

    private fun incrementCount() {
        my_text.text = (++count).toString()
    }
}