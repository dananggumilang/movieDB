package com.muvidb.app.ui.feature.home

import com.muvidb.app.base.arch.BaseActivity
import com.muvidb.app.databinding.ActivityHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(ActivityHomeBinding::inflate) {

    override val viewModel: HomeViewModel by viewModel()

    override fun initView() {
        // TODO: Get Movies List
    }

    override fun observeData() {
        // TODO: Observe Data From View Model
    }

}