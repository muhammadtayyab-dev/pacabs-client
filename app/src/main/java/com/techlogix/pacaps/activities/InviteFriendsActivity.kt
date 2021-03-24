package com.techlogix.pacaps.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.techlogix.pacaps.R
import kotlinx.android.synthetic.main.activity_invite_friends.*

class InviteFriendsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invite_friends)
        initViwes()
    }

    private fun initViwes() {
        backArrowImg.setOnClickListener {
            super.onBackPressed()
        }
    }
}