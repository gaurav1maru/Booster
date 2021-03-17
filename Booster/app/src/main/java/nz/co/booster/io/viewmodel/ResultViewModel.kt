package nz.co.booster.io.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import nz.co.booster.io.model.ResultModel
import java.util.*

/**
 * Created on 28,February,2021.
 */

class ResultViewModel : ViewModel() {

    val resultListLiveData: MutableLiveData<ArrayList<ResultModel>> =
        MutableLiveData<ArrayList<ResultModel>>()

    fun getResults(context: Context) {

        val file_name = "investor_profile_result.json"
        val countryCodes = context.assets.open(file_name).bufferedReader().use {
            it.readText()
        }
        val gson = Gson()

        val type = object : TypeToken<ArrayList<ResultModel>>() {

        }.type
        try {
            resultListLiveData.postValue(gson.fromJson(countryCodes, type))
        } catch (e: Exception) {
            resultListLiveData.postValue(arrayListOf())
        }

    }

}