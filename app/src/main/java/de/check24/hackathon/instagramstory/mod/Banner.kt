package de.check24.hackathon.instagramstory.mod

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Banner(
    val type: BannerType,
    val text: String,
    val x: Int = 50,
    val y: Int = 50,
    val rotation: Int = 0,
    val background: String? = null,
    val textSize: Float = 1f,
    val textColor: String = "FFFFFFFF",
    val cornerRadius: Int = 30,
    val imageUrl: String? = null,
    val width: Int? = null,
    val height: Int? = null,
    val actionDeeplink: String? = null,

) : Parcelable {
    override fun hashCode(): Int {
        return x * y * rotation
    }
}
enum class BannerType {
    TEXT,
    IMAGE,
}
