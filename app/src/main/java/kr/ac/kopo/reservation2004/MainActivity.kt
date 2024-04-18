package kr.ac.kopo.reservation2004

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.Chronometer
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import android.widget.RadioGroup.VISIBLE
import android.widget.TextView
import android.widget.TimePicker

class MainActivity : AppCompatActivity() {
    lateinit var chrono :Chronometer
    lateinit var btnStart : Button
    lateinit var btnDone : Button
    lateinit var rg : RadioGroup
    lateinit var calender :CalendarView
    lateinit var timePick :TimePicker
    lateinit var textResult : TextView
    var selectedYear : Int = 0
    var selectedMonth : Int = 0
    var selectedDay : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chrono = findViewById<Chronometer>(R.id.chrono)
        btnStart = findViewById<Button>(R.id.btnStart)
        btnDone = findViewById<Button>(R.id.btnDone)
        rg =findViewById<RadioGroup>(R.id.rg)
        calender = findViewById<CalendarView>(R.id.calender)
        timePick = findViewById<TimePicker>(R.id.timePick)
        textResult = findViewById<TextView>(R.id.textResult)

        calender.visibility = View.INVISIBLE
        timePick.visibility = View.INVISIBLE

        rg.setOnCheckedChangeListener(rgListener)
        btnStart.setOnClickListener {
            chrono.base = SystemClock.elapsedRealtime()
            chrono.start()
            chrono.setTextColor(Color.MAGENTA)
        }
        btnDone.setOnClickListener {
            chrono.stop()
            chrono.setTextColor(Color.CYAN)
            textResult.setText("" + selectedYear + "년" +selectedMonth  +"월" +selectedDay +"일")
            textResult.append(""+timePick.currentHour +"시")
            textResult.append(""+timePick.currentMinute +"분")
        }
        calender.setOnDateChangeListener { view, year, month, dayOfMonth ->
            selectedYear = year
            selectedMonth = month
            selectedDay = dayOfMonth
        }
    }
    var rgListener = OnCheckedChangeListener{group, checkedId ->
       calender.visibility = View.INVISIBLE
       timePick.visibility = View.INVISIBLE
        when(rg.checkedRadioButtonId) {
            R.id.rbDate -> calender.visibility = View.VISIBLE
            R.id.rbTime -> timePick.visibility = View.VISIBLE
        }
    }
}