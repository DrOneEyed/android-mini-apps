package nv.dr_one.dobcal

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var dispSelcDate : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inpButton : Button = findViewById(R.id.inpButton)
        dispSelcDate = findViewById(R.id.dateOut)

        inpButton.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            {view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this,
                    "Selected Date: $selectedDayOfMonth/$selectedMonth/$selectedYear", Toast.LENGTH_SHORT).show()

                val selectedDate = "$selectedDayOfMonth/$selectedMonth/$selectedYear"

                dispSelcDate?.text = selectedDate
                dispSelcDate?.isVisible = true
                val dispSelc : TextView = findViewById(R.id.selectedDate)
                dispSelc.isVisible = true

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val birthDate = sdf.parse(selectedDate)
                birthDate?.let {
                    val selectedDateInMin = birthDate.time / 60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMin = currentDate.time / 60000
                        val diff = currentDateInMin - selectedDateInMin

                        val dispMin: TextView = findViewById(R.id.dateMinOut)
                        dispMin.text = diff.toString()
                        dispMin.isVisible = true
                        val dispOut: TextView = findViewById(R.id.minOld)
                        dispOut.isVisible = true
                    }
                }

            }, year, month, day
        )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}