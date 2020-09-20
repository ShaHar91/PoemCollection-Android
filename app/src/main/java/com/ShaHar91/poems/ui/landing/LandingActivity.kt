package com.shahar91.poems.ui.landing

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.shahar91.poems.R
import com.shahar91.poems.ui.base.BaseActivity
import com.shahar91.poems.ui.home.HomeActivity
import java.util.*

class LandingActivity : BaseActivity<LandingViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        viewModel = ViewModelProvider(this).get(LandingViewModel::class.java)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                HomeActivity.start(this@LandingActivity)
                finish()
            }
        }, 2000L)
    }
}