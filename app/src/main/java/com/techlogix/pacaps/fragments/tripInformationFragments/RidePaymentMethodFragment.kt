package com.techlogix.pacaps.fragments.tripInformationFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import com.techlogix.pacaps.R
import com.techlogix.pacaps.activities.BaseActivity
import com.techlogix.pacaps.models.orderApiModels.GetOrderIdRequestModel
import com.techlogix.pacaps.models.orderApiModels.GetOrderIdResponseModel
import com.techlogix.pacaps.models.orderApiModels.NotesForOrderID
import com.techlogix.pacaps.network.APIManager
import kotlinx.android.synthetic.main.fragment_ride_payment_method.*
import org.json.JSONObject


class RidePaymentMethodFragment : Fragment(), PaymentResultListener, APIManager.RazorPayCallback {
    var baseActivity: BaseActivity? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ride_payment_method, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        baseActivity = requireActivity() as BaseActivity
        Checkout.preload(context)
        paymentMethodToggleGroup.setOnSelectListener {

            if (it.text.trim().equals("Card")) {
                val notes = NotesForOrderID("PACAB-PAYMENT", "PACAB-PAYMENT")
                val requestModel = GetOrderIdRequestModel(100, "INR", "PACAB-RECEIPT", 1, notes)
                APIManager.getInstance().getOrderIdForOnlinePayment(requestModel, this)
            } else {
                if (it.isSelected) {
                    findNavController().navigate(RidePaymentMethodFragmentDirections.gotoPaymentConfirmationFrag())
                }
            }

        }
    }

    private fun gotoOnlinePaymentUsingRazorPay(responseModel: GetOrderIdResponseModel?) {
        val checkout = Checkout()
        checkout.setKeyID(baseActivity?.resources?.getString(R.string.razor_pay))
        try {
            val rootObj = JSONObject()
            rootObj.put("name", baseActivity?.resources?.getString(R.string.app_name))
            rootObj.put("description", "PACAB Ride Fare")
            rootObj.put("send_sms_hash", true)
            rootObj.put("allow_rotation", true)
            rootObj.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            rootObj.put("currency", responseModel?.currency);
            rootObj.put("amount", "" + responseModel?.amount);

            val preFill = JSONObject()
            preFill.put("email", "test@razorpay.com")
            preFill.put("contact", "9876543210")
            rootObj.put("prefill", preFill)
            checkout.open(baseActivity, rootObj)

        } catch (e: Exception) {
            baseActivity?.showToast("Error in Payment : " + e.message)
            e.printStackTrace()
        }
    }

    override fun onPaymentError(code: Int, response: String?) {
        try {
            baseActivity?.showToast("Payment failed: " + code.toString() + " " + response)

        } catch (e: java.lang.Exception) {
            Log.e("paymentException", "Exception in onPaymentError", e)
        }
    }

    override fun onPaymentSuccess(razorpayPaymentID: String?) {
        baseActivity?.showToast("Payment Successful: " + razorpayPaymentID)
    }

    override fun onGetOrderID(responseModel: GetOrderIdResponseModel?) {
        gotoOnlinePaymentUsingRazorPay(responseModel)
    }
}