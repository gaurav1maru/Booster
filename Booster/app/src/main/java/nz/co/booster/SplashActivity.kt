package nz.co.booster

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * Created BY GAURAV MARU on 27,February,2021.
 */

class SplashActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startButtonView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.startButtonView -> {
                val i: Intent = Intent(this, LauncherActivity::class.java)
                startActivity(i)
                finish()
            }
        }
    }

}