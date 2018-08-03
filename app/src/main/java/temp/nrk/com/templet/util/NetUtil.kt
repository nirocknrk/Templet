package temp.nrk.com.templet.util

import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit

/**
 * Helper class to build retrofit. With static functions which simplify retrofit initializations.
 */
object NetUtil {


    fun getRetrofit(retrofitBuilder: Retrofit.Builder,
                    baseUrl: String,
                    converterFactoryList: List<Converter.Factory>,
                    okHttpClientBuilder: OkHttpClient.Builder,
                    listOfInterceptor: List<Interceptor>,
                    callAdapterFactory: CallAdapter.Factory?,
                    authenticator: Authenticator?): Retrofit {
        val client = getOkHttpClent(okHttpClientBuilder, listOfInterceptor, authenticator)
        retrofitBuilder.baseUrl(baseUrl).client(client)

        for (factory in converterFactoryList) {
            retrofitBuilder.addConverterFactory(factory)
        }
        if (callAdapterFactory != null) {
            retrofitBuilder.addCallAdapterFactory(callAdapterFactory)
        }

        return retrofitBuilder.build()
    }

    fun getOkHttpClent(okHttpClientBuilder: OkHttpClient.Builder,
                       listOfInterceptor: List<Interceptor>,
                       authenticator: Authenticator?): OkHttpClient {
        if (!listOfInterceptor.isEmpty()) {
            for (interceptor in listOfInterceptor)
                okHttpClientBuilder.addInterceptor(interceptor)
        }
        if (authenticator != null) {
            okHttpClientBuilder.authenticator(authenticator)
        }
        return okHttpClientBuilder.build()
    }
}
