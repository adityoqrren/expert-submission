package id.interconnect.gamestar.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.interconnect.gamestar.core.domain.usecase.GameUseCase

class HomeViewModel(gameUseCase: GameUseCase) : ViewModel() {
    val allGames = gameUseCase.getAllGames().asLiveData()
}