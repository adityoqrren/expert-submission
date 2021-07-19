package id.interconnect.gamestar.core.di

import androidx.room.Room
import id.interconnect.gamestar.core.data.GameRepository
import id.interconnect.gamestar.core.data.source.local.LocalDataSource
import id.interconnect.gamestar.core.data.source.local.room.GameDatabase
import id.interconnect.gamestar.core.data.source.remote.RemoteDataSource
import id.interconnect.gamestar.core.data.source.remote.network.ApiService
import id.interconnect.gamestar.core.domain.repository.IGameRepository
import id.interconnect.gamestar.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//room
val databaseModule = module {
    factory { get<GameDatabase>().gameDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            GameDatabase::class.java, "Game.db"
        ).fallbackToDestructiveMigration().build()
    }
}

//network (retrofit)
val networkModule = module{
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.rawg.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get())}
    single { RemoteDataSource(get()) }
    single { AppExecutors() }
    single<IGameRepository>{GameRepository(get(),get(),get())}
}