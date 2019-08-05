package com.org.employeedata.utils


/*
      Created by Iram
*/

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

public class Rest_Adapter {

    companion object {

        val DEFAULT_TIMEOUT_SEC = 90
        var retrofit: Retrofit? = null
        public fun getClient(ctx: Context): Retrofit {

            val httpClient = OkHttpClient.Builder()
                    .connectTimeout(DEFAULT_TIMEOUT_SEC.toLong(), TimeUnit.SECONDS)
                    .readTimeout(DEFAULT_TIMEOUT_SEC.toLong(), TimeUnit.SECONDS)
                    .writeTimeout(DEFAULT_TIMEOUT_SEC.toLong(), TimeUnit.SECONDS)

            httpClient.addInterceptor { chain ->
                val original = chain.request()
                val request: Request
                request = original.newBuilder()
                        .method(original.method(), original.body())
                        .build()


                chain.proceed(request)
            }

            //For logging the call on Logcat
            val interceptor1 = HttpLoggingInterceptor()
            interceptor1.setLevel(HttpLoggingInterceptor.Level.BODY)
            httpClient.addInterceptor(interceptor1)
            var client: OkHttpClient = httpClient.build()

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(AppConstants.BASE_URL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit!!
        }


    }
}