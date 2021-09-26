package com.techlogix.pacaps.fragments.registrationFlow

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.techlogix.pacaps.R
import com.techlogix.pacaps.activities.BaseActivity
import com.techlogix.pacaps.dialogs.ErrorSuccessDialog
import com.techlogix.pacaps.models.GenericResponseModel
import com.techlogix.pacaps.models.ceateUserModel.CreateUserRequestModel
import com.techlogix.pacaps.network.APIManager
import com.techlogix.pacaps.utility.Utility
import kotlinx.android.synthetic.main.fragment_signup.*
import retrofit2.Response

class SignupFragment<T> : Fragment(), APIManager.CallbackGenric<T> {
    var baseActivity: BaseActivity? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val text =
            fromHtml(" I've read and agreed the <b><u> <font size=\"6\"\n" + "          face=\"verdana\"\n" + "          color=\"#051CC4\">Terms and Conditions</font></u><b>")
        termConsCheckbox.setText(text)
        baseActivity = activity as BaseActivity
        getOtpBtn.setOnClickListener {
            if (validateFeilds()) {
                APIManager.getInstance()
                    .createUser(CreateUserRequestModel(fullNameEd.text.toString(),
                        numberEd.text.toString(),
                        emailEd.text.toString(),
                        passwordEd.text.toString(),
                        "12323",2,
                        ""), this)
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
        if (fullNameEd.text.toString().isEmpty()) {
            fullNameEd.setError("Please enter name")
            fullNameEd.requestFocus()
            return false
        } else if (numberEd.text.toString().isEmpty()) {
            numberEd.setError("Please enter phone number")
            numberEd.requestFocus()
            return false
        } else if (emailEd.text.toString().isEmpty()) {
            emailEd.setError("Please enter email address")
            emailEd.requestFocus()
            return false
        } else if (!Utility.emailValidator(emailEd.text.toString())) {
            emailEd.setError("Please enter valid email address")
            emailEd.requestFocus()
            return false
        } else if (passwordEd.text.toString().isEmpty()) {
            passwordEd.setError("Please enter password")
            passwordEd.requestFocus()
            return false
        } else if (passwordEd.text.toString().length < 8) {
            passwordEd.setError("Please enter password lenght greater then 8 digits")
            passwordEd.requestFocus()
            return false
        } else if (!termConsCheckbox.isChecked) {
            baseActivity?.showToast("Please select our terms and conditions")
            return false;
        }

        fullNameEd.setError(null)
        numberEd.setError(null)
        emailEd.setError(null)
        passwordEd.setError(null)
        return true
    }


    private fun fromHtml(source: String): Spanned {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY)
        } else {
            return Html.fromHtml(source)
        }
    }

    override fun onResult(response: GenericResponseModel<T>?, requestCode: Int) {
        if (response!!.status) {
            findNavController().navigate(LoginSinUpFragmentDirections.loginSignupToOTPFragAction())
        }
    }


}