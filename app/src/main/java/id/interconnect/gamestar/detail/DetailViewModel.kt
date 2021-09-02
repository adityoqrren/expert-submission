package id.interconnect.gamestar.detail


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.interconnect.gamestar.core.domain.usecase.GameUseCase

class DetailViewModel(private val gameUseCase: GameUseCase) : ViewModel() {
    private val detailGameId = MutableLiveData<Int>()

    fun setDetailGameId(id: Int) {
        this.detailGameId.value = id
    }

    val detailGames = switchMap(detailGameId) { gameId ->
        gameUseCase.getDetailGame(gameId).asLiveData()
    }


    fun setFavorite() {
        val detailGameItem = detailGames.value
        if (detailGameItem != null) {
            val detailItemData = detailGameItem.data
            if (detailItemData != null) {
                val newState = !detailItemData.favorited
                gameUseCase.setFavoriteGame(detailItemData, newState)
            }
        }
    }
}