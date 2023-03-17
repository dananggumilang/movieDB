package com.muvidb.app.data.network.model.mapper

import com.muvidb.app.data.local.entity.MovieEntity
import com.muvidb.app.ui.viewparam.MovieViewParam
import com.muvidb.app.utils.mapper.ViewParamMapper

object FavouriteMoviesMapper : ViewParamMapper<MovieEntity, MovieViewParam> {
    override fun toViewParam(dataObject: MovieEntity?): MovieViewParam =
        MovieViewParam(
            poster_path = dataObject?.poster_path.orEmpty(),
            overview = dataObject?.overview.orEmpty(),
            release_date = dataObject?.release_date.orEmpty(),
            original_title = dataObject?.original_title.orEmpty(),
            original_language = dataObject?.original_language.orEmpty(),
            title = dataObject?.title.orEmpty(),
            backdrop_path = dataObject?.backdrop_path.orEmpty(),
            popularity = dataObject?.popularity ?: 0.0,
            vote_count = dataObject?.vote_count ?: 0,
            video = dataObject?.video ?: false,
            vote_average = dataObject?.vote_average ?: 0.0,
            id = dataObject?.id ?: 0,
            isFavorite = dataObject?.isFavorite ?: false
        )
}

object AddFavouriteMovieMapper : ViewParamMapper<MovieViewParam, MovieEntity> {
    override fun toViewParam(dataObject: MovieViewParam?): MovieEntity =
        MovieEntity(
            poster_path = dataObject?.poster_path.orEmpty(),
            overview = dataObject?.overview.orEmpty(),
            release_date = dataObject?.release_date.orEmpty(),
            original_title = dataObject?.original_title.orEmpty(),
            original_language = dataObject?.original_language.orEmpty(),
            title = dataObject?.title.orEmpty(),
            backdrop_path = dataObject?.backdrop_path.orEmpty(),
            popularity = dataObject?.popularity ?: 0.0,
            vote_count = dataObject?.vote_count ?: 0,
            video = dataObject?.video ?: false,
            vote_average = dataObject?.vote_average ?: 0.0,
            id = dataObject?.id ?: 0,
            isFavorite = dataObject?.isFavorite ?: false
        )
}
