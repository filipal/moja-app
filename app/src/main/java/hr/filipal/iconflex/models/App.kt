package hr.filipal.iconflex.models

import android.graphics.drawable.Drawable

data class App(
    val name: String, // Ime aplikacije
    val packageName: String, // Paket ime aplikacije
    val iconDrawable: Drawable // Ikona aplikacije
)
