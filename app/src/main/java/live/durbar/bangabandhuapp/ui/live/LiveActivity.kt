package live.durbar.bangabandhuapp.ui.live

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import live.durbar.bangabandhuapp.R

class LiveActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live)
    }

    /*private fun getActiveLiveStreaming() {
        viewModel.liveStreaming.observe(this,{data ->
            try {
                if ()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
    }*/


}