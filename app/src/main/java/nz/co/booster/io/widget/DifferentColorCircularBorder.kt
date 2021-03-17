package nz.co.booster.io.widget

import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.os.Build
import android.view.LayoutInflater
import android.widget.ProgressBar
import android.widget.RelativeLayout
import nz.co.booster.R

/**
 * Created on 26,August,2020.
 */

class DifferentColorCircularBorder(private val parentLayout: RelativeLayout) {
    fun addBorderPortion(context: Context, color: Int, startDegree: Int, endDegree: Int) {
        val portion = getBorderPortion(context, color, startDegree, endDegree)
        parentLayout.addView(portion)
    }

    private fun getBorderPortion(
        context: Context,
        color: Int,
        startDegree: Int,
        endDegree: Int
    ): ProgressBar {
        val inflater = LayoutInflater.from(context)
        val portion =
            inflater.inflate(R.layout.layout_border_portion, parentLayout, false) as ProgressBar
        portion.rotation = startDegree.toFloat()
        portion.progress = endDegree - startDegree
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            portion.progressDrawable.colorFilter = BlendModeColorFilter(
                color,
                BlendMode.SRC_ATOP
            )
        } else {
            portion.progressDrawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
        val params = portion.layoutParams as RelativeLayout.LayoutParams
        params.addRule(RelativeLayout.CENTER_IN_PARENT)
        portion.layoutParams = params
        return portion
    }
}