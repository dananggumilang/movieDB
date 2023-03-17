package com.muvidb.app.ui.feature.detailmovie

import androidx.core.content.ContextCompat
import com.muvidb.app.R
import com.muvidb.app.base.arch.BaseActivity
import com.muvidb.app.databinding.ActivityDetailMovieBinding
import com.muvidb.app.ui.viewparam.MovieViewParam
import com.muvidb.app.utils.ext.subscribe
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieActivity : BaseActivity<ActivityDetailMovieBinding, DetailMovieViewModel>(ActivityDetailMovieBinding::inflate) {

    override val viewModel: DetailMovieViewModel by viewModel()
    private lateinit var currentMovie: MovieViewParam

    private val data: MovieViewParam? by lazy {
        intent?.extras?.getParcelable("MOVIE_ITEM")
    }

    override fun initData() {
        data?.let {
            currentMovie = it
            viewModel.getFavouriteMovieById(it.id)
        }
    }

    override fun initView() {
        binding.btnFavoriteMovie.setOnClickListener {
            if(currentMovie.isFavorite) {
                viewModel.deleteFavoriteMovie(currentMovie)
            } else {
                viewModel.addFavoriteMovie(currentMovie)
            }
        }
    }

    override fun observeData() {
        viewModel.getFavouriteMovieByIdResult.observe(this) {
            it.subscribe(
                doOnSuccess = { result ->
                    binding.btnFavoriteMovie.setImageDrawable(ContextCompat.getDrawable(this, if (result.data?.isFavorite == true) R.drawable.ic_favorite_true else R.drawable.ic_favorite_false))
                },
                doOnError = { error ->
                    println(error.exception)
                }
            )
        }

        viewModel.addFavoriteResult.observe(this) {
            it.subscribe(
                doOnSuccess = { result ->
                    setImageDrawableFavorite(result.data!!)
                },
                doOnError = { error ->
                    println(error.exception)
                }
            )
        }

        viewModel.removeFavoriteResult.observe(this) {
            it.subscribe(
                doOnSuccess = { result ->
                    setImageDrawableFavorite(result.data!!)
                },
                doOnError = { error ->
                    println(error.exception)
                }
            )
        }

    }

    private fun setImageDrawableFavorite(movieViewParam: MovieViewParam) {
        currentMovie = movieViewParam
        binding.btnFavoriteMovie.setImageDrawable(ContextCompat.getDrawable(this, if (currentMovie.isFavorite) R.drawable.ic_favorite_true else R.drawable.ic_favorite_false))
    }

}