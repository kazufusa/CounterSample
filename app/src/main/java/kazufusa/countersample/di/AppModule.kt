package kazufusa.countersample.di


import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Rfc3339DateJsonAdapter
import dagger.Module
import dagger.Provides
import kazufusa.countersample.App
import kazufusa.countersample.api.CurrentDateTimeService
import kazufusa.countersample.db.*
import kazufusa.countersample.repository.ClockRepository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
import java.util.Date

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideCountersDatabase(app: App): AppDb {
        return AppDb.getInstance(app)
    }

    @Singleton
    @Provides
    fun provideCountersDao(db: AppDb): CountersDao {
        return db.countersDao()
    }

    @Singleton
    @Provides
    fun provideCurrentDateTimesDao(db: AppDb): CurrentDateTimesDao {
        return db.currentDateTimesDao()
    }

    @Provides
    @Singleton
    fun provideMoshi() : Moshi {
        return Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
                .build()
    }

    @Singleton
    @Provides
    fun provideCurrentDaService(moshi: Moshi): CurrentDateTimeService {
        return Retrofit.Builder()
                .baseUrl("https://ntp-a1.nict.go.jp")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(CurrentDateTimeService::class.java)
    }
}