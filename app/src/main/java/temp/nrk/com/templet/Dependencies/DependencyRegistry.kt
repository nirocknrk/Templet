package temp.nrk.com.templet.Dependencies

import android.app.Application
import java.util.ArrayList
import java.util.Arrays

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import temp.nrk.com.templet.api.Api
import temp.nrk.com.templet.db.AppDB
import temp.nrk.com.templet.repository.Repository
import temp.nrk.com.templet.util.Config
import temp.nrk.com.templet.util.NetUtil

class DependencyRegistry {
    companion object { /*initialization*/ val shared = DependencyRegistry() }

    //region Objects Initializations
    val retrofitForServiceApi: Retrofit
        get() = NetUtil.getRetrofit(
                retrofitBuilder = Retrofit.Builder(),
                baseUrl = Config.BASE_URL,
                converterFactoryList = Arrays.asList(ScalarsConverterFactory.create(), GsonConverterFactory.create()),
                okHttpClientBuilder = OkHttpClient.Builder(),
                listOfInterceptor = ArrayList<Interceptor>(),
                callAdapterFactory = RxJava2CallAdapterFactory.create(),
                authenticator = null
        )

    fun getDb(application: Application): AppDB {
        return AppDB.getDatabase(application)
    }
    //endregion - Objects Initializations

    //region of methods to inject objects to classes

    fun inject(application: Application,repository: Repository) {
        repository.configureWith(getDb(application),retrofitForServiceApi.create(Api::class.java))
    }

    //endregion - of methods to inject objects to classes



}
