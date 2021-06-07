package id.co.culturefluent.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InstrumentModel(
    val image : Int,
    val name: String,
    val desc: String,
    val question : String,
    val choices : List<String>,
    val rightChoice : Int
) : Parcelable
