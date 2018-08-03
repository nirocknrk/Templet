package temp.nrk.com.templet

import android.app.Application
import android.content.Context
import temp.nrk.com.templet.Dependencies.DependencyRegistry

class Application : Application() {

    lateinit var appContext: Context

    lateinit var dependencyRegistry: DependencyRegistry

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        dependencyRegistry = DependencyRegistry.shared
    }
}