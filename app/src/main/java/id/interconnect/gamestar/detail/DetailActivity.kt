package id.interconnect.gamestar.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
            if (detailGame != null) {
                when (detailGame) {
                    is Resource.Loading<*> -> {
                        supportActionBar?.title = ""
                    }
                    is Resource.Success<*> -> {
                        if (detailGame.data != null) {
                            val detailGameData = detailGame.data
                            supportActionBar?.title = detailGameData?.name_original
                            Glide.with(this)
                                .load(detailGameData?.background_image)
                                .into(detailBinding.ivDetailImage)
                            detailBinding.detailContent.description.text =
                                detailGameData?.description_raw
                            detailBinding.detailContent.genre.text = detailGameData?.genres
                            detailBinding.detailContent.developers.text = detailGameData?.developers
                            detailBinding.detailContent.parentPlatforms.text =
                                detailGameData?.parent_platforms
                            detailBinding.detailContent.released.text = detailGameData?.released
                            detailGameData?.updated?.let {
                                detailBinding.detailContent.updated.text = it
                            }
                            detailBinding.detailContent.rating.text =
                                detailGameData?.rating.toString()
                            detailGameData?.playtime?.let {
                                detailBinding.detailContent.playtime.text =
                                    resources.getQuantityString(
                                        R.plurals.detail_playtime_hour,
                                        it,
                                        it
                                    )
                            }
                            detailBinding.detailContent.ageRating.text = detailGameData?.esrb_rating
                            detailGameData?.tba?.let {
                                if (it) {
                                    detailBinding.detailContent.tba.text =
                                        resources.getText(R.string.tba_yes)
                                } else {
                                    detailBinding.detailContent.tba.text =
                                        resources.getText(R.string.tba_no)
                                }
                            }
                            detailGameData?.favorited?.let {
                                favorited = it
                                changeFavoriteIcon(it)
                            }
                            detailBinding.favoriteFab.setOnClickListener {
                                indicatorChangeFav = true
                                runBlocking {
                                    detailViewModel.setFavorite()
                                }
                            }
                        }
                    }
                    is Resource.Error<*> -> {
                        Toast.makeText(
                            this,
                            resources.getString(R.string.error_message),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        })

    }


    private fun changeFavoriteIcon(favorited: Boolean) {
        if (favorited) {
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
            detailBinding.favoriteFab.setImageResource(R.drawable.ic_favorite_white)
        }
        indicatorChangeFav = false
    }


}