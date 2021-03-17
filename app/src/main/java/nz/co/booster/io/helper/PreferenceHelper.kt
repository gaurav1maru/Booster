package nz.co.booster.io.helper

import android.content.Context
import android.content.SharedPreferences
import nz.co.booster.io.model.ScoreModel

object PreferenceHelper {

    private const val SHAREDPREFERENCE = "SHAREDPREFERENCE"

    private const val SCORE_MODEL = "SCORE_MODEL"

    private fun staticPreference(context: Context): SharedPreferences =
        context.getSharedPreferences(SHAREDPREFERENCE, Context.MODE_PRIVATE)

    fun persistentPreference(context: Context): SharedPreferences =
        context.getSharedPreferences(SHAREDPREFERENCE, Context.MODE_PRIVATE)

    inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    fun getUserData(context: Context?): ScoreModel? {
        context ?: return null

        val pref: SharedPreferences =
            staticPreference(context)

        return pref[SCORE_MODEL, ScoreModel::class.java] as ScoreModel?
    }

    fun setUserData(context: Context?, userDataModel: ScoreModel?) {
        context ?: return

        val pref: SharedPreferences =
            staticPreference(context)
        pref[SCORE_MODEL] = userDataModel
    }
}