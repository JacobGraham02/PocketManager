package com.jacobdgraham.pocketmanager

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jacobdgraham.pocketmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val extended_floating_action_button = findViewById<ExtendedFloatingActionButton>(R.id.extended_floating_action_button_main)
        val floating_action_button_time_manager = findViewById<FloatingActionButton>(R.id.extended_floating_action_button_time_manager)
        val floating_action_button_budgeter = findViewById<FloatingActionButton>(R.id.extended_floating_action_button_budgeter)
        val floating_action_button_time_manager_text = findViewById<TextView>(R.id.floating_action_button_time_manager_text)
        val floating_action_button_budgeter_text = findViewById<TextView>(R.id.floating_action_button_budgeter_text)

        floating_action_button_time_manager.visibility = View.GONE
        floating_action_button_budgeter.visibility = View.GONE
        floating_action_button_time_manager_text.visibility = View.GONE
        floating_action_button_budgeter_text.visibility = View.GONE

        var floating_action_buttons_and_text_are_visible = false

        extended_floating_action_button.setOnClickListener {
            if (floating_action_buttons_and_text_are_visible) {
                floating_action_button_time_manager.visibility = View.GONE
                floating_action_button_time_manager_text.visibility = View.GONE
                floating_action_button_budgeter.visibility = View.GONE
                floating_action_button_budgeter_text.visibility = View.GONE
                floating_action_buttons_and_text_are_visible = false
            } else {
                floating_action_button_time_manager.visibility = View.VISIBLE
                floating_action_button_time_manager_text.visibility = View.VISIBLE
                floating_action_button_budgeter.visibility = View.VISIBLE
                floating_action_button_budgeter_text.visibility = View.VISIBLE
                floating_action_buttons_and_text_are_visible = true
            }
        }
    }
}