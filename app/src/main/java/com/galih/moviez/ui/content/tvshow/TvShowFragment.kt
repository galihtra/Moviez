package com.galih.moviez.ui.content.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.galih.moviez.adapter.TvShowAdapter
import com.galih.moviez.databinding.FragmentTvShowBinding
import com.galih.moviez.ui.detail.DetailActivity
import com.galih.moviez.ui.home.HomeViewModel
import com.galih.moviez.utils.ViewModelFactory
import com.galih.moviez.utils.showLoading
import com.galih.moviez.utils.showToastConnectionLost
import com.galih.moviez.vo.Status
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TvShowFragment : DaggerFragment() {

    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding as FragmentTvShowBinding

    private lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTvShowBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // init
        viewModel = ViewModelProvider(activity as FragmentActivity, factory)[HomeViewModel::class.java]

        setUpRecyclerView()

        getTvShow()

    }

    private fun setUpRecyclerView() {
        binding.apply {
            TvShowAdapter().also { adapter ->
                rvTvshow.setHasFixedSize(true)
                rvTvshow.adapter = adapter

                adapter.onClick {
                    Intent(activity, DetailActivity::class.java).also { intent ->
                        intent.putExtra(DetailActivity.EXTRA_ID, it.tvShowId)
                        intent.putExtra(DetailActivity.EXTRA_TYPE, DetailActivity.TYPES[1])
                        startActivity(intent)
                    }
                }
            }
        }
    }

    private fun getTvShow() {
        binding.apply {
            viewModel.getTvShow().observe(viewLifecycleOwner){
                if (it != null){
                    when(it.status){
                        Status.LOADING -> progressBar.showLoading(true)
                        Status.SUCCESS -> {
                            progressBar.showLoading(false)
                            rvTvshow.adapter?.let { adapter ->
                                when(adapter){
                                    is TvShowAdapter -> {
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