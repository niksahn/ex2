package com.example.myapplication.diModule


import android.content.Context
import androidx.room.Room
import com.example.myapplication.Main.VeiwModel.APP_PREFERENCES
import com.example.myapplication.Main.VeiwModel.MyViewModel
import com.example.myapplication.data.Room.AppDatabase
import com.example.myapplication.data.api.RickApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single{
        val baseurl = "https://rickandmortyapi.com"
        val retrofit = Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(RickApi::class.java)

      }
    single{
        Room.databaseBuilder(
            get() ,
            AppDatabase::class.java, "pers.db"
        ).build()
    }
    single {
      androidContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
    }

}
val viewModelModule = module {
    viewModel{
        MyViewModel(get(),get(),get())
    }
}


