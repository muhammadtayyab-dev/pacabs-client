package com.techlogix.pacaps.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.common.api.Status
import com.techlogix.pacaps.R
import com.techlogix.pacaps.fragments.settingsFragments.MyFavoritesFragment
import com.techlogix.pacaps.fragments.settingsFragments.MyProfileFragment
import com.techlogix.pacaps.utility.Utility
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity<T> : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        initViews()
    }

    private fun initViews() {
        backArrowImg.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {

        val fragment =
            (supportFragmentManager.fragments[0] as NavHostFragment).childFragmentManager.fragments.get(
                0)
        if (fragment is MyProfileFragment || fragment is MyFavoritesFragment<*>) {
            setText("Settings")
        }
        super.onBackPressed()
    }

    public fun setText(title: String) {
        paymentTv.text = title
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val fragment =
            (supportFragmentManager.fragments[0] as NavHostFragment).childFragmentManager.fragments.get(
                0)
        if (requestCode == Utility.PLACES_CODE) {
            if (fragment is MyFavoritesFragment<*>) {
                if (data != null && (data.extras?.get("status") as Status).isSuccess) fragment.onActivityResult(
                    requestCode,
                    requestCode,
                    data)
            }
        }
    }
}