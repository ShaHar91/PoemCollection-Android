package com.shahar91.poems.ui.entry

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.shahar91.poems.R
import com.shahar91.poems.ui.base.normal.BaseGoogleMobileActivity

class EntryActivity : BaseGoogleMobileActivity<EntryViewModel, EntryComponent>() {
    companion object {
        @JvmStatic
        fun startWithIntent(context: Context): Intent {
            return Intent(context, EntryActivity::class.java)
        }
    }

    override fun createComponent(): EntryComponent {
        return DaggerEntryComponent.builder()
            .applicationComponent(appComponent)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)

        //TODO: this tampers with scrolling the layout when the keyboard is present, look into this when fragments are implemented, if issue still persists, try something else 😅
        // https://stackoverflow.com/questions/27856603/lollipop-draw-behind-statusbar-with-its-color-set-to-transparent
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        component.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(EntryViewModel::class.java)

//        setResult(Activity.RESULT_OK)
//        finish()
    }
}
