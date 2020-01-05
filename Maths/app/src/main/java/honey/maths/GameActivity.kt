package honey.maths

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_game)
        image6.setOnClickListener(this)
        image5.setOnClickListener(this)
        image4.setOnClickListener(this)
        image3.setOnClickListener(this)
        image7.setOnClickListener(this)
        image8.setOnClickListener(this)
        answer2.setOnClickListener(this)
        answer1.setOnClickListener(this)
        answer3.setOnClickListener(this)
        answer4.setOnClickListener(this)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.image6 -> loadAnim(image6)
            R.id.image5 -> loadAnim(image5)
            R.id.image4 -> loadAnim(image4)
            R.id.image3 -> loadAnim(image3)
            // second line
            R.id.image7 -> {
                loadAnim(image7)
                view1.visibility = View.VISIBLE
                view2.visibility = View.VISIBLE
            }
            R.id.image8 -> loadAnim(image8)
            R.id.answer2 -> answer2.setBackgroundResource(R.drawable.green_bg)
            R.id.answer1 -> vibrate(answer1)
            R.id.answer3 -> vibrate(answer3)
            R.id.answer4 -> vibrate(answer4)

        }
    }

    private fun vibrate(view: TextView) {
        view.setBackgroundResource(R.drawable.red_bg)
        val anim = AnimationUtils.loadAnimation(applicationContext, R.anim.vibrate_anim)
        view.startAnimation(anim)
    }

    private fun loadAnim(view: ImageView) {
        val anim = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_right)
        view.startAnimation(anim)
    }
}