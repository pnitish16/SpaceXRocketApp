package com.spacex.app.util

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.core.content.ContextCompat

object Utils {

    fun applySpanToLabel(label: String, value: String): SpannableString {
        val spannable = SpannableString("$label $value")

        spannable.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            label.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        return spannable
    }

    fun applyColorSpanToLabel(
        context: Context,
        label: String,
        value: String,
        color: Int
    ): SpannableString {
        val spannable = SpannableString("$label $value")

        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(context, color)),
            label.length + 1,
            spannable.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        return spannable
    }


}