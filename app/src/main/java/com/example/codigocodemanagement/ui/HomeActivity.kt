package com.example.codigocodemanagement.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.codigocodemanagement.adapter.PopularAdapter
import com.example.codigocodemanagement.adapter.UpcomingAdapter
import com.example.codigocodemanagement.databinding.ActivityHomeBinding
import com.example.codigocodemanagement.utility.delegateutils.activityViewBinding
import com.example.codigocodemanagement.utility.kodeinViewModel
import com.example.codigocodemanagement.viewmodel.MovieViewModel
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI

class HomeActivity : AppCompatActivity(), DIAware {
    override val di by closestDI()
    private val binding by activityViewBinding(ActivityHomeBinding::inflate)
    private lateinit var popularAdapter: PopularAdapter
    var alert: AlertDialog? = null
    private lateinit var upcomingAdapter: UpcomingAdapter
    private val movieViewModel: MovieViewModel by kodeinViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeLocalMovies()
        observePopular()
        observeUpcoming()
        loadData()
    }

    override fun onResume() {
        super.onResume()
        setAdapter()
        loadData()
    }

    private fun loadData() {
        movieViewModel.getMoviesFromDB(0)
        movieViewModel.getMoviesFromDB(1)
        movieViewModel.getPopularMovies()
        movieViewModel.getUpcomingMovies()
    }

    private fun setAdapter() {
        popularAdapter = PopularAdapter(this, movieViewModel)
        binding.rcvRecommended.adapter = popularAdapter

        upcomingAdapter = UpcomingAdapter(this, movieViewModel)
        binding.rcvUpcoming.adapter = upcomingAdapter
    }

    private fun observeLocalMovies() {
        movieViewModel.localMovieListPopularLiveData.observe(this) {

            if (!(it.isNullOrEmpty())) {
                popularAdapter.submitList(it)

                binding.sflHome.stopShimmer()
                binding.sflHome.visibility = View.GONE
                binding.cslHome.visibility = View.VISIBLE
            }
        }
        movieViewModel.localMovieListUpcomingLiveData.observe(this) {
            if (!(it.isNullOrEmpty())) {
                upcomingAdapter.submitList(it)

                binding.sflHome.stopShimmer()
                binding.sflHome.visibility = View.GONE
                binding.cslHome.visibility = View.VISIBLE
            }
        }
    }

    private fun observePopular() {
        movieViewModel.popularMovieListLiveData.observe(this) {

            popularAdapter.submitList(it)

        }
    }

    private fun observeUpcoming() {
        movieViewModel.upcomingMovieListLiveData.observe(this) {
            upcomingAdapter.submitList(it)

            if (!(it.isNullOrEmpty())) {
                if (alert != null) {
                    alert!!.dismiss()
                }
                binding.sflHome.stopShimmer()
                binding.sflHome.visibility = View.GONE
                binding.cslHome.visibility = View.VISIBLE
            } else {
                showRetryDialog()
            }
        }
    }

    private fun showRetryDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("No Internet Connection, try again")
            .setCancelable(false)
            .setPositiveButton("Retry") { _, id ->
                loadData()
            }
        alert = builder.create()
        alert!!.show()
    }

}