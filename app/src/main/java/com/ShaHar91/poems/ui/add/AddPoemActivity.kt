package com.shahar91.poems.ui.add

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import be.appwise.core.extensions.view.setErrorLayout
import com.shahar91.poems.R
import com.shahar91.poems.ui.base.normal.BaseGoogleMobileActivity
import kotlinx.android.synthetic.main.activity_add_poem.*
import kotlinx.android.synthetic.main.toolbar.*

class AddPoemActivity : BaseGoogleMobileActivity<AddPoemViewModel, AddPoemComponent>() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(Intent(context, AddPoemActivity::class.java))
        }

        @JvmStatic
        fun startWithIntent(context: Context): Intent {
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
        configureToolbar(toolbar, true, R.string.add_poem_toolbar_title, R.drawable.ic_close)

        btnSavePoem.setOnClickListener {
            checkToSavePoem()
        }
    }

    private fun checkToSavePoem() {
        tilPoemTitle.setErrorLayout(null)
        tilPoemBody.setErrorLayout(null)

        var isValid = true
        val poemTitle = tilPoemTitle.editText?.text?.toString() ?: ""
        val poemBody = tilPoemBody.editText?.text?.toString() ?: ""

        if (poemTitle.isBlank()) {
            tilPoemTitle.setErrorLayout(getString(R.string.add_poem_required_title))
            isValid = false
        }

        if (poemBody.isBlank()) {
            tilPoemBody.setErrorLayout(getString(R.string.add_poem_required_body))
            isValid = false
        }

        if (cgCategories.childCount == 0) {
            tilPoemCategory.setErrorLayout("Add at least one Category")
            isValid = false
        }

        //        ArrayList("5d725c84c4ded7bcb480eaa0", "5d725c84c4ded7bcb480eaa0")
        if (isValid) {
            viewModel.addNewPoem(poemTitle, poemBody, listOf("5d725c84c4ded7bcb480eaa0"), {
                finishThisActivity(Activity.RESULT_OK)
            }, {
                snackBar(it.message ?: "")
            })
        }
    }
