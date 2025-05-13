package vcmsa.ci.weatherapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val welcomeTextView = findViewById<TextView>(R.id.welcomeTextView)
        val txtAppTitle = findViewById<TextView>(R.id.txtAppTitle)
        val nextButton = findViewById<Button>(R.id.nextButton)

        nextButton.setOnClickListener {
            val intent = Intent(this, MainScreen::class.java)
            startActivity(intent)

        }
    }
}