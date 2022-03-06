package com.example.codigocodemanagement.adapter

import android.content.Context
import android.content.Intent
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.codigocodemanagement.R
import com.example.codigocodemanagement.databinding.ListItemUpcomingBinding
import com.example.codigocodemanagement.room.entity.MovieEntity
import com.example.codigocodemanagement.ui.DetailActivity
import com.example.codigocodemanagement.utility.delegateutils.adapterViewBinding
import com.example.codigocodemanagement.viewmodel.MovieViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpcomingAdapter(private val context: Context, val movieViewModel: MovieViewModel) :
    ListAdapter<MovieEntity, UpcomingAdapter.UpcomingViewHolder>(diffCallback) {
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class UpcomingViewHolder(val binding: ListItemUpcomingBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        val v = parent.adapterViewBinding(ListItemUpcomingBinding::inflate)
        return UpcomingViewHolder(v)
    }

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        val movie = getItem(position)
        CoroutineScope(Dispatchers.Main).launch {
            setData(movie, holder)
        }
    }

    private suspend fun setData(movie: MovieEntity, holder: UpcomingViewHolder) {
        holder.itemView.setOnClickListener {
            val i = Intent(context, DetailActivity::class.java)
            i.putExtra("movieId", movie.id)
            context.startActivity(i)
        }
        with(holder)
        {
            if (movieViewModel.getFav(movie.id) != null) {
                if (movieViewModel.getFav(movie.id) == 0) {
                    binding.imgFav.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_un_favorite_24))
                } else if (movieViewModel.getFav(movie.id) == 1) {
                    binding.imgFav.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_favorite_24))
                }
            }
            binding.imgFav.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch {
                    if (movieViewModel.getFav(movie.id) == 0)
                        movieViewModel.updateFav(1, movie.id)
                    else
                        movieViewModel.updateFav(0, movie.id)
                }
                notifyDataSetChanged()
            }
            binding.txtName.text = movie.title
            binding.txtOverview.text = movie.overview
            binding.txtPercentageRate.text = "${movie.voteAverage} %"
            binding.txtPopularity.text =
                if (movie.voteCount.length > 3) "${movie.voteCount[0]}K" else movie.voteCount
            Glide.with(context).load("https://image.tmdb.org/t/p/original/${movie.posterPath}")
                .placeholder(
                    R.drawable.placeholder
                ).into(binding.imgPhoto)


        }
    }
}