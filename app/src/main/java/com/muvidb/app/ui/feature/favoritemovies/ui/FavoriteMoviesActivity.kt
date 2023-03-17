package com.muvidb.app.ui.feature.favoritemovies.ui

import android.content.Intent
import androidx.recyclerview.widget.GridLayoutManager
import com.muvidb.app.base.arch.BaseActivity
import com.muvidb.app.databinding.ActivityFavoriteMoviesBinding
import com.muvidb.app.ui.feature.detailmovie.DetailMovieActivity
import com.muvidb.app.ui.feature.favoritemovies.adapter.FavoriteMoviesAdapter
import com.muvidb.app.utils.ext.subscribe
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteMoviesActivity : BaseActivity<ActivityFavoriteMoviesBinding, FavoriteMoviesViewModel>(ActivityFavoriteMoviesBinding::inflate) {

    override val viewModel: FavoriteMoviesViewModel by viewModel()
    private lateinit var favoriteMoviesAdapter: FavoriteMoviesAdapter

    override fun initData() {
        viewModel.getFavouriteMovies()
    }

    override fun initView() {
        setRecyclerView()
    }

    private fun setRecyclerView() {
        favoriteMoviesAdapter = FavoriteMoviesAdapter { v, items ->
            val intent = Intent(this, DetailMovieActivity::class.java)
            intent.putExtra("MOVIE_ITEM", items)
            startActivity(intent)
        }

        with(binding.rvFavoriteMovies) {
            layoutManager = GridLayoutManager(this@FavoriteMoviesActivity, 3)
            adapter = favoriteMoviesAdapter
        }
    }

    override fun observeData() {
        viewModel.getFavouriteMoviesResult.observe(this) {
            it.subscribe(
                doOnSuccess = { result ->
                    result.data?.let { it -> favoriteMoviesAdapter.setItems(it) }
                },
                doOnError = { error ->
                    println(error.exception)
                }
            )
        }
    }

}