package com.techlogix.pacaps.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.techlogix.pacaps.R
import kotlinx.android.synthetic.main.activity_payment.*

class PaymentActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        initviews()
    }

    private fun initviews() {
        backArrowImg.setOnClickListener {
            super.onBackPressed()
        }
    }
}