package kazufusa.countersample

import dagger.android.support.DaggerApplication
import kazufusa.countersample.di.applyAutoInjector
import kazufusa.countersample.di.DaggerAppComponent

class App : DaggerApplication() {

    // @Inject lateinit var appLifecycleCallbacks: AppLifecycleCallbacks
    // @Inject lateinit var userViewModel: UserViewModel

    override fun applicationInjector() = DaggerAppComponent.builder()
            .application(this)
            .build()

    override fun onCreate() {
        super.onCreate()
        applyAutoInjector()
//        appLifecycleCallbacks.onCreate(this)
    }

    override fun onTerminate() {
//        appLifecycleCallbacks.onTerminate(this)
        super.onTerminate()
    }

}
