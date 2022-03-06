package com.example.codigocodemanagement.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.codigocodemanagement.R
import com.example.codigocodemanagement.adapter.CastAdapter
import com.example.codigocodemanagement.databinding.ActivityDetailBinding
import com.example.codigocodemanagement.utility.changeDateFormat
import com.example.codigocodemanagement.utility.convertHoursAndMinutes
import com.example.codigocodemanagement.utility.cutHoursAndMinutes
import com.example.codigocodemanagement.utility.delegateutils.activityViewBinding
import com.example.codigocodemanagement.utility.kodeinViewModel
import com.example.codigocodemanagement.viewmodel.MovieViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI


class DetailActivity : AppCompatActivity(), DIAware {
    override val di by closestDI()
    private val binding by activityViewBinding(ActivityDetailBinding::inflate)
    private lateinit var castAdapter: CastAdapter
    private val movieViewModel: MovieViewModel by kodeinViewModel()
    var movieId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        movieId = intent.getIntExtra("movieId", 0)
        castAdapter = CastAdapter(this)
        binding.rcvCast.adapter = castAdapter

        movieViewModel.getCasts(movieId)
        movieViewModel.getDetail(movieId)
        observeCasts()
        observeDetail()
        CoroutineScope(Dispatchers.Main).launch {
            if (movieViewModel.getFav(movieId) != null) {
                if (movieViewModel.getFav(movieId) == 0) {
                    binding.imgFav.setImageDrawable(getDrawable(R.drawable.ic_baseline_un_favorite_24))
                } else if (movieViewModel.getFav(movieId) == 1) {
                    binding.imgFav.setImageDrawable(getDrawable(R.drawable.ic_baseline_favorite_24))
                }
            }
        }

        binding.imgBack.setOnClickListener {
            super.onBackPressed()
        }
        binding.imgFav.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                if (movieViewModel.getFav(movieId) == 0) {
                    movieViewModel.updateFav(1, movieId)
                    binding.imgFav.setImageDrawable(getDrawable(R.drawable.ic_baseline_favorite_24))

                } else {
                    movieViewModel.updateFav(0, movieId)
                    binding.imgFav.setImageDrawable(getDrawable(R.drawable.ic_baseline_un_favorite_24))

                }
            }
        }
    }

    private fun observeCasts() {
        movieViewModel.castListLiveData.observe(this) {
            castAdapter.submitList(it)
        }
    }

    private fun observeDetail() {
        movieViewModel.movieDetailLiveData.observe(this) { mModel ->
            if (mModel != null) {
                Glide.with(this).load("https://image.tmdb.org/t/p/original/${mModel.backdropPath}")
                    .placeholder(
                        R.drawable.placeholder
                    ).into(binding.imgPhoto)
                val playTime = mModel.runtime!!.convertHoursAndMinutes().cutHoursAndMinutes()
                val genres = mModel.genres!!.joinToString { it.name }
                binding.txtName.text = mModel.title
                binding.txtPercentageRate.text = "${mModel.voteAverage} %"
                binding.txtTotalVotes.text = "${mModel.voteCount} votes"
                binding.txtProductionYear.text =
                    "${mModel.productionCountries!![0].iso} | ${mModel.releaseDate!!.changeDateFormat()}"
                binding.txtDurationGenres.text = "$playTime | $genres "
                binding.txtAudio.text = mModel.spokenLanguages!![0].name
                binding.txtDescription.text = mModel.overview


            }
        }
    }


}