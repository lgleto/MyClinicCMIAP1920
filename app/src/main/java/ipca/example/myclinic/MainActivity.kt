package ipca.example.myclinic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        VolleyHelper.instance.getAllMedicos(this) { response ->
            response?.let {
                Log.d("MainActivity", it.toString())
            }
        }
    }

}
