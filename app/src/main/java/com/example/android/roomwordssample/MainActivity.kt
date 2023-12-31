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
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe

class MainActivity : AppCompatActivity() {

    private val newWordActivityRequestCode = 1
    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as Application).repository)
    }
    private val favExerciseViewModel: FavExerciseViewModel by viewModels {
        FavExerciseViewModelFactory((application as Application).favExerciseRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        userViewModel.allWords.observe(owner = this) {
            words ->
//            wordViewModel.delete()
        }

        favExerciseViewModel.allExercises.observe(owner = this) {
            if(it.size == 0) {
                resources.openRawResource(R.raw.exercise).bufferedReader()
                    .forEachLine {
                        val temp = it.split(",")
                        favExerciseViewModel.insert(FavExercise(temp[1], temp[2], temp[3], temp[4], temp[5], false))
                    }
            }
        }

        val start = findViewById<Button>(R.id.button)
        start.setOnClickListener {
            val intent = Intent(this@MainActivity, NewUserActivity::class.java)
            startActivity(intent)
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
//        super.onActivityResult(requestCode, resultCode, intentData)
//
//        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
//           intentData?.getParcelableExtra<PersonalInformation>(NewWordActivity.EXTRA_REPLY)?.let { reply ->
//                val word = Word(reply.weight, reply.height)
//                wordViewModel.insert(word)
//            }
//        } else {
//            Toast.makeText(
//                applicationContext,
//                R.string.empty_not_saved,
//                Toast.LENGTH_LONG
//            ).show()
//        }
//    }
}
