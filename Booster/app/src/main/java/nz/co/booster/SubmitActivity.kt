package nz.co.booster

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_submit.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import nz.co.booster.io.helper.PreferenceHelper
import java.lang.Exception

/**
 * Created on 28,February,2021.
 */

class SubmitActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)
        toolbarView?.let {
            setSupportActionBar(it)
            val actionBar = supportActionBar
            actionBar?.setDisplayHomeAsUpEnabled(false)
            actionBar?.setHomeButtonEnabled(false)
            actionBar?.setDisplayShowHomeEnabled(false)
        }

        submitButtonView.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.action_main -> {
                val i: Intent = Intent(this, MainActivity::class.java)
                startActivity(i)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.submitButtonView -> {
                val scoreModel = PreferenceHelper.getUserData(this)
                val emailIntent = Intent(android.content.Intent.ACTION_SEND)
                emailIntent.type = "text/plain"
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Your Booster Score")
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, arrayOf("james.story@booster.co.nz"))
                emailIntent.putExtra(
                    android.content.Intent.EXTRA_TEXT,
                    "Name - ${
                        nameTextView.text.toString().trim()
                    } \n Email - ${
                        emailTextView.text.toString().trim()
                    } \n Phone - ${mobileTextView.text.toString().trim()} \n" +
                            " Score - ${scoreModel?.score ?: 0} \n Thank you."
                )

                try {
                    startActivity(
                        Intent.createChooser(
                            emailIntent,
                            "Send email using..."
                        )
                    );
                } catch (ex: Exception) {
                }
            }
        }
    }
}