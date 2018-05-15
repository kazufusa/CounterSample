package kazufusa.countersample

import android.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MyViewModelTest {
    private lateinit var myViewModel: MyViewModel

    @Rule
    @JvmField
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        myViewModel = MyViewModel()
    }

    @Test
    fun `increment counter from zero to one`(){
        myViewModel.increment()
        assertEquals(1, myViewModel.changeNotifier.value)
        myViewModel.increment()
        assertEquals(2, myViewModel.changeNotifier.value)
    }
}