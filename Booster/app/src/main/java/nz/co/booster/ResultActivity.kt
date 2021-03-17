package nz.co.booster

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_result.*
import nz.co.booster.io.helper.PreferenceHelper
import nz.co.booster.io.model.ResultModel
import nz.co.booster.io.model.ScoreModel
import nz.co.booster.io.viewmodel.FundViewModel
import nz.co.booster.io.viewmodel.ResultViewModel

/**
 * Created on 28,February,2021.
 */

class ResultActivity : AppCompatActivity(),
    View.OnClickListener {

    private var resultModel: ResultModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val scoreModel = intent.getParcelableExtra("SCORE") ?: ScoreModel()
        PreferenceHelper.setUserData(this, scoreModel)

        scoreTextView.text = "${scoreModel.score}"

        val resultViewModel: ResultViewModel =
            ViewModelProvider.NewInstanceFactory().create(ResultViewModel::class.java)
        resultViewModel.getResults(this)
        resultViewModel.resultListLiveData.observe(this, Observer {
            resultModel = it.firstOrNull { resultModels ->
                resultModels.start <= scoreModel.score && resultModels.end >= scoreModel.score
            }
            resultModel?.let { it1 ->
                scoreDescriptionTextView.text = "You are a ${it1.type} investor"
            }
        })
        nextButtonView.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.nextButtonView -> {
                val fundViewModel: FundViewModel =
                    ViewModelProvider.NewInstanceFactory().create(FundViewModel::class.java)
                fundViewModel.getFund(this, resultModel?.type ?: "")

                fundViewModel.fundLiveData.observe(this, Observer {
                    val intent = Intent(this, FundDetailsActivity::class.java)
                    intent.putExtra("FUND_MODEL", it)
                    startActivity(intent)
                    finish()
                })
            }
        }
    }
}