package com.techlogix.pacaps.activities

import android.os.Bundle
import com.techlogix.pacaps.R
import kotlinx.android.synthetic.main.activity_service_cap.*

class ServcieProviderAndCapActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_cap)
        backArrowImg.setOnClickListener {
            super.onBackPressed()
        }
    }
}