package kazufusa.countersample.di

import dagger.Module
import dagger.Provides
import kazufusa.countersample.App
import kazufusa.countersample.db.CountersDao
import kazufusa.countersample.db.CountersDb
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
}
