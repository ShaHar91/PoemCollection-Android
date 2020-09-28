package com.shahar91.poems.ui.home.poem

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import be.appwise.core.extensions.logging.loge
import be.appwise.core.extensions.view.setupRecyclerView
import be.appwise.core.networking.Networking.isLoggedIn
import com.shahar91.poems.Constants
import com.shahar91.poems.R
import com.shahar91.poems.data.models.Review
import com.shahar91.poems.ui.base.PoemBaseFragment
import com.shahar91.poems.ui.entry.EntryActivity.Companion.startWithIntent
import com.shahar91.poems.ui.home.poem.adapter.PoemReviewsAdapter
import com.shahar91.poems.utils.DialogFactory.showDialogOkCancel
import com.shahar91.poems.utils.DialogFactory.showDialogToAddReview
import com.shahar91.poems.utils.DialogFactory.showDialogToEditReview
import kotlinx.android.synthetic.main.fragment_poem.*
import kotlinx.android.synthetic.main.list_item_global_rating.*
import kotlinx.android.synthetic.main.list_item_review.*
import kotlinx.android.synthetic.main.reuse_poem_detail.*

class PoemFragment : PoemBaseFragment<PoemViewModel>() {
    private lateinit var poemReviewsAdapter: PoemReviewsAdapter
    private val safeArgs: PoemFragmentArgs by navArgs()

    private val poemViewModelListener: PoemViewModel.ViewModelCallbacks = object :
        PoemViewModel.ViewModelCallbacks {
        override fun refreshLayout() {
            showPoem()
        }

        override fun error(throwable: Throwable) {
            loge(null, throwable)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_poem, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PoemViewModel::class.java)
        viewModel.init(safeArgs.poemId, poemViewModelListener)

        initViews()

        viewModel.getPoemAndAllReviews()
    }

    private fun initViews() {
        poemReviewsAdapter = PoemReviewsAdapter(requireActivity(),
            object : PoemReviewsAdapter.ReviewInteractionListener {})

        rvPoemReviews.apply {
            setupRecyclerView(null)
            adapter = poemReviewsAdapter
        }
    }

    private fun showPoem() {
        viewModel.poem?.let { poem ->
            loadingView.isGone = true
            poemLayout.isVisible = true

            tvPoemTitle.text = poem.title
            tvPoem.text = poem.body
            tvWriterPoemDetail.text = poem.user?.username

            noReview.isVisible = viewModel.ownReview == null
            viewModel.ownReview?.let {
                noReview.setOnRatingChangedListener { ratingBar, rating, fromUser ->
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

            ownReviewLayout.isVisible = viewModel.ownReview != null

            viewModel.ownReview?.let { review ->
                rhUserHeader.userName = review.user?.username ?: ""
                rhUserHeader.rating = review.rating
                tvReviewBody.text = review.text

                ivReviewMenu.setOnClickListener {
                    PopupMenu(requireContext(), it).apply {
                        menuInflater.inflate(R.menu.menu_review, this.menu)
                        setOnMenuItemClickListener { item ->
                            when (item.itemId) {
                                R.id.review_edit -> {
                                    showEditReviewDialog(review)
                                }
                                R.id.review_delete ->
                                    viewModel.deleteReview(review._id)
                            }
                            true
                        }
                        show()
                    }
                }
            }

            tvAverageRating.text = String.format("%.1f", poem.averageRating)
            rbTotalRating.rating = poem.averageRating
            tvTotalReviews.text = viewModel.totalReviews.toString()

            listOf(pbOneStarRating, pbTwoStarRating, pbThreeStarRating,
                pbFourStarRating, pbFiveStarRating).forEachIndexed { i, pb ->
                pb.max = viewModel.totalReviews
                pb.progress = poem.totalRatingCount[i] ?: 0
            }

            poemReviewsAdapter.setItems(poem.shortReviewList)
        }

        if (viewModel.delayedRating != null) {
            if (viewModel.ownReview != null) {
                showDialogOkCancel(requireActivity(), "Review already exists",
                    "You already have an existing review for this poem, do you want to edit the previous one?", {
                        showEditReviewDialog(viewModel.ownReview!!)
                    }, {
                        viewModel.resetRating()
                    })
            } else {
                showAddReviewDialog(viewModel.delayedRating ?: 0F)
            }
        }
    }

    private fun showAddReviewDialog(rating: Float) {
        viewModel.resetRating()
        showDialogToAddReview(requireActivity(), rating, viewModel::saveOrUpdateReview)
    }

    private fun showEditReviewDialog(review: Review) {
        viewModel.resetRating()
        showDialogToEditReview(requireActivity(), review, viewModel::saveOrUpdateReview)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == Constants.REQUEST_CODE_NEW_USER) {
            if (resultCode == Activity.RESULT_OK) {
                // A user has been logged in successfully
                // Refresh the poem and all reviews (as the user's review may have been in the preview list)
                viewModel.getPoemAndAllReviews(data?.getFloatExtra(Constants.ACTIVITY_RESPONSE_RATING_KEY, -1f))
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}