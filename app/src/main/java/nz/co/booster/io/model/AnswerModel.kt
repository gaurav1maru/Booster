package nz.co.booster.io.model


import android.annotation.SuppressLint
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class AnswerModel(
    val option: String? = null,
    val score: Int = 0
) : Parcelable