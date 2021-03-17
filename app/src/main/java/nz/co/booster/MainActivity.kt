package nz.co.booster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import nz.co.booster.adapter.FundAdapter
import nz.co.booster.io.helper.PreferenceHelper
import nz.co.booster.io.model.FundModel
import nz.co.booster.io.model.ScoreModel
import nz.co.booster.io.viewmodel.FundViewModel
import nz.co.booster.io.viewmodel.QuestionViewModel

/**
 * Created on 27,February,2021.
 */

class MainActivity : AppCompatActivity(),
    View.OnClickListener,
    FundAdapter.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbarView?.let {
            setSupportActionBar(it)
            val actionBar = supportActionBar
            actionBar?.setDisplayHomeAsUpEnabled(true)
            actionBar?.setHomeButtonEnabled(true)
            actionBar?.setDisplayShowHomeEnabled(true)
            titleView.text = getString(R.string.menu)
        }

        questionnaireButton.setOnClickListener(this)
        submitButton.setOnClickListener(this)

        recyclerView.setHasFixedSize(true)

        val mLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = mLayoutManager

        getData()
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
            R.id.questionnaireButton -> {
                val questionViewModel: QuestionViewModel =
                    ViewModelProvider.NewInstanceFactory().create(QuestionViewModel::class.java)
                questionViewModel.getQuestions(this)
                questionViewModel.questionListLiveData.observe(this, Observer {
                    val intent = Intent(this, QuestionActivity::class.java)
                    intent.putParcelableArrayListExtra("QUESTIONS", it)
                    intent.putExtra("INDEX", 0)
                    intent.putExtra("SCORE", ScoreModel())
                    startActivity(intent)
                })
            }
            R.id.submitButton -> {
                val intent = Intent(this, SubmitActivity::class.java)
                intent.putExtra("SCORE", ScoreModel())
                startActivity(intent)
            }
        }
    }

    private fun getData() {
        val fundViewModel: FundViewModel =
            ViewModelProvider.NewInstanceFactory().create(FundViewModel::class.java)
        fundViewModel.getFunds(this)
        fundViewModel.fundListLiveData.observe(this, Observer {
            val adapter = FundAdapter(
                this,
                it, this
            )
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        })
    }

    override fun onItemClick(fundModel: FundModel) {
        val intent = Intent(this, FundDetailsActivity::class.java)
        intent.putExtra("FUND_MODEL", fundModel)
        startActivity(intent)
        finish()
    }
}