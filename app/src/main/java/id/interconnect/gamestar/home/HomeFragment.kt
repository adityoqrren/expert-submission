package id.interconnect.gamestar.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import id.interconnect.gamestar.core.data.Resource
import id.interconnect.gamestar.core.ui.GameListAdapter
import id.interconnect.gamestar.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val gameListAdapter = GameListAdapter()
            gameListAdapter.onItemClick = { data ->
                val toDetailGameFragment = HomeFragmentDirections.actionNavHomeToDetailActivity(gameId = data.id)
                view.findNavController().navigate(toDetailGameFragment)
            }
            homeViewModel.allGames.observe(viewLifecycleOwner, { listGames ->
                if (listGames != null) {
                    when (listGames) {
                        is Resource.Loading -> {
                            binding.progressBarHome.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            binding.progressBarHome.visibility = View.INVISIBLE
                            gameListAdapter.setData(listGames.data)
                        }
                        is Resource.Error -> {
                            binding.progressBarHome.visibility = View.INVISIBLE
                            Toast.makeText(
                                requireActivity(),
                                "Oops, there's something wrong. Try later!",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            })


            binding.rvHome.layoutManager = LinearLayoutManager(context)
            binding.rvHome.setHasFixedSize(true)
            binding.rvHome.adapter = gameListAdapter

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}