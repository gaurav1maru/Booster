package nz.co.booster.io.model


import android.annotation.SuppressLint
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class QuestionModel(
    val answer: ArrayList<AnswerModel> = arrayListOf(),
    val question: String? = null
) : Parcelable