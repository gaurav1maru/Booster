package nz.co.booster.io.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import nz.co.booster.io.model.QuestionModel
import java.util.ArrayList

/**
 * Created on 28,February,2021.
 */

class QuestionViewModel : ViewModel() {

    val questionListLiveData: MutableLiveData<ArrayList<QuestionModel>> =
        MutableLiveData<ArrayList<QuestionModel>>()

    fun getQuestions(context: Context) {

        val file_name = "investor_profile_questions.json"
        val countryCodes = context.assets.open(file_name).bufferedReader().use {
            it.readText()
        }
        val gson = Gson()

        val type = object : TypeToken<ArrayList<QuestionModel>>() {

        }.type
        try {
            questionListLiveData.postValue(gson.fromJson(countryCodes, type))
        } catch (e: Exception) {
            questionListLiveData.postValue(arrayListOf())
        }

    }
}