package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker=findViewById<Button>(R.id.btnDate)
        fun clickDatePicker(view: View){
            /**
             * This Gets a calendar using the default time zone and locale.
             * The calender returned is based on the current time
             * in the default time zone with the default.
             */
            val c = Calendar.getInstance()
            val year =
                    c.get(Calendar.YEAR) // Returns the value of the given calendar field. This indicates YEAR
            val month = c.get(Calendar.MONTH) // This indicates the Month
            val day = c.get(Calendar.DAY_OF_MONTH) // This indicates the Day

            /**
             * Creates a new date picker dialog for the specified date using the parent
             * context's default date picker dialog theme.
             */
            val dpd = DatePickerDialog(
                    this,
                    DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                        /**
                         * The listener used to indicate the user has finished selecting a date.
                         */

                        /**
                         * The listener used to indicate the user has finished selecting a date.
                         */

                        /**
                         *Here the selected date is set into format i.e : day/Month/Year
                         * And the month is counted in java is 0 to 11 so we need to add +1 so it can be as selected.
                         * */
                        /**
                         *Here the selected date is set into format i.e : day/Month/Year
                         * And the month is counted in java is 0 to 11 so we need to add +1 so it can be as selected.
                         * */
                        val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
                        val tvSelectedDate=findViewById<TextView>(R.id.tvDate)

                        // Selected date it set to the TextView to make it visible to user.
                        tvSelectedDate.setText(selectedDate)

                        /**
                         * Here we have taken an instance of Date Formatter as it will format our
                         * selected date in the format which we pass it as an parameter and Locale.
                         * Here I have passed the format as dd/MM/yyyy.
                         */
                        /**
                         * Here we have taken an instance of Date Formatter as it will format our
                         * selected date in the format which we pass it as an parameter and Locale.
                         * Here I have passed the format as dd/MM/yyyy.
                         */
                        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                        // The formatter will parse the selected date in to Date object
                        // so we can simply get date in to milliseconds.
                        val theDate = sdf.parse(selectedDate)

                        /** Here we have get the time in milliSeconds from Date object
                         * And as we know the formula as milliseconds can be converted to second by dividing it by 1000.
                         * And the seconds can be converted to minutes by dividing it by 60.
                         * So now in the selected date into minutes.
                         */
                        /** Here we have get the time in milliSeconds from Date object
                         * And as we know the formula as milliseconds can be converted to second by dividing it by 1000.
                         * And the seconds can be converted to minutes by dividing it by 60.
                         * So now in the selected date into minutes.
                         */
                        val selectedDateToMinutes = theDate!!.time / 60000

                        // Here we have parsed the current date with the Date Formatter which is used above.
                        val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                        // Current date in to minutes.
                        val currentDateToMinutes = currentDate!!.time / 60000

                        /**
                         * Now to get the difference into minutes.
                         * We will subtract the selectedDateToMinutes from currentDateToMinutes.
                         * Which will provide the difference in minutes.
                         */
                        /**
                         * Now to get the difference into minutes.
                         * We will subtract the selectedDateToMinutes from currentDateToMinutes.
                         * Which will provide the difference in minutes.
                         */
                        val differenceInMinutes = currentDateToMinutes - selectedDateToMinutes
                        val tvSelectedDateInMinutes=findViewById<TextView>(R.id.tvDateMin)
                        // Set the difference in minutes to textview to show the user.
                        tvSelectedDateInMinutes.setText(differenceInMinutes.toString())
                    },
                    year,
                    month,
                    day
            )

            /**
             * Sets the maximal date supported by this in
             * milliseconds since January 1, 1970 00:00:00 in time zone.
             * doesnt allow us to select future dates
             * @param maxDate The maximal supported date.
             */
            // 86400000 is milliseconds of 24 Hours. Which is used to restrict the user to select today and future day.
            dpd.datePicker.setMaxDate(Date().time - 86400000)
            dpd.show()
        }

        btnDatePicker.setOnClickListener{view->
            clickDatePicker(view)
           // Toast.makeText(this,"Button Works", Toast.LENGTH_LONG).show()

        }

    }
}