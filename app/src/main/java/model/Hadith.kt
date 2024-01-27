package model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hadith(val title : String , val content : String):Parcelable {
}
