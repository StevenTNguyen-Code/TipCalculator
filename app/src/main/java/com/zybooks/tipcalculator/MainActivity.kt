package com.zybooks.tipcalculator

import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

private const val KEY_TOTAL_PIZZAS = "totalPizzas"

class MainActivity : AppCompatActivity() {

    private lateinit var numAttendEditText: EditText
    private lateinit var numPizzasTextView: TextView
    private lateinit var howHungryRadioGroup: RadioGroup
    private var totalPizzas = 0.0



    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numAttendEditText = findViewById(R.id.num_attend_edit_text)
        numPizzasTextView = findViewById(R.id.num_pizzas_text_view)
        howHungryRadioGroup = findViewById(R.id.hungry_radio_group)

        //This is to display the AlertDialog.
        val dialog = WarningDialogFragment()
        dialog.show(supportFragmentManager, "warningDialog")

        //This is the contextMenu for the Random Button
        val listView1 = findViewById<RadioButton>(R.id.custom_radio_button)
        registerForContextMenu(listView1)

        //This is the contextMenu for the 20% Button
        val listView2 = findViewById<RadioButton>(R.id.ravenous_radio_button)
        registerForContextMenu(listView2)

        //This is the contextMenu for the 15% Button
        val listView3 = findViewById<RadioButton>(R.id.medium_radio_button)
        registerForContextMenu(listView3)

        //This is the contextMenu for the 10% Button
        val listView4 = findViewById<RadioButton>(R.id.light_radio_button)
        registerForContextMenu(listView4)
        


        // Restore state
        if (savedInstanceState != null) {
            totalPizzas = savedInstanceState.getDouble(KEY_TOTAL_PIZZAS)
            displayTotal()
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?,
                                     v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.low_tip -> {
                val dialog = LowButtonWarningDialogFragment()
                dialog.show(supportFragmentManager, "warningDialog")
                true
            }
            R.id.medium_tip -> {
                val dialog = MediumButtonWarningDialogFragment()
                dialog.show(supportFragmentManager, "warningDialog")
                true
            }
            R.id.high_tip -> {
                val dialog = HighButtonWarningDialogFragment()
                dialog.show(supportFragmentManager, "warningDialog")
                true
            }
            R.id.random_tip -> {
                val dialog = RandomButtonWarningDialogFragment()
                dialog.show(supportFragmentManager, "warningDialog")
                true
            }

            else -> super.onContextItemSelected(item)
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putDouble(KEY_TOTAL_PIZZAS, totalPizzas)
    }

    fun calculateClick(view: View) {

        // Get the text that was typed into the EditText
        val numAttendStr = numAttendEditText.text.toString()

        // Convert the text into an integer
        val numAttend = numAttendStr.toDoubleOrNull() ?: 0.0

        // Get hunger level selection
        val hungerLevel = when (howHungryRadioGroup.getCheckedRadioButtonId()) {
            R.id.light_radio_button -> TipCalculator.HungerLevel.LIGHT
            R.id.medium_radio_button -> TipCalculator.HungerLevel.MEDIUM
            R.id.ravenous_radio_button -> TipCalculator.HungerLevel.RAVENOUS
            else -> TipCalculator.HungerLevel.RANDOM
        }

        // Get the number of pizzas needed
        val calc = TipCalculator(numAttend, hungerLevel)
        totalPizzas = calc.totalPizzas
        displayTotal()
    }

    private fun displayTotal() {
        val totalText = getString(R.string.total_bill_cost, totalPizzas)
        numPizzasTextView.text = totalText
    }
}