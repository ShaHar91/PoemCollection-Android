package com.shahar91.poems.ui.add

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.text.Spanned
import android.text.style.ImageSpan
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import be.appwise.core.extensions.activity.snackBar
import be.appwise.core.extensions.view.optionalCallbacks
import be.appwise.core.extensions.view.setErrorLayout
import com.google.android.material.chip.ChipDrawable
import com.shahar91.poems.R
import com.shahar91.poems.data.models.Category
import com.shahar91.poems.ui.base.normal.BaseGoogleMobileActivity
import com.shahar91.poems.utils.DialogFactory
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
        toolbar.navigationIcon?.colorFilter = PorterDuffColorFilter(ContextCompat.getColor(this, R.color.colorWhite), PorterDuff.Mode.SRC_IN)

        tilPoemTitle.editText?.optionalCallbacks(beforeTextChanged = { _, _, _, _ -> resetErrorLayouts() })
        tilPoemBody.editText?.optionalCallbacks(beforeTextChanged = { _, _, _, _ -> resetErrorLayouts() })
        tilPoemCategory.editText?.optionalCallbacks(beforeTextChanged = { _, _, _, _ -> resetErrorLayouts() })

        btnSavePoem.setOnClickListener {
            checkToSavePoem()
        }

        viewModel.getAllCategories { categories ->
            tilPoemCategory.editText?.setOnClickListener {
                DialogFactory.showDialogToAddCategories(this, categories, viewModel.checkedCategories) {
                    viewModel.checkedCategories = it
                    atvCategory.text = null
                    it.forEach { category -> addChip(category) }
                }
            }
        }
    }

    private fun addChip(category: Category) {
        val span = ImageSpan(createChip(category))
        atvCategory.text = atvCategory.text?.append("${category.name}, ")
        val text = atvCategory.text
        text?.setSpan(span, text.length - (category.name.length + 2), text.length - 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
    }

    private fun createChip(category: Category): ChipDrawable {
        val chip = ChipDrawable.createFromResource(this, R.xml.chip_category)
        chip.text = category.name
        chip.setTextAppearanceResource(R.style.Category_Chip_TextAppearance)
        // setBounds should be done as the very last thing
        chip.setBounds(0, 0, chip.intrinsicWidth, chip.intrinsicHeight)
        chip.chipEndPadding = 10f
        return chip
    }

    private fun resetErrorLayouts() {
        tilPoemTitle.setErrorLayout(null)
        tilPoemBody.setErrorLayout(null)
        tilPoemCategory.setErrorLayout(null)
    }

    //TODO: when poem is added, refresh the pages the user is on...
    private fun checkToSavePoem() {
        resetErrorLayouts()

        var isValid = true
        val poemTitle = tilPoemTitle.editText?.text?.toString() ?: ""
        val poemBody = tilPoemBody.editText?.text?.toString() ?: ""
        val poemCategories = tilPoemCategory.editText?.text?.toString() ?: ""

        if (poemTitle.isBlank()) {
            tilPoemTitle.setErrorLayout(getString(R.string.add_poem_required_title))
            isValid = false
        }

        if (poemBody.isBlank()) {
            tilPoemBody.setErrorLayout(getString(R.string.add_poem_required_body))
            isValid = false
        }

        if (poemCategories.isBlank()) {
            tilPoemCategory.setErrorLayout("Add at least one Category")
            isValid = false
        }

        if (isValid) {
            viewModel.addNewPoem(poemTitle, poemBody, {
                finishThisActivity(Activity.RESULT_OK)
            }, {
                snackBar(it.message ?: "")
            })
        }
    }
}