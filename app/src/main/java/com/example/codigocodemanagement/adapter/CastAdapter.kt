package com.example.codigocodemanagement.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.codigocodemanagement.R
import com.example.codigocodemanagement.databinding.ListItemRecommendedBinding
import com.example.codigocodemanagement.model.CastModel
import com.example.codigocodemanagement.utility.delegateutils.adapterViewBinding

class CastAdapter(private val context: Context) :
    ListAdapter<CastModel, CastAdapter.PopularViewHolder>(diffCallback) {
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<CastModel>() {
            override fun areItemsTheSame(oldItem: CastModel, newItem: CastModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: CastModel, newItem: CastModel): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class PopularViewHolder(val binding: ListItemRecommendedBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val v = parent.adapterViewBinding(ListItemRecommendedBinding::inflate)
        return PopularViewHolder(v)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val cast = getItem(position)
        setData(cast, holder)

    }

    private fun setData(cast: CastModel, holder: PopularViewHolder) {
        with(holder)
        {
            Log.d("livedata", "adapter: $cast")
            binding.imgFav.visibility = View.GONE
            binding.txtPercentageRate.visibility = View.GONE

            binding.txtName.text = cast.name
            Glide.with(context).load("https://image.tmdb.org/t/p/original/${cast.profilePath}")
                .placeholder(
                    R.drawable.placeholder
                ).into(binding.imgPhoto)
        }
    }
}