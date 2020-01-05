package honey.mvpexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , HomeView{

    private lateinit var homepresent : HomePresenterImplp

    override fun clickedOnViewMore() {
        val intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        homepresent = HomePresenterImplp()

        button_click.setOnClickListener{
            homepresent.setView(this)
        }
    }
}
