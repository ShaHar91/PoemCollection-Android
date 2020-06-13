package com.shahar91.poems.extensions

import android.app.Activity
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

fun Activity.snackBar(message: String, textColor: Int = android.R.color.white, viewID: Int = android.R.id.content) =
    snackBar(findViewById(viewID), message, textColor).show()

internal fun snackBar(parentView: View, message: String, textColor: Int) =
    Snackbar.make(parentView, message, Snackbar.LENGTH_LONG).apply {
        view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            .setTextColor(ContextCompat.getColor(context, textColor))
    }

fun TextInputLayout.setErrorLayout(error: String?){
    setError(error)
    isErrorEnabled = error != null
}
