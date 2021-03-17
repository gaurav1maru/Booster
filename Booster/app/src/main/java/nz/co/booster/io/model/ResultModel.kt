package nz.co.booster.io.model


import android.annotation.SuppressLint
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class ResultModel(
    var boosterKiwisaverSchemeFund: List<String>? = null,
    var end: Int = 0,
    var type: String? = null,
    var productDisclosureStatement: List<String>? = null,
    var start: Int = 0
) : Parcelable