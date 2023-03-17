package com.muvidb.app.ui.feature.detailmovie

import androidx.core.content.ContextCompat
import com.muvidb.app.R
import com.muvidb.app.base.arch.BaseActivity
import com.muvidb.app.databinding.ActivityDetailMovieBinding
import com.muvidb.app.ui.viewparam.MovieViewParam
import com.muvidb.app.utils.ext.subscribe
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieActivity :
    BaseActivity<ActivityDetailMovieBinding, DetailMovieViewModel>(ActivityDetailMovieBinding::inflate) {

    override val viewModel: DetailMovieViewModel by viewModel()
    private val dataParcelable: MovieViewParam? by lazy {
        intent?.extras?.getParcelable("MOVIE_ITEM")
    }
    private lateinit var currentMovie: MovieViewParam

    override fun initData() {
        dataParcelable?.let { movie ->
            currentMovie = movie
            viewModel.getFavouriteMovieById(movie.id)
            viewModel.getKeyMovieTrailer(movie.id)
        }
    }

    override fun initView() {

        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.ivFavorite.setOnClickListener {
            if (currentMovie.isFavorite) {
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
                    binding.ivFavorite.setImageDrawable(
                        ContextCompat.getDrawable(
                            this,
                            if (result.data?.isFavorite == true) R.drawable.ic_favorite_true else R.drawable.ic_favorite_false
                        )
                    )
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

        viewModel.getKeyMovieTrailerResult.observe(this) {
            it.subscribe(
                doOnSuccess = { result ->
                    println(result.data)
                    result.data?.let { keyMovieTrailer ->
                        binding.ytTrailer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                            override fun onReady(youTubePlayer: YouTubePlayer) {
                                super.onReady(youTubePlayer)
                                youTubePlayer.loadVideo(keyMovieTrailer, 0F)
                            }
                        })
                    }
                },
                doOnError = { error ->
                    println(error.exception)
                }
            )
        }
    }

    private fun setImageDrawableFavorite(movieViewParam: MovieViewParam) {
        currentMovie = movieViewParam
        binding.ivFavorite.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                if (currentMovie.isFavorite) R.drawable.ic_favorite_true else R.drawable.ic_favorite_false
            )
        )
    }

}