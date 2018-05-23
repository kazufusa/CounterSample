package kazufusa.countersample.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kazufusa.countersample.R
import kazufusa.countersample.di.Injectable
import kotlinx.android.synthetic.main.title_fragment.*

class TitleFragment : Fragment(), Injectable {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.title_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_counter.setOnClickListener {
            it?.let {
                Navigation.findNavController(it).navigate(R.id.action_title_to_counter)
            }
        }

        btn_clock.setOnClickListener {
            it?.let {
                Navigation.findNavController(it).navigate(R.id.action_title_to_clock)
            }
        }
    }
}