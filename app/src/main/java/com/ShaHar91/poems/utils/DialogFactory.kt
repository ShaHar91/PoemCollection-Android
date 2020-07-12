package com.shahar91.poems.utils

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import be.appwise.core.extensions.view.optionalCallbacks
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.shahar91.poems.R
import com.shahar91.poems.data.models.Review
import com.wajahatkarim3.easyvalidation.core.view_ktx.textEqualTo
import kotlinx.android.synthetic.main.custom_dialog_add_review.view.*

class DialogFactory {
    companion object {
        @JvmStatic
        fun showDialogToEditReview(activity: Activity, review: Review,
            saveReviewCallback: (reviewId: String?, reviewText: String, rating: Float) -> Unit) {
            showDialogToAddOrEditReview(activity, review = review, saveReviewCallback = saveReviewCallback)
        }

        @JvmStatic
        fun showDialogToAddReview(activity: Activity, rating: Float,
            saveReviewCallback: (reviewId: String?, reviewText: String, rating: Float) -> Unit) {
            showDialogToAddOrEditReview(activity, rating = rating, saveReviewCallback = saveReviewCallback)
        }

        private fun showDialogToAddOrEditReview(activity: Activity, review: Review? = null, rating: Float? = null,
            saveReviewCallback: (reviewId: String?, reviewText: String, rating: Float) -> Unit) {
            // inflate custom view
            val inflater: LayoutInflater = activity.layoutInflater
            val dialogView: View = inflater.inflate(R.layout.custom_dialog_add_review, null)

            // get views
            val dialogRatingBar = dialogView.rbReviewDialog
            val dialogReviewEditText = dialogView.tilReviewBody.editText

            dialogRatingBar.rbReviewDialog.rating = review?.rating ?: (rating ?: 0f)
            dialogReviewEditText?.setText(review?.text ?: "")

            // instantiate the dialog
            val dialog = MaterialAlertDialogBuilder(activity, R.style.MaterialAlertDialog_Review)
                .setTitle(if (review != null) "Edit a review" else "Write a review")
                .setView(dialogView)
                .setCancelable(false)
                .setPositiveButton("ok", null)
                .setNegativeButton("cancel", null)
                .show()

            // custom validation and handling the Positive Button clicks
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).apply {
                isEnabled = !dialogReviewEditText?.text?.toString().isNullOrBlank()

                setOnClickListener {
                    val newReviewText = dialogReviewEditText?.text?.toString()

                    // No need for to make a call to the backend when both new values are unchanged
                    if (review != null && newReviewText?.textEqualTo(review.text) == true && dialogRatingBar?.rating?.equals(review.rating) == true){
                        dialog.dismiss()
                        return@setOnClickListener
                    }

                    if (!newReviewText.isNullOrBlank()) {
                        saveReviewCallback(review?.id, newReviewText, dialogRatingBar.rating)
                        dialog.dismiss()
                        return@setOnClickListener
                    }
                }
            }

            dialogReviewEditText?.optionalCallbacks { s ->
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = !s.isBlank()
            }
        }
    }
}