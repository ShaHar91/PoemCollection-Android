package com.shahar91.poems.ui.home.poem

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import be.appwise.core.extensions.view.setupRecyclerView
import be.appwise.core.networking.Networking.isLoggedIn
import com.shahar91.poems.Constants
import com.shahar91.poems.R
import com.shahar91.poems.data.models.Review
import com.shahar91.poems.databinding.FragmentPoemBinding
import com.shahar91.poems.ui.base.PoemBaseFragment
import com.shahar91.poems.ui.entry.EntryActivity.Companion.startWithIntent
import com.shahar91.poems.ui.home.poem.adapter.PoemReviewsAdapter
import com.shahar91.poems.utils.DialogFactory.showDialogOkCancel
import com.shahar91.poems.utils.DialogFactory.showDialogToAddReview
import com.shahar91.poems.utils.DialogFactory.showDialogToEditReview

class PoemFragment : PoemBaseFragment<PoemViewModel>() {
    private val safeArgs: PoemFragmentArgs by navArgs()
    private lateinit var mDataBinding: FragmentPoemBinding
    private lateinit var poemReviewsAdapter: PoemReviewsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mDataBinding = DataBindingUtil.inflate<FragmentPoemBinding>(inflater, R.layout.fragment_poem, container, false)
            .apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = ViewModelProvider(this@PoemFragment, PoemViewModel.FACTORY(safeArgs.poemId))
                    .get(PoemViewModel::class.java)
                    .apply {
                        mViewModel = this
                        setDefaultExceptionHandler(::onError)

                        getPoemAndAllDataCr()
                    }
            }

        initViews()

        return mDataBinding.root
    }

    private fun initViews() {
        poemReviewsAdapter = PoemReviewsAdapter(requireActivity(),
            object : PoemReviewsAdapter.ReviewInteractionListener {})

        mDataBinding.rvPoemReviews.apply {
            setupRecyclerView(null)
            adapter = poemReviewsAdapter
        }

        mViewModel.getRefreshLayout().observe(this, Observer {
            if (it) {
                showPoem()
            }
        })
    }

    override fun onError(throwable: Throwable) {
        super.onError(throwable)
        mViewModel.resetRating()
    }

    private fun showPoem() {
        mDataBinding.apply {
            mViewModel.poem.value?.let { poem ->
                mViewModel.ownReview.value?.let { review ->
                    ownReviewLayout.rhUserHeader.userName = review.user?.username ?: ""
                    ownReviewLayout.rhUserHeader.rating = review.rating
                    ownReviewLayout.tvReviewBody.text = review.text

                    ownReviewLayout.ivReviewMenu.setOnClickListener {
                        PopupMenu(requireContext(), it).apply {
                            menuInflater.inflate(R.menu.menu_review, this.menu)
                            setOnMenuItemClickListener { item ->
                                when (item.itemId) {
                                    R.id.review_edit -> {
                                        showEditReviewDialog(review)
                                    }
                                    R.id.review_delete ->
                                        mViewModel.deleteReview(review._id)
                                }
                                true
                            }
                            show()
                        }
                    }
                } ?: run {
                    mDataBinding.noReview.setOnRatingChangedListener { ratingBar, rating, fromUser ->
                        if (fromUser) {
                            if (isLoggedIn()) {
                                showAddReviewDialog(rating)
                            } else {
                                // start the EntryActivity to make sure the user gets logged in
                                startActivityForResult(startWithIntent(requireContext(), rating),
                                    Constants.REQUEST_CODE_NEW_USER)
                            }

                            Handler().postDelayed({
                                ratingBar.rating = 0f
                            }, 500)
                        }
                    }
                }

                poemReviewsAdapter.setItems(poem.shortReviewList)
            }

            if (mViewModel.delayedRating != null) {
                if (mViewModel.ownReview.value != null) {
                    showDialogOkCancel(requireActivity(), "Review already exists",
                        "You already have an existing review for this poem, do you want to edit the previous one?", {
                            showEditReviewDialog(mViewModel.ownReview.value!!)
                        }, {
                            mViewModel.resetRating()
                        })
                } else {
                    showAddReviewDialog(mViewModel.delayedRating ?: 0F)
                }
            }
        }
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
                mViewModel.getPoemAndAllDataCr(data?.getFloatExtra(Constants.ACTIVITY_RESPONSE_RATING_KEY, -1f))
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}