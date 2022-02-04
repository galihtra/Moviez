package com.galih.moviez.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.galih.moviez.data.source.local.entitiy.MovieEntitiy
import com.galih.moviez.databinding.ItemMovieOrTvshowBinding
import com.galih.moviez.utils.BASE_URL_API_IMAGE
import com.galih.moviez.utils.POSTER_SIZE_W185
import com.galih.moviez.utils.loadImageMovie

class MovieAdapter : PagedListAdapter<MovieEntitiy, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    private var listener: ((MovieEntitiy) -> Unit)? = null

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) holder.bind(movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        ItemMovieOrTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false).also {
            return MovieViewHolder(it)
        }
    }

    fun onClick(listener: ((MovieEntitiy) -> Unit)?) {
        this.listener = listener
    }

    inner class MovieViewHolder(private val binding: ItemMovieOrTvshowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntitiy) {
            binding.apply {
                movie.poster?.let {
                    poster.loadImageMovie("$BASE_URL_API_IMAGE$POSTER_SIZE_W185$it")
                }
                tvTitle.text = movie.title
                tvDesc.text = movie.desc

                listener?.let { itemView.setOnClickListener { it(movie) } }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntitiy>() {
            override fun areItemsTheSame(oldItem: MovieEntitiy, newItem: MovieEntitiy): Boolean =
                oldItem.movieId == newItem.movieId

            override fun areContentsTheSame(oldItem: MovieEntitiy, newItem: MovieEntitiy): Boolean =
                oldItem == newItem

        }
    }
}