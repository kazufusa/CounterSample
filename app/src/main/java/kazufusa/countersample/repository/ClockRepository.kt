package kazufusa.countersample.repository

import kazufusa.countersample.api.CurrentDateTimeService
import kazufusa.countersample.db.CurrentDateTimesDao
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClockRepository @Inject constructor(
    private val dao: CurrentDateTimesDao,
    private val currentDateTimeService: CurrentDateTimeService
) {
    fun loadCurrentDateTime(): Date? {
        return dao.getCurrent()?.date
    }

    fun updateCurrentDateTime() {
        val response = currentDateTimeService.currenttime().execute()
        val body = response?.body()
        body?.let {
            dao.insert(it)
            dao.updateLatest(Date(it.st.toLong()*1000L))
        }
    }
}
