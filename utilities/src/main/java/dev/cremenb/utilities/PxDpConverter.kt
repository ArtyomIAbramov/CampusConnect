package dev.cremenb.utilities

import android.content.Context

object PxDpConverter {

    fun dpToPx(dp: Int, context: Context): Int {
        return (dp * context.resources.displayMetrics.density).toInt()
    }

    fun pxToDp(px: Int, context: Context): Int {
        return (px / context.resources.displayMetrics.density).toInt()
    }
}