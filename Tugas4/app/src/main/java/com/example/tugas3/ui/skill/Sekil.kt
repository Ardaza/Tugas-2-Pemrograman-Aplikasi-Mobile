package com.example.tugas3.ui.skill

import android.os.Parcel
import android.os.Parcelable

data class Sekil(val titleImage: Int, var heading:String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!
    ){
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(titleImage)
        parcel.writeString(heading)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Sekil> {
        override fun createFromParcel(parcel: Parcel): Sekil {
            return Sekil(parcel)
        }

        override fun newArray(size: Int): Array<Sekil?> {
            return arrayOfNulls(size)
        }
    }
}