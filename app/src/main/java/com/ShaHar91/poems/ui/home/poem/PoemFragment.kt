package com.shahar91.poems.ui.home.poem

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import be.appwise.core.extensions.logging.loge
import be.appwise.core.extensions.view.setupRecyclerView
import be.appwise.core.networking.Networking.isLoggedIn
import com.shahar91.poems.Constants
import com.shahar91.poems.R
import com.shahar91.poems.data.models.Review
import com.shahar91.poems.ui.base.PoemBaseFragment
import com.shahar91.poems.ui.entry.EntryActivity.Companion.startWithIntent
import com.shahar91.poems.ui.home.poem.adapter.PoemDetailAdapterController
import com.shahar91.poems.utils.DialogFactory.showDialogToAddReview
import com.shahar91.poems.utils.DialogFactory.showDialogToEditReview
import kotlinx.android.synthetic.main.fragment_poem.*

class PoemFragment : PoemBaseFragment<PoemViewModel>() {
    private lateinit var controller: PoemDetailAdapterController
    private val safeArgs: PoemFragmentArgs by navArgs()
    private val poemDetailAdapterControllerListener: PoemDetailAdapterController.Listener = object :
        PoemDetailAdapterController.Listener {
        override fun onRatingBarTouched(rating: Float) {
            if (isLoggedIn()) {
                showAddReviewDialog(rating)
            } else {
                // start the EntryActivity to make sure the user gets logged in
                startActivityForResult(startWithIntent(requireContext(), rating),
                    Constants.REQUEST_CODE_NEW_USER)
            }
        }

        override fun onEditReviewClicked(review: Review) {
            showDialogToEditReview(requireActivity(), review
            ) { reviewId: String?, newReviewText: String?, newRating: Float ->
                viewModel.saveOrUpdateReview(reviewId, newReviewText, newRating)
            }
        }

        override fun onDeleteReviewClicked(reviewId: String) {
            viewModel.deleteReview(reviewId)
        }
    }

    private val poemViewModelListener: PoemViewModel.ViewModelCallbacks = object :
        PoemViewModel.ViewModelCallbacks {
        override fun refreshLayout() {
            showPoem()
        }

        override fun error(throwable: Throwable) {
            throwable.printStackTrace()
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
    }

    private fun initViews() {
        controller = PoemDetailAdapterController(requireContext(), poemDetailAdapterControllerListener)
        rvPoemDetails.setupRecyclerView(null, LinearLayoutManager(requireContext()))
        rvPoemDetails.adapter = controller.adapter
    }

    private fun showPoem() {
        if (viewModel.poem != null) {
            controller.setData(viewModel.poem, viewModel.ownReview)
        }

        if (viewModel.delayedRating != null) {
            showAddReviewDialog(viewModel.delayedRating ?: 0F)
        }
    }

    private fun showAddReviewDialog(rating: Float) {
        viewModel.resetRating()
        showDialogToAddReview(requireActivity(), rating
        ) { reviewId: String?, newReviewText: String?, newRating: Float ->
            viewModel.saveOrUpdateReview(reviewId, newReviewText, newRating)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPoemAndAllReviews()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == Constants.REQUEST_CODE_NEW_USER) {
            if (resultCode == Activity.RESULT_OK) {
                // A user has been logged in successfully
                // Refresh the poem and all reviews (as the user's review may have been in the preview list)
                viewModel.getPoemAndAllReviews(data?.getFloatExtra("rating", -1f))
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}