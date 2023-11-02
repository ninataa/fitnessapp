package com.example.android.roomwordssample

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Exercise (val exercise_name: String
                , val description: String, var set: String
                , val filter: String, val group: String
                , var added: Boolean): Parcelable {

}