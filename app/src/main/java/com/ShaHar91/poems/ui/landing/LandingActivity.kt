package com.shahar91.poems.ui.landing

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.shahar91.poems.R
import com.shahar91.poems.ui.base.normal.BaseGoogleMobileActivity
import com.shahar91.poems.ui.home.HomeActivity
import com.shahar91.poems.ui.landing.LandingActivity
import java.util.*
import javax.inject.Inject

class LandingActivity : BaseGoogleMobileActivity<LandingViewModel, LandingComponent>() {

    override fun createComponent(): LandingComponent {
        return DaggerLandingComponent.builder()
            .applicationComponent(appComponent)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        component.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LandingViewModel::class.java)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                HomeActivity.start(this@LandingActivity)
                finish()
            }
        }, 2000L)
    }
}