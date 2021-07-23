package id.interconnect.gamestar.detail

import androidx.lifecycle.*
import id.interconnect.gamestar.core.domain.usecase.GameUseCase

class DetailViewModel(val gameUseCase: GameUseCase): ViewModel() {
    val detailGameId = MutableLiveData<Int>()

    fun setDetailGameId(id: Int){
        this.detailGameId.value = id
    }

    val detailGames = Transformations.switchMap(detailGameId){ gameId ->
        gameUseCase.getDetailGame(gameId).asLiveData()
    }

    fun setFavorite(){
        val detailGameItem = detailGames.value
        if(detailGameItem != null){
            val detailItemData = detailGameItem.data
            if(detailItemData != null){
                val newState = !detailItemData.favorited
                gameUseCase.setFavoriteGame(detailItemData, newState)
            }
        }
    }
}