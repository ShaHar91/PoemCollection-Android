package com.shahar91.poems.utils

import android.app.Activity
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import be.appwise.core.extensions.activity.snackBar
import be.appwise.core.extensions.view.optionalCallbacks
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.shahar91.poems.R
import com.shahar91.poems.data.models.Category
import com.shahar91.poems.data.models.Review
import com.wajahatkarim3.easyvalidation.core.view_ktx.textEqualTo
import kotlinx.android.synthetic.main.custom_dialog_add_review.view.*

object DialogFactory {
    @JvmStatic
    fun showDialogOkCancel(activity: Activity, title: String, body: String, ok: () -> Unit, cancel: () -> Unit) {
        MaterialAlertDialogBuilder(activity, R.style.MaterialAlertDialog_Review)
            .setTitle(title)
            .setMessage(body)
            .setPositiveButton(android.R.string.ok) { _, _ -> ok() }
            .setNegativeButton(android.R.string.cancel) { _, _ -> cancel()}
            .show()
    }

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

        // fill in starting values
        dialogRatingBar.rating = review?.rating ?: (rating ?: 0f)
        dialogReviewEditText?.setText(review?.text ?: "")

        // instantiate the dialog
        val dialog = MaterialAlertDialogBuilder(activity, R.style.MaterialAlertDialog_Review)
            .setView(dialogView)
            .setTitle(if (review != null) activity.getString(R.string.dialog_review_title_edit) else activity.getString(
                R.string.dialog_review_title_create))
            .setCancelable(false)
            .setPositiveButton(activity.getString(R.string.common_ok), null)
            .setNegativeButton(activity.getString(R.string.common_cancel), null)
            .show()

        // custom validation and handling the Positive Button clicks
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).apply {
            // starting state for the button
            isEnabled = !dialogReviewEditText?.text?.toString().isNullOrBlank()

            setOnClickListener {
                val newReviewText = dialogReviewEditText?.text?.toString() ?: ""

                // No need for to make a call to the backend when both new values are unchanged
                if (review != null && newReviewText.textEqualTo(review.text) && dialogRatingBar.rating.equals(
                        review.rating)
                ) {
                    dialog.dismiss()
                    return@setOnClickListener
                }

                if (dialogRatingBar.rating < 1) {
                    activity.snackBar("At least a rating of 1 is required")
                    return@setOnClickListener
                }

                saveReviewCallback(review?._id, newReviewText, dialogRatingBar.rating)
                dialog.dismiss()
            }
        }

        dialogRatingBar.setOnRatingChangeListener { _, barRating ->
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = barRating > 0
        }

        dialogReviewEditText?.optionalCallbacks { s ->
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = !s.isBlank()
        }
    }

    @JvmStatic
    fun showDialogToAddCategories(activity: Activity, categories: List<Category>, checkedItems: List<Category>,
        onResult: (resultItems: List<Category>) -> Unit) {
        var resultItems = checkedItems

        // instantiate the dialog
        MaterialAlertDialogBuilder(activity, R.style.MaterialAlertDialog_Review)
            .setTitle("Choose the categories")
            .setMultiChoiceItems(
                categories.map { it.name }.toTypedArray(),
                categories.map { checkedItems.contains(it) }.toTypedArray().toBooleanArray()
            ) { _, which, checked ->
                resultItems = if (checked) {
                    resultItems.plus(categories[which])
                } else {
                    resultItems.minus(categories[which])
                }
            }
            .setPositiveButton(activity.getString(R.string.common_ok)) { dialogInterface, _ ->
                onResult(resultItems)
                dialogInterface.dismiss()
            }
            .setNegativeButton(activity.getString(R.string.common_cancel), null)
            .show()
    }
}