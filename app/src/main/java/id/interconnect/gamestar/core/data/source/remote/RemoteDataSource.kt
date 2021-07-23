package id.interconnect.gamestar.core.data.source.remote

import android.util.Log
import id.interconnect.gamestar.BuildConfig.MY_API_KEY
import id.interconnect.gamestar.core.data.source.remote.network.ApiResponse
import id.interconnect.gamestar.core.data.source.remote.network.ApiService
import id.interconnect.gamestar.core.data.source.remote.response.GameItemResponse
import id.interconnect.gamestar.core.data.source.remote.response.GameResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getListGames(): Flow<ApiResponse<List<GameItemResponse>>>{
        return flow{
            try {
                val response = apiService.getListGames(MY_API_KEY)
                val dataList = response.results
                Log.d("lihat datalist: ",dataList.toString())
                if(dataList.isNotEmpty()){
                    emit(ApiResponse.Success(dataList))
                }else{
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource m1",e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailGame(id: Int): Flow<ApiResponse<GameResponse>>{
        return flow {
            try{
                val response = apiService.getDetailGame(id, MY_API_KEY)
                emit(ApiResponse.Success(response))
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource m2", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}