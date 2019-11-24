package com.app.vogobook.di.module;

import android.content.Context
import com.app.vogobook.BuildConfig
import com.app.vogobook.app.Application
import com.app.vogobook.service.connect.TrustHtppS
import com.app.vogobook.service.connect.rx.DisposableManager
import com.app.vogobook.service.repository.BookService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class ServiceModule(application: Application, context: Context) {

    init {
        val mApplication = application
        val mContext = context
    }

    @Singleton
    @Provides
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
    }

    @Singleton
    @Provides
    @Named("ok-1")
    internal fun provideOkHttpClient1(interceptor: HttpLoggingInterceptor): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
    }

    @Singleton
    @Provides
    @Named("ok-2")
    internal fun provideOkHttpClient2(interceptor: HttpLoggingInterceptor): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
    }

    @Singleton
    @Provides
    internal fun provideTrustHtppS(@Named("ok-1") client: OkHttpClient.Builder): TrustHtppS {
        return TrustHtppS(client)
    }

    @Singleton
    @Provides
    @Named("url-configure")
    internal fun provideBaseURL1(): String {
        return BuildConfig.BASE_URL
    }

    @Singleton
    @Provides
    internal fun provideRetrofit(trustHtppS: TrustHtppS, @Named("ok-1") client: OkHttpClient.Builder, @Named("url-configure") baseUrl: String): Retrofit {
        trustHtppS.intializeCertificate()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    internal fun provideBookService(retrofit: Retrofit) : BookService {
        return retrofit.create(BookService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideDisposableManager() : DisposableManager {
        return DisposableManager()
    }
}