package com.shahar91.poems.ui.add

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.shahar91.poems.R
import com.shahar91.poems.ui.base.normal.BaseGoogleMobileActivity
import kotlinx.android.synthetic.main.toolbar.*

class AddPoemActivity : BaseGoogleMobileActivity<AddPoemViewModel, AddPoemComponent>() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(Intent(context, AddPoemActivity::class.java))
        }

        @JvmStatic
        fun startWithIntent(context: Context): Intent{
            return Intent(context, AddPoemActivity::class.java)
        }
    }

    override fun createComponent(): AddPoemComponent {
        return DaggerAddPoemComponent.builder()
            .applicationComponent(appComponent)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_poem)

        component.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(AddPoemViewModel::class.java)

        initViews()
    }

    private fun initViews() {
        configureToolbar(toolbar, true, R.string.app_name, R.drawable.ic_close)
    }
}
