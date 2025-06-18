//Nosicelo Ngubane
//ST10483775

package vcmsa.ci.weatherapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.ArrayList

class MainScreen : AppCompatActivity() {
    //EditText and TextView variables to display and enter user response
    private lateinit var txtViewTitle: TextView
    private lateinit var txtAvg: TextView
    private lateinit var txtDay: TextView
    private lateinit var editTxtDay: EditText
    private lateinit var txtMinTemp: TextView
    private lateinit var editTxtMin: EditText
    private lateinit var txtMaxTemp: TextView
    private lateinit var editTxtMax: EditText
    private lateinit var editTxtCondition: EditText
    private lateinit var txtStatus: TextView


    //Button variable to navigate to detail screen
    private lateinit var btnNext: Button
    private lateinit var btnClear: Button
    private lateinit var btnExit: Button
    private lateinit var btnAdd: Button

    //Array to store user input
    private val days = arrayListOf<String>()
    private val minTemps = arrayListOf<Double>()
    private val maxTemps = arrayListOf<Double>()
    private val conditions = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_screen)

        //Text View displays second screen title
        val txtViewTitle = findViewById<TextView>(R.id.txtViewTitle)

        //TextView displays the average temperature for user input
        val txtAvg = findViewById<TextView>(R.id.txtAvg)

        //EditText for user input
        val editTxtDay = findViewById<EditText>(R.id.editTxtDay)

        //Button to navigate to detail screen
        val btnNext = findViewById<Button>(R.id.btnNext)

        //Button to navigate to clear screen
        val btnClear = findViewById<Button>(R.id.btnClear)

        //Display minimum temperature
        val editTextMin = findViewById<TextView>(R.id.editTxtMin)

        //Display maximum temperature
        val editTextMax = findViewById<TextView>(R.id.editTxtMax)

        //Display comment
        val editTextCondition = findViewById<TextView>(R.id.editTxtCondition)

        val status = findViewById<TextView>(R.id.txtStatus)

        val btnAdd = findViewById<Button>(R.id.btnAdd)

        val btnExit = findViewById<Button>(R.id.btnExit)

        //Button to add user input
        btnAdd.setOnClickListener {
            //Get user input
            val day = editTxtDay.text.toString().trim()
            val minTempStr = editTxtMin.text.toString().trim()
            val maxTempStr = editTxtMax.text.toString().trim()
            val condition = editTxtCondition.text.toString().trim()

            //Error handling
            if (day.isBlank() || minTempStr.isBlank() || maxTempStr.isBlank() || condition.isBlank()) {
                txtStatus.text = "Please fill in all fields."
                return@setOnClickListener
            }
            val minTemp = minTempStr.toDoubleOrNull()
            val maxTemp = maxTempStr.toDoubleOrNull()
            if (minTemp == null || maxTemp == null) {
                txtStatus.text = "Please enter valid numbers for minimum and maximum temperatures."
                return@setOnClickListener
            }
            days.add(day)
            minTemps.add(minTemp)
            maxTemps.add(maxTemp)
            conditions.add(condition)

            //Clear all input fields
            editTxtDay.text.clear()
            editTxtMin.text.clear()
            editTxtMax.text.clear()
            editTxtCondition.text.clear()
            txtStatus.text = "Data added successfully. (${days.size}/7)"

            //Calculate and display average temperature for 7 days
            if (days.size == 7) {
                val avgTemp = calculateAvgTemp()
                txtAvg.text = "Average temperature: %1fC".format(avgTemp)
                txtStatus.text = "Here is the week's weather forecast."
            }
        }

        //When user clicks the next button
        btnNext.setOnClickListener {
            if (days.size < 7) {
                txtStatus.text = "Please enter data for all 7 days."
                return@setOnClickListener
            }
            //Create a new screen-switching intent to move to the detail screen
            val intent = Intent(this, DetailScreen::class.java)
            intent.putStringArrayListExtra("days", ArrayList(days))
            intent.putExtra("minTemps", minTemps.toDoubleArray())
            intent.putExtra("maxTemps", maxTemps.toDoubleArray())
            intent.putStringArrayListExtra("conditions", ArrayList(conditions))
            //Start the new screen
            startActivity(intent)
        }

        //When user clicks the clear button
        btnClear.setOnClickListener {
            //Clear the EditText
            days.clear()
            minTemps.clear()
            maxTemps.clear()
            conditions.clear()
            editTxtDay.text.clear()
            editTxtMin.text.clear()
            editTxtMax.text.clear()
            editTxtCondition.text.clear()
            txtAvg.text = ""
            txtStatus.text = "Data cleared."
        }

        //When user clicks the exit button
        btnExit.setOnClickListener {
            //Close the app
            finish()
        }
    }

    private fun calculateAvgTemp(): Double {
        var sum = 0.0
        for (i in 0 until 7) {
            sum += minTemps[i] + maxTemps[i] / 2
        }
        return sum / 7
    }
}

