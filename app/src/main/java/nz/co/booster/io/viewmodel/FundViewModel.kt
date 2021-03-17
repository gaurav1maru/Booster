package nz.co.booster.io.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import nz.co.booster.io.model.FundModel
import java.util.ArrayList

/**
 * Created on 28,February,2021.
 */

class FundViewModel : ViewModel() {

    val fundListLiveData: MutableLiveData<List<FundModel>> = MutableLiveData<List<FundModel>>()
    val fundLiveData: MutableLiveData<FundModel> = MutableLiveData<FundModel>()

    fun getFunds(context: Context) {

        val file_name = "investor_types_and_funds.json"
        val countryCodes = context.assets.open(file_name).bufferedReader().use {
            it.readText()
        }
        val gson = Gson()

        val type = object : TypeToken<ArrayList<FundModel>>() {

        }.type
        try {
            fundListLiveData.postValue(gson.fromJson(countryCodes, type))
        } catch (e: Exception) {
            fundListLiveData.postValue(arrayListOf())
        }

    }

    fun getFund(context: Context, name: String) {

        val file_name = "investor_types_and_funds.json"
        val countryCodes = context.assets.open(file_name).bufferedReader().use {
            it.readText()
        }
        val gson = Gson()

        val type = object : TypeToken<ArrayList<FundModel>>() {

        }.type
        try {
            val fundModels: ArrayList<FundModel> = gson.fromJson(countryCodes, type)
            val fundModel = fundModels.firstOrNull { it.name.equals(name) }
            fundLiveData.postValue(fundModel)
        } catch (e: Exception) {
            fundLiveData.postValue(null)
        }

    }

}