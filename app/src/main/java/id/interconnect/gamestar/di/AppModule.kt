package id.interconnect.gamestar.di

import id.interconnect.gamestar.core.domain.usecase.GameInteractor
import id.interconnect.gamestar.core.domain.usecase.GameUseCase
import id.interconnect.gamestar.favorite.FavoriteViewModel
import id.interconnect.gamestar.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

//import org.koin.android.viewmodel.dsl.viewModel
//import org.koin.dsl.module

val useCaseModule = module {
    factory<GameUseCase> {GameInteractor(get())}
}

val viewModelModule = module{
   viewModel { HomeViewModel(get()) }
   viewModel { FavoriteViewModel(get()) }
}