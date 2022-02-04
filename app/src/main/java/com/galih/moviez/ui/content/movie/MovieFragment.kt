package com.galih.moviez.ui.content.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.galih.moviez.adapter.MovieAdapter
import com.galih.moviez.databinding.FragmentMovieBinding
import com.galih.moviez.ui.detail.DetailActivity
import com.galih.moviez.ui.home.HomeViewModel
import com.galih.moviez.utils.ViewModelFactory
import com.galih.moviez.utils.showLoading
import com.galih.moviez.utils.showToastConnectionLost
import com.galih.moviez.vo.Status
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MovieFragment : DaggerFragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding as FragmentMovieBinding

    private lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var factory: ViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // init
        activity?.let {
            viewModel =
                ViewModelProvider(it, factory)[HomeViewModel::class.java]
        }

        setUpRecyclerView()

        getMovies()

    }

    private fun setUpRecyclerView() {
        binding.apply {
            MovieAdapter().also { adapter ->
                rvMovie.setHasFixedSize(true)
                rvMovie.adapter = adapter

                adapter.onClick {
                    Intent(activity, DetailActivity::class.java).also { intent ->
                        intent.putExtra(DetailActivity.EXTRA_ID, it.movieId)
                        intent.putExtra(DetailActivity.EXTRA_TYPE, DetailActivity.TYPES[0])
                        startActivity(intent)
                    }
                }
            }
        }
    }

    private fun getMovies() {
        binding.apply {
            viewModel.getMovies().observe(viewLifecycleOwner) {

                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> progressBar.showLoading(true)
                        Status.SUCCESS -> {
                            progressBar.showLoading(false)
                            rvMovie.adapter?.let { adapter ->
                                when (adapter) {
                                    is MovieAdapter -> {
                                        adapter.submitList(it.data)
                                        adapter.notifyDataSetChanged()
                                    }
                                }
                            }
                        }
                        Status.ERROR -> {
                            progressBar.showLoading(false)
                            context?.showToastConnectionLost()
                        }
                    }
                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}