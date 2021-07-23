package id.interconnect.gamestar.detail

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import id.interconnect.gamestar.R
import id.interconnect.gamestar.core.data.Resource
import id.interconnect.gamestar.databinding.ActivityDetailBinding
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var detailBinding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()
    private var indicatorChangeFav: Boolean = false
    private var favorited: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        setSupportActionBar(detailBinding.detailToolbar)

        val idGame = DetailActivityArgs.fromBundle(intent.extras as Bundle).gameId

        detailViewModel.setDetailGameId(idGame)
        detailViewModel.detailGames.observe(this, { detailGame ->
            if(detailGame != null){
                when(detailGame){
                    is Resource.Loading -> {
                            supportActionBar?.title = ""
                    }
                    is Resource.Success -> {
                        if(detailGame.data!=null){
                            supportActionBar?.title = detailGame.data.name_original
                            Glide.with(this)
                                .load(detailGame.data.background_image)
                                .into(detailBinding.ivDetailImage)
                            detailBinding.detailContent.description.text = detailGame.data.description_raw
                            detailBinding.detailContent.genre.text = detailGame.data.genres
                            detailBinding.detailContent.developers.text = detailGame.data.developers
                            detailBinding.detailContent.parentPlatforms.text = detailGame.data.parent_platforms
                            detailBinding.detailContent.released.text = detailGame.data.released
                            detailBinding.detailContent.updated.text = detailGame.data.updated
                            detailBinding.detailContent.rating.text = detailGame.data.rating.toString()
                            detailBinding.detailContent.playtime.text = " ${detailGame.data.playtime.toString()} hour"
                            favorited = detailGame.data.favorited
                            changeFavoriteIcon(detailGame.data.favorited)
                            Log.d("Lihat det favorited: ","${detailGame.data.favorited}")
                            detailBinding.favoriteFab.setOnClickListener {
                                indicatorChangeFav = true
                                runBlocking {
                                    detailViewModel.setFavorite()
                                }
                            }
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            this,
                            "Oops, error guys!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        })

    }


    private fun changeFavoriteIcon(favorited: Boolean){
        if (favorited) {
            Log.d("scope 1 det: ", favorited.toString())
            if (indicatorChangeFav) Toast.makeText(
                this,
                resources.getText(R.string.tambah_fav_game),
                Toast.LENGTH_SHORT
            ).show()
            detailBinding.favoriteFab.setImageResource(R.drawable.ic_favorite_red)
        } else {
            if (indicatorChangeFav) Toast.makeText(
                this,
                resources.getText(R.string.hapus_fav_game),
                Toast.LENGTH_SHORT
            ).show()
            Log.d("scope 2 det: ", favorited.toString())
            detailBinding.favoriteFab.setImageResource(R.drawable.ic_favorite_white)
        }
        indicatorChangeFav = false
    }


}