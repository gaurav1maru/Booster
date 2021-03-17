package nz.co.booster

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.faskn.lib.PieChart
import com.faskn.lib.Slice
import com.faskn.lib.buildChart
import kotlinx.android.synthetic.main.activity_fund_details.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import nz.co.booster.io.model.FundModel


/**
 * Created on 28,February,2021.
 */

class FundDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fund_details)
        toolbarView?.let {
            setSupportActionBar(it)
            val actionBar = supportActionBar
            actionBar?.setDisplayHomeAsUpEnabled(true)
            actionBar?.setHomeButtonEnabled(true)
            actionBar?.setDisplayShowHomeEnabled(true)
        }

        val fundModel: FundModel? = intent.getParcelableExtra("FUND_MODEL")
        fundModel?.let {
            fundNameTextView.text = it.fundName

            val stringBuffer = StringBuffer()
            for (details in it.fundDetails ?: arrayListOf()) {
                stringBuffer.append("* ").append(details).append("\n\n")
            }
            fundDetailsTextView.text = stringBuffer.toString()

            // Kotlin DSL example
            val pieChartDSL = buildChart {
                slices { provideSlices(it) }
                sliceWidth { 80f }
                sliceStartPoint { 0f }
                clickListener { angle, index ->
                    // ...
                }
            }

            chart.setPieChart(pieChartDSL)
            chart.showLegend(legendLayout)

            val pieChart = PieChart(
                slices = provideSlices(it),
                clickListener = null,
                sliceStartPoint = 0f,
                sliceWidth = 80f
            ).build()

            chart.setPieChart(pieChart)
            chart.showLegend(legendLayout)

        }
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

    private fun provideSlices(fundModel: FundModel): ArrayList<Slice> {
        val list = ArrayList<Slice>()
        for (data in fundModel.fundInvestmentMix ?: arrayListOf()) {
            when (data.sectionTitle) {
                "Cash and cash equivalents" -> {
                    list.add(
                        Slice(
                            (data.percentage ?: 0).toFloat(),
                            R.color.primary,
                            data.sectionTitle ?: ""
                        )
                    )
                }
                "International equities" -> {
                    list.add(
                        Slice(
                            (data.percentage ?: 0).toFloat(),
                            R.color.primary_dark,
                            data.sectionTitle ?: ""
                        )
                    )
                }
                "Australasian equities" -> {
                    list.add(
                        Slice(
                            (data.percentage ?: 0).toFloat(),
                            R.color.accent,
                            data.sectionTitle ?: ""
                        )
                    )
                }
                "International fixed interest" -> {
                    list.add(
                        Slice(
                            (data.percentage ?: 0).toFloat(),
                            R.color.purple_700,
                            data.sectionTitle ?: ""
                        )
                    )
                }
                "New Zealand fixed interest" -> {
                    list.add(
                        Slice(
                            (data.percentage ?: 0).toFloat(),
                            R.color.teal_200,
                            data.sectionTitle ?: ""
                        )
                    )
                }
            }

        }
        return list
    }
}