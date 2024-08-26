package com.example.bbobabboba.custom

import android.os.Parcel
import android.os.Parcelable


data class CustomData(var inputText: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(inputText)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<CustomData> {
        override fun createFromParcel(parcel: Parcel): CustomData {
            return CustomData(parcel)
        }

        override fun newArray(size: Int): Array<CustomData?> {
            return arrayOfNulls(size)
        }
    }
}
