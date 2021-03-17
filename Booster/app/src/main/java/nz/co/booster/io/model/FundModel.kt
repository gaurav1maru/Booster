package nz.co.booster.io.model


import android.annotation.SuppressLint
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class FundModel(
    var fundDetails: List<String>? = null,
    var fundInvestmentMix: List<FundInvestmentMixModel>? = null,
    var fundName: String? = null,
    var name: String? = null
) : Parcelable