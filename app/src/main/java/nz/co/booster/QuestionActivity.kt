package nz.co.booster

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_question.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import nz.co.booster.io.model.QuestionModel
import nz.co.booster.io.model.ScoreModel


/**
 * Created on 28,February,2021.
 */

class QuestionActivity : AppCompatActivity(),
    View.OnClickListener {

    lateinit var questions: ArrayList<QuestionModel>
    private var index: Int = 0
    lateinit var scoreModel: ScoreModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        toolbarView?.let {
            setSupportActionBar(it)
            val actionBar = supportActionBar
            actionBar?.setDisplayHomeAsUpEnabled(true)
            actionBar?.setHomeButtonEnabled(true)
            actionBar?.setDisplayShowHomeEnabled(true)
        }

        questions = intent.getParcelableArrayListExtra("QUESTIONS") ?: arrayListOf()
        index = intent.getIntExtra("INDEX", 0)
        scoreModel = intent.getParcelableExtra("SCORE") ?: ScoreModel()


        questionNameTextView.text = "Question ${(index + 1)}"
        questionTextView.text = questions[index].question ?: ""

        for (answer in questions[index].answer) {
            val radioButton = RadioButton(this)
            radioButton.text = answer.option ?: ""
            radioButton.tag = answer.score
            radioButton.setPadding(0, 16, 0, 16)
            radioGroup.addView(radioButton)
        }

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
                val checkedRadioButtonId = radioGroup.checkedRadioButtonId
                val radioBtn = findViewById<View>(checkedRadioButtonId) as RadioButton

                scoreModel.score += radioBtn.tag.toString().toInt()
                Log.d("ANSWER", " ${radioBtn.text}")
                Log.d("ANSWER SCORE", " ${scoreModel.score}")

                if ((index + 1) < questions.size) {
                    val intent = Intent(this, QuestionActivity::class.java)
                    intent.putParcelableArrayListExtra("QUESTIONS", questions)
                    intent.putExtra("INDEX", (index + 1))
                    intent.putExtra("SCORE", scoreModel)
                    startActivity(intent)
                    finish()
                } else {
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra("SCORE", scoreModel)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}