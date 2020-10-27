package kuy.belajar.jetpackpreference

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager

/**
 * Created by Imam Fahrur Rofi on 27/10/2020.
 */
class DarkModeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // KODE DIBAWAH digunakan untuk setting dark mode.
        // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        // buat variabel sharedPreferences
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        // dapatkan nilai Boolean sharedpref dari key dark_mode_setting..
        // jika key dark_mode_setting belum disetting maka nilai standarnya false
        val isDarkMode = sharedPref.getBoolean("dark_mode_setting", false)
        // jika nilai isDarkMode adalah true maka ubah ke tema gelap / mode night yes
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else { // selainnya ubah ke tema terang / mode night np
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }
}