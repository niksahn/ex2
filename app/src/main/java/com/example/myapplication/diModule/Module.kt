package com.example.myapplication.diModule


import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.myapplication.Constants.APP_PREFERENCES
import com.example.myapplication.Constants.baseurl
import com.example.myapplication.data.api.RickApi
import com.example.myapplication.data.repository.*
import com.example.myapplication.data.room.AppDatabase
import com.example.myapplication.domain.interactor.Interactor
import com.example.myapplication.domain.interactor.InteractorImpl
import com.example.myapplication.ui.inf.InfViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object dataModule {
    @Singleton
    @Provides
    fun procvidesApiRepository(apiService: RickApi): ApiRepository = ApiRepositoryImpl(apiService)

    @Singleton
    @Provides
    fun providesDbRepository(db: AppDatabase): DbRepository = DbRepositoryImpl(db)

    @Singleton
    @Provides
    fun providesSharedPreferencesRepository(mSettings: SharedPreferences): SharedPreferencesRepository =
        SharedPreferencesRepositoryImpl(mSettings)

    @Singleton
    @Provides
    fun providesRetrofit(): RickApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(RickApi::class.java)

    }


    @Singleton
    @Provides
    fun ProvidesRoom(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "pers.db"
        ).build()
    }

    @Singleton
    @Provides
    fun ProvideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
    }

}

@Module
@InstallIn(SingletonComponent::class)
object domainModule {

    @Singleton
    @Provides
    fun ProvidesInteractor(
        SharedPreferencesRepository: SharedPreferencesRepository,
        ApiRepository: ApiRepository,
        DbRepository: DbRepository
    ): Interactor = InteractorImpl(SharedPreferencesRepository, ApiRepository, DbRepository)
    @Singleton
    @Provides
    fun ProvidesViewModel(interactor:Interactor):InfViewModel=InfViewModel(interactor)
}



