/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.roomwordssample

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

/**
 * Activity for entering a word.
 */

class NewWordActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as Application).repository)
    }

    private lateinit var weight: EditText
    private lateinit var height: EditText
    private lateinit var name: EditText
    private lateinit var female: RadioButton
    private lateinit var male: RadioButton
    private lateinit var gender: TextView
    private lateinit var gainMuscle: CheckBox
    private lateinit var leanAndTone: CheckBox
    private lateinit var endurance: CheckBox
    private lateinit var looseFat: CheckBox
    private lateinit var fitnessGoal: TextView



    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)


        weight = findViewById<EditText>(R.id.edit_word)
        height = findViewById<EditText>(R.id.edit_word2)
        name = findViewById<EditText>(R.id.name)

        val button = findViewById<Button>(R.id.button_save)

        female = findViewById<RadioButton>(R.id.female)
        male = findViewById<RadioButton>(R.id.male)

        gainMuscle = findViewById<CheckBox>(R.id.gainmuscle)
        leanAndTone = findViewById<CheckBox>(R.id.leanandtone)
        endurance = findViewById<CheckBox>(R.id.endurance)
        looseFat = findViewById<CheckBox>(R.id.loosefat)

        gender = findViewById<TextView>(R.id.textView13)
        fitnessGoal = findViewById(R.id.textView3)

        button.setOnClickListener {
            if(CheckAllFields()) {
                val pGender = if (female.isChecked == true) "female" else "male"
                val pWeight = weight.text.toString()
                val pHeight = height.text.toString()
                val pName = name.text.toString()
                val user = User(
                    pName,
                    pWeight.toInt(),
                    pHeight.toInt(),
                    pGender,
                    gainMuscle.isChecked,
                    leanAndTone.isChecked,
                    endurance.isChecked,
                    looseFat.isChecked
                )
                userViewModel.insert(user)
                val intent = Intent(this@NewWordActivity, StartNowActivity::class.java)
                startActivity(intent)
            }
        }
    }
    fun isInteger(value: String): Boolean {
        return try {
            // Attempt to parse the value as an integer
            value.toInt()
            // If parsing succeeds, it's an integer
            true
        } catch (e: NumberFormatException) {
            // Parsing failed, it's not an integer
            false
        }
    }


    fun CheckAllFields() : Boolean
    {
        var qualified = true
        if (!female.isChecked && !male.isChecked) {
            Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show()
            gender.error =""
            qualified = false
        }
        else
        {
            gender.error = null
        }
        if (TextUtils.isEmpty(name.text)) {
            name.setError("This field is required");
            qualified= false
        }
        if (TextUtils.isEmpty(weight.text)) {
            weight.setError("This field is required");
            qualified= false
        }
        if (!isInteger(weight.text.toString())) {
            weight.setError("This field must be a number");
            qualified= false
        }
        if (TextUtils.isEmpty(height.text)) {
            height.setError("This field is required");
            qualified = false
        }
        if (!isInteger(height.text.toString())) {
            height.setError("This field must be a number");
            qualified = false
        }
        if (!leanAndTone.isChecked && !gainMuscle.isChecked && !looseFat.isChecked && !endurance.isChecked) {
            Toast.makeText(this, "Please select your fitness goals", Toast.LENGTH_SHORT).show()
            qualified= false
            fitnessGoal.error=""
        }
        else
        {
            fitnessGoal.error = null
        }
        return  qualified
    }

}
