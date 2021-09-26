package com.techlogix.pacaps.fragments.registrationFlow

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.techlogix.pacaps.R
import com.techlogix.pacaps.activities.BaseActivity
import com.techlogix.pacaps.activities.DashboardActivity
import com.techlogix.pacaps.models.GenericResponseModel
import com.techlogix.pacaps.models.ceateUserModel.CreateUserResponseModel
import com.techlogix.pacaps.models.ceateUserModel.VerifyUserWithMobileAndPasswoadRequest
import com.techlogix.pacaps.network.APIManager
import com.techlogix.pacaps.utility.SharePrefData
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.keyImg
import kotlinx.android.synthetic.main.fragment_login.numberEd
import kotlinx.android.synthetic.main.fragment_login.passwordEd
import kotlinx.android.synthetic.main.fragment_signup.*

class LoginFragment<T> : Fragment(), APIManager.CallbackGenric<T> {

    var baseActivity: BaseActivity? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        baseActivity = activity as BaseActivity?
        loginBtn.setOnClickListener {
            if (validateFeilds()) {
                APIManager.getInstance()
                    .verifyWithNumberPassowwrd(VerifyUserWithMobileAndPasswoadRequest(numberEd.text.toString(),
                        passwordEd.text.toString()), this)
            }
        }
        keyImg.setOnClickListener {
            if (passwordEd.transformationMethod.equals(PasswordTransformationMethod.getInstance())) {
                passwordEd.transformationMethod = HideReturnsTransformationMethod.getInstance()
            }else{
                passwordEd.transformationMethod= PasswordTransformationMethod.getInstance()
            }
            passwordEd.setSelection(passwordEd.text.toString().length)
        }
    }

    private fun validateFeilds(): Boolean {
        if (numberEd.text.toString().isEmpty()) {
            numberEd.setError("Please enter mobile number")
            numberEd.requestFocus()
            return false
        } else if (passwordEd.text.toString().isEmpty()) {
            passwordEd.setError("Please enter password")
            passwordEd.requestFocus()
            return false
        } else {
            numberEd.setError(null)
            passwordEd.setError(null)
            return true
        }
    }

    override fun onResult(response: GenericResponseModel<T>?, requestCode: Int) {
        if (response?.result is CreateUserResponseModel) {
            SharePrefData.getInstance().userId = (response.result as CreateUserResponseModel).id
            SharePrefData.getInstance().userNum =
                (response.result as CreateUserResponseModel).mobile
            SharePrefData.getInstance().userName = (response.result as CreateUserResponseModel).name
            SharePrefData.getInstance().isLoggedIn=true
            baseActivity?.openActivity(DashboardActivity::class.java, null)
            requireActivity().finish()
        }

    }

}