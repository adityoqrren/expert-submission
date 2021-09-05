package id.interconnect.gamestar.core.di

import androidx.room.Room
import id.interconnect.gamestar.core.BuildConfig
import id.interconnect.gamestar.core.data.GameRepository
import id.interconnect.gamestar.core.data.source.local.LocalDataSource
import id.interconnect.gamestar.core.data.source.local.room.GameDatabase
import id.interconnect.gamestar.core.data.source.remote.RemoteDataSource
import id.interconnect.gamestar.core.data.source.remote.network.ApiService
import id.interconnect.gamestar.core.domain.repository.IGameRepository
import id.interconnect.gamestar.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
        //sqlite encryption
        val passphrase:ByteArray = SQLiteDatabase.getBytes("mygamestarryeahh".toCharArray())
        val factory = SupportFactory(passphrase)

        Room.databaseBuilder(
            androidContext(),
            GameDatabase::class.java, "Game.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

//network (retrofit)
val networkModule = module {
    single {
        val logging = HttpLoggingInterceptor()

        if(BuildConfig.DEBUG){
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        }else{
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        }

        //certificate pinning
        val hostname = "api.rawg.io"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/RI9CUmPUOpUk2vdVMSZDWj+wtoQO5k9MSCSM9w4grmU=")
            .add(hostname, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
            .add(hostname, "sha256/Y9mvm0exBk1JoQ57f9Vm28jKo5lFm/woKcVxrYxu80o=")
            .build()

        OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
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
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single { AppExecutors() }
    single<IGameRepository> {
        GameRepository(
            get(),
            get(),
            get()
        )
    }
}