package com.example.myapplication.diModule


import android.content.Context
import androidx.room.Room
import com.example.myapplication.Constants.APP_PREFERENCES
import com.example.myapplication.Constants.baseurl
import com.example.myapplication.main.veiwModel.MyViewModel
import com.example.myapplication.data.room.AppDatabase
import com.example.myapplication.data.api.RickApi
import com.example.myapplication.data.repository.*
import com.example.myapplication.domain.interactor.Interactor
import com.example.myapplication.domain.interactor.InteractorImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single<ApiRepository> { ApiRepositoryImpl(get()) }
    single<DbRepository> { DbRepositoryImpl(get()) }
    single<SharedPreferencesRepository> { SharedPreferencesRepositoryImpl(get()) }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(RickApi::class.java)

    }
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java, "pers.db"
        ).build()
    }
    single {
        androidContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
    }

}
val domainModule = module {
    factory<Interactor> { InteractorImpl(get(), get(), get()) }

}
val viewModelModule = module {
    viewModel {
        MyViewModel(get())
    }
}


