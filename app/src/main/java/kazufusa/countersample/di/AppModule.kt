package kazufusa.countersample.di


import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import kazufusa.countersample.App
import kazufusa.countersample.db.CountersDao
import kazufusa.countersample.db.CountersDb
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideCountersDatabase(app: App): CountersDb {
        return CountersDb.getInstance(app)
    }

    @Singleton
    @Provides
    fun provideCountersDao(db: CountersDb): CountersDao {
        return db.countersDao()
    }

    @Provides
    @Singleton
    fun provideMoshi() : Moshi {
        return Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
    }

    @Singleton
    @Provides
    fun provideTestService(moshi: Moshi): TestService {
        return Retrofit.Builder()
                .baseUrl("https://ntp-a1.nict.go.jp")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(TestService::class.java)
    }
}

data class Test(
        val id: String,
        val it: Double,
        val st: Double,
        val leap: Int,
        val next: Int,
        val step: Int
)

interface TestService {
    @GET("/cgi-bin/json")
    fun currenttime(): Call<Test>
}