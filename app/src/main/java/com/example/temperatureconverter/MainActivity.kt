package com.example.temperatureconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var celsiusSeekBar: SeekBar
    private lateinit var fahrenheitSeekBar: SeekBar
    private lateinit var importantMessage: TextView
    private lateinit var celsiusValueTextView: TextView
    private lateinit var fahrenheitValueTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        celsiusSeekBar = findViewById(R.id.celsiusSeekBar)
        fahrenheitSeekBar = findViewById(R.id.fahrenheitSeekBar)
        importantMessage = findViewById(R.id.importantMessage)
        celsiusValueTextView = findViewById(R.id.celsiusValueTextView)
        fahrenheitValueTextView = findViewById(R.id.fahrenheitValueTextView)

        celsiusSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val celsius = progress
                val fahrenheit = celsiusToFahrenheit(progress)
                fahrenheitSeekBar.progress = fahrenheit
                celsiusValueTextView.text = "$celsius °C"
                updateMessage(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        fahrenheitSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val fahrenheit = if (progress < 32) 32 else progress//used ChatGPT to get the coerce function

                val celsius = fahrenheitToCelsius(fahrenheit)
                celsiusSeekBar.progress = celsius
                fahrenheitValueTextView.text = "$fahrenheit °F"
                updateMessage(celsius)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar?.progress ?: 0 < 32) { // Safe call (?.) and Elvis operator (?:)
                    seekBar?.progress = 32 // Safe call (?.)
                }
            }
        })
    }
    private fun celsiusToFahrenheit(celsius: Int): Int{
        return (celsius * 9/5) + 32
    }
    private fun fahrenheitToCelsius(fahrenheit: Int): Int{
        return ((fahrenheit-32) * 5) / 9
    }
    private fun updateMessage(celsius: Int){
        val message = if (celsius <= 20) {
            "I wish it were warmer."
        } else{
            "I wish it were colder."
        }
        importantMessage.text = message
    }
}