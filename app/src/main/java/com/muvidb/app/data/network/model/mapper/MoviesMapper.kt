package com.muvidb.app.data.network.model.mapper

import com.muvidb.app.data.network.model.response.ResultsItem
import com.muvidb.app.ui.viewparam.MovieViewParam
import com.muvidb.app.utils.mapper.ViewParamMapper

object MoviesMapper : ViewParamMapper<ResultsItem, MovieViewParam> {
    override fun toViewParam(dataObject: ResultsItem?): MovieViewParam = MovieViewParam(
        poster_path = dataObject?.posterPath.orEmpty(),
        overview = dataObject?.overview.orEmpty(),
        release_date = dataObject?.releaseDate.orEmpty(),
        original_title = dataObject?.originalTitle.orEmpty(),
        original_language = dataObject?.originalLanguage.orEmpty(),
        title = dataObject?.title.orEmpty(),
        backdrop_path = dataObject?.backdropPath.orEmpty(),
        popularity = dataObject?.popularity ?: 0.0,
        vote_count = dataObject?.voteCount ?: 0,
        video = dataObject?.video ?: false,
        vote_average = dataObject?.voteAverage ?: 0.0,
        id = dataObject?.id ?: 0,
    )
}