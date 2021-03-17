package nz.co.booster.io.model


import android.annotation.SuppressLint
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class FundInvestmentMixModel(
    var assetType: String? = null,
    var percentage: Int? = null,
    var sectionTitle: String? = null
) : Parcelable