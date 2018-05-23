package kazufusa.countersample.api

import kazufusa.countersample.vo.CurrentDateTime
import retrofit2.Call
import retrofit2.http.GET

interface CurrentDateTimeService {
    @GET("/cgi-bin/json")
    fun currenttime(): Call<CurrentDateTime>
}
