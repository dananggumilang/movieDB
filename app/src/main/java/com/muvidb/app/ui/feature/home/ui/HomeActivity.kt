package com.muvidb.app.ui.feature.home.ui

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.muvidb.app.base.arch.BaseActivity
import com.muvidb.app.databinding.ActivityHomeBinding
import com.muvidb.app.ui.feature.detailmovie.DetailMovieActivity
import com.muvidb.app.ui.feature.favoritemovies.ui.FavoriteMoviesActivity
import com.muvidb.app.ui.feature.genreslist.GenresListActivity
import com.muvidb.app.ui.feature.home.adapter.PlayingMovieAdapter
import com.muvidb.app.ui.feature.home.adapter.PopularMovieAdapter
import com.muvidb.app.ui.feature.home.adapter.UpComingMovieAdapter
import com.muvidb.app.utils.ext.subscribe
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(ActivityHomeBinding::inflate) {

    override val viewModel: HomeViewModel by viewModel()
    private lateinit var playingMovieAdapter: PlayingMovieAdapter
    private val popularMovieAdapter = PopularMovieAdapter()
    private val upComingMovieAdapter = UpComingMovieAdapter()

    override fun initData() {
        viewModel.getPlayingMovies()
        viewModel.getPopularMovies()
        viewModel.getUpComingMovies()
    }

    override fun initView() {
        setRecyclerView()

        binding.ivMenu.setOnClickListener {
            startActivity(Intent(this, FavoriteMoviesActivity::class.java))
        }
    }

    private fun setRecyclerView() {

        playingMovieAdapter = PlayingMovieAdapter { v, items ->
            val intent = Intent(this, DetailMovieActivity::class.java)
            intent.putExtra("MOVIE_ITEM", items)
            startActivity(intent)
        }

        with(binding.rvPlayingMovie) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = playingMovieAdapter
        }

        with(binding.rvPopularMovie) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularMovieAdapter
        }

        with(binding.rvUpcomingMovie) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = upComingMovieAdapter
        }
    }

    override fun observeData() {
        viewModel.getPlayingMoviesResult.observe(this) {
            it.subscribe(
                doOnSuccess = { response ->
                    showLoading(false)
                    response.data?.let { data -> playingMovieAdapter.setItems(data) }
                },
                doOnLoading = {
                    showLoading(true)
                },
                doOnError = {
                    showLoading(false)
                },
                doOnEmpty = {
                    showLoading(false)
                }
            )
        }

        viewModel.getPopularMoviesResult.observe(this) {
            it.subscribe(
                doOnSuccess = { response ->
                    showLoading(false)
                    response.data?.let { data -> popularMovieAdapter.setItems(data) }
                },
                doOnLoading = {
                    showLoading(true)
                },
                doOnError = {
                    showLoading(false)
                },
                doOnEmpty = {
                    showLoading(false)
                }
            )
        }

        viewModel.getUpComingMoviesResult.observe(this) {
            it.subscribe(
                doOnSuccess = { response ->
                    showLoading(false)
                    response.data?.let { data -> upComingMovieAdapter.setItems(data) }
                },
                doOnLoading = {
                    showLoading(true)
                },
                doOnError = {
                    showLoading(false)
                },
                doOnEmpty = {
                    showLoading(false)
                }
            )
        }
    }

    private fun showLoading(isShowLoading: Boolean) {

    }

}