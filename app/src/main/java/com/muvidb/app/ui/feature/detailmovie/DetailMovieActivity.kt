package com.muvidb.app.ui.feature.detailmovie

import com.muvidb.app.base.arch.BaseActivity
import com.muvidb.app.databinding.ActivityDetailMovieBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieActivity: BaseActivity<ActivityDetailMovieBinding, DetailMovieViewModel>(ActivityDetailMovieBinding::inflate) {

    override val viewModel: DetailMovieViewModel by viewModel()

    override fun initView() {
        // TODO: Set UI from Parcelable
    }

    override fun observeData() {
        // TODO: Observe Data From View Model
    }

    override fun initData() {
        // TODO: Get Movie Trailer
    }

}