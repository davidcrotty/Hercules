package net.davidcrotty.hercules.model

import android.os.Parcel
import android.os.Parcelable
import net.davidcrotty.progressview.SetState

/**
 * Created by David Crotty on 16/09/2017.
 *
 * Copyright © 2017 David Crotty - All Rights Reserved
 */
class Set(val title: String, val repitions: Int, val timeSeconds: Int, val state: SetState) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readSerializable() as SetState) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeInt(repitions)
        parcel.writeInt(timeSeconds)
        parcel.writeSerializable(state)
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