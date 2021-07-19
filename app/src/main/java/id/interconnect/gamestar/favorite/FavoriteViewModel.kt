package id.interconnect.gamestar.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.interconnect.gamestar.core.domain.usecase.GameUseCase

class FavoriteViewModel(gameUseCase: GameUseCase) :  ViewModel(){
    val favoriteGames = gameUseCase.getFavoriteGames().asLiveData()
}