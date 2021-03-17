package nz.co.booster.io

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_launcher.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import nz.co.booster.MainActivity
import nz.co.booster.R

/**
 * Created on 28,February,2021.
 */

class ConfirmationActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        toolbarView?.let {
            setSupportActionBar(it)
            val actionBar = supportActionBar
            actionBar?.setDisplayHomeAsUpEnabled(false)
            actionBar?.setHomeButtonEnabled(false)
            actionBar?.setDisplayShowHomeEnabled(false)
        }

        startButtonView.setOnClickListener(this)
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
            R.id.startButtonView -> {
                val i: Intent = Intent(this, MainActivity::class.java)
                startActivity(i)
                finish()
            }
        }
    }
}