package kuy.belajar.jetpackpreference

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }

        // kode untuk menampilkan tombol back di toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // kode untuk mengaktifkan tombol back di toolbar
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    class SettingsFragment : PreferenceFragmentCompat() {

        // buat lateinit var untuk Shared Preferences
        private lateinit var sharedPref: SharedPreferences
        private lateinit var sharedPrefListener: SharedPreferences.OnSharedPreferenceChangeListener

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
            // isi lateinit var sharedPref
            sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
            // isi sharedPrefListener
            sharedPrefListener =
                SharedPreferences.OnSharedPreferenceChangeListener { sharedPref, key ->
                    if (key == "dark_mode_setting") {
                        val isDarkMode = sharedPref.getBoolean(key, false)
                        if (isDarkMode) {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        } else {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        }
                    }
                }
            // tambahkan listener pada sharedPref
            sharedPref.registerOnSharedPreferenceChangeListener(sharedPrefListener)
        }

        override fun onDestroyView() {
            super.onDestroyView()
            // lepas listener pada sharedPref jika setting Activity ditutup
            sharedPref.unregisterOnSharedPreferenceChangeListener(sharedPrefListener)
        }
    }
}