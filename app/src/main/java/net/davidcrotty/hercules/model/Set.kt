package net.davidcrotty.hercules.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by David Crotty on 16/09/2017.
 *
 * Copyright © 2017 David Crotty - All Rights Reserved
 */
class Set(val title: String, val repitions: Int, val timeSeconds: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeInt(repitions)
        parcel.writeInt(timeSeconds)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Set> {
        override fun createFromParcel(parcel: Parcel): Set {
            return Set(parcel)
        }

        override fun newArray(size: Int): Array<Set?> {
            return arrayOfNulls(size)
        }
    }
}