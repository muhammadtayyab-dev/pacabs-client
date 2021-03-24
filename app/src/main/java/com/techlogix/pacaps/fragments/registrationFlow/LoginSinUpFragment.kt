package com.techlogix.pacaps.fragments.registrationFlow

import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.navArgs
import androidx.viewpager.widget.ViewPager
import com.example.studentparenttutorapplication.adapters.FragmentsViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.techlogix.pacaps.R
import com.techlogix.pacaps.activities.BaseActivity
import kotlinx.android.synthetic.main.fragment_login_sin_up.*
import java.lang.Exception

class LoginSinUpFragment<T> : Fragment() {
    var baseActivity: BaseActivity? = null
    val args: LoginSinUpFragmentArgs by navArgs()
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_sin_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            initViews(view)
        } catch (e: Exception) {

        }
    }

    private fun initViews(view: View) {
        baseActivity = activity as BaseActivity?
//        for (i in 0..registrationTabLayout.tabCount) {
//            val textView =
//                LayoutInflater.from(context).inflate(R.layout.custom_tv, null) as TextView
//            val typeFace = Typeface.createFromAsset(context?.assets, "fonts/quicksand_regular.otf")
//            textView.setTypeface(typeFace)
//            registrationTabLayout.getTabAt(i)?.setCustomView(textView)
//        }
        registrationTabLayout.setupWithViewPager(registrationViewPager)
        setupViewpage(registrationViewPager)

    }

    private fun setupViewpage(registrationViewPager: ViewPager?) {
        val fragList = arrayListOf<Fragment>()
        val fragTitleList = arrayListOf<String>()
        fragList.add(SignupFragment<T>())
        fragList.add(LoginFragment<T>())
        context?.resources?.getString(R.string.signup)?.let { fragTitleList.add(it) }
        context?.resources?.getString(R.string.login)?.let { fragTitleList.add(it) }

        val pagerAdapter = context?.let {
            FragmentsViewPagerAdapter(it, childFragmentManager, fragList, fragTitleList)
        }
        registrationViewPager?.adapter = pagerAdapter
        if (args.loginOrSignup == 1) registrationViewPager?.setCurrentItem(1, true)
    }

}