package id.interconnect.gamestar.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import id.interconnect.gamestar.core.ui.GameListAdapter
import id.interconnect.gamestar.databinding.FragmentFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoriteFragment : Fragment() {
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val gameListAdapter = GameListAdapter()
            gameListAdapter.onItemClick = { data ->
                val toDetailGameFragment =
                    FavoriteFragmentDirections.actionNavFavoriteToDetailActivity(gameId = data.id)
                view.findNavController().navigate(toDetailGameFragment)
            }
            favoriteViewModel.favoriteGames.observe(viewLifecycleOwner, { listFavGames ->
                if (listFavGames != null) {
                    gameListAdapter.setData(listFavGames)
                    if(listFavGames.size == 0){
                        binding.favoriteTextNodata.visibility = View.VISIBLE
                    }
                }
            })

            binding.rvFavorite.layoutManager = LinearLayoutManager(context)
            binding.rvFavorite.setHasFixedSize(true)
            binding.rvFavorite.adapter = gameListAdapter
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}