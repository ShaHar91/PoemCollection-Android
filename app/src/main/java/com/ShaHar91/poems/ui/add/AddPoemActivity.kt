package com.shahar91.poems.ui.add

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.text.Spanned
import android.text.style.ImageSpan
import androidx.core.content.ContextCompat
import com.google.android.material.chip.ChipDrawable
import com.shahar91.poems.R
import com.shahar91.poems.databinding.ActivityAddPoemBinding
import com.shahar91.poems.domain.model.Category
import com.shahar91.poems.ui.base.PoemBaseActivity
import com.shahar91.poems.utils.DialogFactory
import com.thedeadpixelsociety.passport.Passport
import com.thedeadpixelsociety.passport.TextInputLayoutValidator
import com.thedeadpixelsociety.passport.passport
import com.thedeadpixelsociety.passport.required
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddPoemActivity : PoemBaseActivity<ActivityAddPoemBinding>() {
    companion object {

        @JvmStatic
        fun startWithIntent(context: Context): Intent {
            return Intent(context, AddPoemActivity::class.java)
        }
    }

    override val mViewModel: AddPoemViewModel by viewModel()
    override fun getLayout() = R.layout.activity_add_poem

    private lateinit var validation: Passport

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.viewModel = mViewModel

        initValidation()
        initViews()
        initObservers()
    }

    private fun initValidation() {
        validation = passport {
            Passport.validatorFactory { TextInputLayoutValidator() }

            rules<String>(mBinding.tilPoemTitle) {
                required(getString(R.string.add_poem_required_title))
            }

            rules<String>(mBinding.tilPoemBody) {
                required(getString(R.string.add_poem_required_title))
            }

            rules<String>(mBinding.tilPoemCategory) {
                required("At least 1 Category should be added")
            }
        }
    }

    private fun initViews() {
        configureToolbar(mBinding.activityToolbar.toolbar, R.string.add_poem_toolbar_title, true, R.drawable.ic_close)
        mBinding.activityToolbar.toolbar.navigationIcon?.colorFilter = PorterDuffColorFilter(
            ContextCompat.getColor(this, R.color.colorWhite),
            PorterDuff.Mode.SRC_IN
        )

        mBinding.btnSavePoem.setOnClickListener {
            checkToSavePoem()
        }
    }

    private fun initObservers() {
        mViewModel.categoriesLive.observe(this) { categories ->
            mBinding.tilPoemCategory.editText?.setOnClickListener {
                DialogFactory.showDialogToAddCategories(this, categories, mViewModel.checkedCategories) {
                    mViewModel.checkedCategories = it
                    mBinding.atvCategory.text = null
                    it.forEach { category -> addChip(category) }
                }
            }
        }
    }

    private fun addChip(category: Category) {
        val span = ImageSpan(createChip(category))
        mBinding.atvCategory.text = mBinding.atvCategory.text?.append("${category.name}, ")
        val text = mBinding.atvCategory.text
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

    private fun checkToSavePoem() {

        if (validation.validate(this)) {
            mViewModel.addNewPoem(mViewModel.poemTitle.value!!, mViewModel.poemBody.value!!) {
                finishThisActivity(Activity.RESULT_OK)
            }
        }
    }
}