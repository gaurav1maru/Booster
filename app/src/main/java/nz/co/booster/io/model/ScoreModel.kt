package nz.co.booster.io.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created on 28,February,2021.
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class ScoreModel(
    var score: Int = 0
) : Parcelable