package com.shahar91.poems.ui.home.poem

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.fragment.navArgs
import be.appwise.core.extensions.view.setupRecyclerView
import com.shahar91.poems.Constants
import com.shahar91.poems.R
import com.shahar91.poems.data.models.Review
import com.shahar91.poems.databinding.FragmentPoemBinding
import com.shahar91.poems.ui.base.PoemBaseBindingVMFragment
import com.shahar91.poems.ui.entry.EntryActivity.Companion.newIntent
import com.shahar91.poems.ui.home.poem.adapter.PoemReviewsAdapter
import com.shahar91.poems.utils.DialogFactory.showDialogOkCancel
import com.shahar91.poems.utils.DialogFactory.showDialogToAddReview
import com.shahar91.poems.utils.DialogFactory.showDialogToEditReview
import com.shahar91.poems.utils.HawkManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PoemFragment : PoemBaseBindingVMFragment<FragmentPoemBinding>() {
    private val safeArgs: PoemFragmentArgs by navArgs()
    private val poemReviewsAdapter = PoemReviewsAdapter()

    override fun getLayout() = R.layout.fragment_poem
    override fun getToolbar() = mBinding.mergeToolbar.toolbar
    override val mViewModel: PoemViewModel by viewModel { parametersOf(safeArgs.poemId) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.viewModel = mViewModel

        initViews()
        initObservers()
    }

    private fun initObservers() {
        mViewModel.shortReviewList.observe(viewLifecycleOwner) {
            poemReviewsAdapter.submitList(it)
        }

        mViewModel.delayedRating.observe(viewLifecycleOwner) { delayedRating ->
            if (delayedRating != null) {
                if (mViewModel.ownReview.value != null) {
                    showDialogOkCancel(requireActivity(), "Review already exists",
                        "You already have an existing review for this poem, do you want to edit the previous one?", {
                            mViewModel.ownReview.value?.review?.let { showEditReviewDialog(it) }
                        }, {
                            mViewModel.resetRating()
                        })
                } else {
                    showAddReviewDialog(delayedRating)
                }
            }
        }
    }

    private fun initViews() {
        mBinding.run {
            rvPoemReviews.run {
                setupRecyclerView(null)
                adapter = poemReviewsAdapter
            }

            ownReviewLayout.ivReviewMenu.setOnClickListener {
                PopupMenu(requireContext(), it).run {
                    menuInflater.inflate(R.menu.menu_review, this.menu)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.review_edit -> {
                                mViewModel.ownReview.value?.review?.let { review ->
                                    showEditReviewDialog(review)
                                }
                            }

                            R.id.review_delete -> {
                                mViewModel.ownReview.value?.review?.let { review ->
                                    mViewModel.deleteReview(review.id)
                                }
                            }
                        }
                        true
                    }
                    show()
                }
            }

            noReview.setOnRatingChangedListener { ratingBar, rating, fromUser ->
                if (fromUser) {
                    if (!HawkManager.currentUserId.isNullOrBlank()) {
                        showAddReviewDialog(rating)
                    } else {
                        // start the EntryActivity to make sure the user gets logged in
                        startActivityForResult(
                            newIntent(requireContext(), rating),
                            Constants.REQUEST_CODE_NEW_USER
                        )
                    }

                    Handler().postDelayed({
                        ratingBar.rating = 0f
                    }, 500)
                }
            }
        }
    }

    override fun onError(throwable: Throwable) {
        super.onError(throwable)
        mViewModel.resetRating()
    }

    private fun showAddReviewDialog(rating: Float) {
        mViewModel.resetRating()
        showDialogToAddReview(requireActivity(), rating) { reviewId, reviewText, newRating ->
            mViewModel.saveOrUpdateReview(reviewId, reviewText, newRating)
        }
    }

    private fun showEditReviewDialog(review: Review) {
        mViewModel.resetRating()
        showDialogToEditReview(requireActivity(), review) { reviewId, reviewText, newRating ->
            mViewModel.saveOrUpdateReview(reviewId, reviewText, newRating)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == Constants.REQUEST_CODE_NEW_USER) {
            if (resultCode == Activity.RESULT_OK) {
                // A user has been logged in successfully
                // Refresh the poem and all reviews (as the user's review may have been in the preview list)
                mViewModel.getPoemAndAllData(data?.getFloatExtra(Constants.ACTIVITY_RESPONSE_RATING_KEY, -1f))
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}