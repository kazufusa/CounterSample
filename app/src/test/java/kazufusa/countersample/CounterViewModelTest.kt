package kazufusa.countersample

import android.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class CounterViewModelTest {
    private lateinit var counterViewModel: CounterViewModel

    @Rule
    @JvmField
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        counterViewModel = CounterViewModel()
    }

    @Test
    fun `increment counter from zero to one`(){
        counterViewModel.increment()
        assertEquals(1, counterViewModel.changeNotifier.value)
        counterViewModel.increment()
        assertEquals(2, counterViewModel.changeNotifier.value)
    }
}