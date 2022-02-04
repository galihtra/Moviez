package com.galih.moviez.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.galih.moviez.data.source.local.entitiy.TvShowEntity
import com.galih.moviez.databinding.ItemMovieOrTvshowBinding
import com.galih.moviez.utils.BASE_URL_API_IMAGE
import com.galih.moviez.utils.POSTER_SIZE_W185
import com.galih.moviez.utils.loadImageTvShow

class TvShowAdapter : PagedListAdapter<TvShowEntity, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {

    private var listener: ((TvShowEntity) -> Unit)? = null

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvshow = getItem(position)
        if (tvshow != null) holder.bind(tvshow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        ItemMovieOrTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false).also {
            return TvShowViewHolder(it)
        }
    }

    fun onClick(listener: ((TvShowEntity) -> Unit)?) {
        this.listener = listener
    }

    inner class TvShowViewHolder(private val binding: ItemMovieOrTvshowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvshow: TvShowEntity) {
            binding.apply {
                tvshow.poster?.let {
                    poster.loadImageTvShow("$BASE_URL_API_IMAGE$POSTER_SIZE_W185$it")
                }
                tvTitle.text = tvshow.title
                tvDesc.text = tvshow.desc

                listener?.let { itemView.setOnClickListener { it(tvshow) } }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean =
                oldItem.tvShowId == newItem.tvShowId

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean =
                oldItem == newItem

        }
    }
}