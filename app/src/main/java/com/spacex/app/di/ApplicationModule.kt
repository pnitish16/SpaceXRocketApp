package com.spacex.app.di

import com.spacex.app.BuildConfig
import com.spacex.app.data.db.RocketDatabase
import com.spacex.app.data.db.mapper.DbMapper
import com.spacex.app.data.db.mapper.DbMapperImpl
import com.spacex.app.data.network.client.RocketApiClient
import com.spacex.app.data.network.mapper.ApiMapper
import com.spacex.app.data.network.mapper.ApiMapperImpl
import com.spacex.app.domain.repository.RocketRepository
import com.spacex.app.domain.repository.RocketRepositoryImpl
import com.spacex.app.util.imageloader.ImageLoader
import com.spacex.app.util.imageloader.ImageLoaderImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL_NAME = "BASE_URL"
private const val BASE_URL = "https://api.spacexdata.com/v4/"
private const val MAIN_DISPATCHER = "main_dispatcher"
private const val BACKGROUND_DISPATCHER = "background_dispatcher"

val applicationModule = module {
    single(named(BASE_URL_NAME)) {
        BASE_URL
    }

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        val client = OkHttpClient().newBuilder()

        if (BuildConfig.DEBUG) {
            client.addInterceptor(get<HttpLoggingInterceptor>())
        }

        client.build()
    }

    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named(BASE_URL_NAME)))
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .client(get())
            .build()
    }

    single {
        get<Retrofit>().create(RocketApiClient::class.java)
    }

    single<ApiMapper> {
        ApiMapperImpl()
    }

    single<RocketRepository> {
        RocketRepositoryImpl(
            get(),
            get(),
            get(named(BACKGROUND_DISPATCHER)),
            get(),
            get()
        )
    }

    single<ImageLoader> { ImageLoaderImpl(get()) }

    single { Picasso.get() }

    single(named(MAIN_DISPATCHER)) { Dispatchers.Main }

    single(named(BACKGROUND_DISPATCHER)) { Dispatchers.IO }

    single { RocketDatabase.create(androidContext()) }

    single { get<RocketDatabase>().rocketDao() }

    single<DbMapper> { DbMapperImpl() }
}