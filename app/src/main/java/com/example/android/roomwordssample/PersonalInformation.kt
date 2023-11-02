package com.example.android.roomwordssample
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class PersonalInformation (val weight: String, val height: Int) : Parcelable
{

}