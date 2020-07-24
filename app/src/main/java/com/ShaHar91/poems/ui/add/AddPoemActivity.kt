package com.shahar91.poems.ui.add

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import be.appwise.core.extensions.activity.snackBar
import be.appwise.core.extensions.libraries.copyFromRealm
import be.appwise.core.extensions.view.optionalCallbacks
import be.appwise.core.extensions.view.setErrorLayout
import com.google.android.material.chip.Chip
import com.shahar91.poems.R
import com.shahar91.poems.data.models.Category
import com.shahar91.poems.ui.add.adapter.CategoryAutoCompleteAdapter
import com.shahar91.poems.ui.base.normal.BaseGoogleMobileActivity
import kotlinx.android.synthetic.main.activity_add_poem.*
import kotlinx.android.synthetic.main.toolbar.*

class AddPoemActivity : BaseGoogleMobileActivity<AddPoemViewModel, AddPoemComponent>() {
    lateinit var adapter: ArrayAdapter<String>

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

        tilPoemTitle.editText?.optionalCallbacks(beforeTextChanged = {s, start, count, after -> resetErrorLayouts()})
        tilPoemBody.editText?.optionalCallbacks(beforeTextChanged = {s, start, count, after -> resetErrorLayouts()})
        tilPoemCategory.editText?.optionalCallbacks(beforeTextChanged = {s, start, count, after -> resetErrorLayouts()})
        
        btnSavePoem.setOnClickListener {
            checkToSavePoem()
        }

        viewModel.getAllCategories {
            atvCategories.threshold = 0
            atvCategories.setAdapter(CategoryAutoCompleteAdapter(this, it.copyFromRealm().toTypedArray()))
            atvCategories.setOnItemClickListener { adapterView, view, i, l ->
                atvCategories.text = null
                val category = adapterView.getItemAtPosition(i) as Category

                addChip(category)
            }
        }
    }

    private fun addChip(category: Category) {
        val chip = layoutInflater.inflate(R.layout.chip_category, null) as Chip
            chip.apply {
                text = category.name
                tag = category.id

                setOnCloseIconClickListener {
                    cgCategories.removeView(it)
                }
            }
        cgCategories.addView(chip)
    }

    private fun resetErrorLayouts() {
        tilPoemTitle.setErrorLayout(null)
        tilPoemBody.setErrorLayout(null)
        tilPoemCategory.setErrorLayout(null)
    }

    private fun checkToSavePoem() {
        resetErrorLayouts()

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
}
//info@stevenverheyen.be
//GmuDrqtR2ujc