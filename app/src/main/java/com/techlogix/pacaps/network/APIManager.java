package com.techlogix.pacaps.network;

import android.app.Dialog;

import com.techlogix.pacaps.PACAP;
import com.techlogix.pacaps.activities.BaseActivity;
import com.techlogix.pacaps.dialogs.PacapDialog;
import com.techlogix.pacaps.enumirations.ErrorDescription;
import com.techlogix.pacaps.models.GenericResponseModel;
import com.techlogix.pacaps.models.ceateUserModel.CreateUserRequestModel;
import com.techlogix.pacaps.models.ceateUserModel.CreateUserResponseModel;
import com.techlogix.pacaps.models.ceateUserModel.VerifyUserOtp;
import com.techlogix.pacaps.models.ceateUserModel.VerifyUserWithMobileAndPasswoadRequest;
import com.techlogix.pacaps.models.favoritesModels.CreateFavLoctionsRequestModel;
import com.techlogix.pacaps.models.favoritesModels.MyFavoritesResponseModel;
import com.techlogix.pacaps.models.orderApiModels.GetOrderIdRequestModel;
import com.techlogix.pacaps.models.orderApiModels.GetOrderIdResponseModel;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class APIManager {
    public static APIManager instance = new APIManager();
    public static Retrofit retrofit, orderRetrofit;

    public GenericResponseModel getResponseModel() {
        return responseModel;
    }

    public GenericResponseModel responseModel;

    public static APIManager getInstance() {

        return instance;
    }

    private APIManager() {
        retrofit = ClientInstance.getRetrofitInstance();
        orderRetrofit = ClientInstance.getRetrofitInstanceForOrderAPI();
    }


    public void createUser(CreateUserRequestModel requestModel, CallbackGenric callback) {
        GetDataService service = retrofit.create(GetDataService.class);
        Call<GenericResponseModel<CreateUserResponseModel>> result = service.createUser(requestModel);
        sendResultGeneric(result, callback, 0);
    }


    public void verifyOtp(VerifyUserOtp requestModel, CallbackGenric callback) {
        GetDataService service = retrofit.create(GetDataService.class);
        Call<GenericResponseModel<CreateUserResponseModel>> result = service.verfiOtp(requestModel);
        sendResultGeneric(result, callback, 0);
    }


    public void verifyWithNumberPassowwrd(VerifyUserWithMobileAndPasswoadRequest requestModel, CallbackGenric callback) {
        GetDataService service = retrofit.create(GetDataService.class);
        Call<GenericResponseModel<CreateUserResponseModel>> result = service.verifyWithNumberPassowwrd(requestModel);
        sendResultGeneric(result, callback, 0);
    }


    public void getOrderIdForOnlinePayment(GetOrderIdRequestModel requestModel, RazorPayCallback callback) {
        GetDataService service = orderRetrofit.create(GetDataService.class);
        Call<GetOrderIdResponseModel> result = service.getOrderId(requestModel);
        sendResultGenericRazorPay(result, callback, 0);
    }


    public void createFavLov(CreateFavLoctionsRequestModel requestModel, CallbackGenric callback) {
        GetDataService service = retrofit.create(GetDataService.class);
        Call<GenericResponseModel<MyFavoritesResponseModel>> result = service.createFavLov(requestModel);
        sendResultGeneric(result, callback, 0);
    }


    public void getAllFavLocs(int userId, CallbackGenric callback) {
        GetDataService service = retrofit.create(GetDataService.class);
        Call<GenericResponseModel<ArrayList<MyFavoritesResponseModel>>> result = service.getAllFavLoc(userId);
        sendResultGeneric(result, callback, 1);
    }

    private <T> void sendResultGeneric(Call<T> call, final CallbackGenric result, int rc) {
        PacapDialog dialog = null;
        if (Objects.requireNonNull(PACAP.Companion.getINSTANCE()).getBaseActivity() != null && !Objects.requireNonNull(PACAP.Companion.getINSTANCE().getBaseActivity()).isFinishing()) {
            dialog = new PacapDialog(PACAP.Companion.getINSTANCE().getBaseActivity());
        }
        if (dialog != null)
            dialog.show();
        final Dialog finalDialog = dialog;
        call.enqueue(new retrofit2.Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (finalDialog != null)
                    finalDialog.dismiss();
                if (response.code() == 200 || response.code() == 201) {
                    GenericResponseModel<T> genericResponseModel = (GenericResponseModel<T>) response.body();
                    if (!genericResponseModel.getStatus()) {
//                        result.onResult(new GenericResponseModel(false, genericResponseModel.getMessage(), null, genericResponseModel.getError()), rc);
                        ((BaseActivity) PACAP.Companion.getINSTANCE().getBaseActivity()).showErrorDialog("Error", genericResponseModel.getMessage() + "\n" +
                                ((genericResponseModel.getError() == null ||
                                        genericResponseModel.getError().isEmpty()) ? "" : genericResponseModel.getError()), genericResponseModel);
                    } else {
                        responseModel = genericResponseModel;
                        result.onResult(genericResponseModel, rc);
                    }
                } else {
                    ((BaseActivity) PACAP.Companion.getINSTANCE().getBaseActivity()).showErrorDialog("Error", response.message(), new GenericResponseModel(false, response.message(), null, response.message()));

                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                if (finalDialog != null)
                    finalDialog.dismiss();
                ErrorDescription errorDescription = ErrorDescription.GENERAL_ERROR;
                if (t instanceof java.net.UnknownHostException || t instanceof java.net.ConnectException) {
                    errorDescription = ErrorDescription.SERVER_RESPONDING;
                } else if (t instanceof JSONException) {
                    errorDescription = ErrorDescription.INVALID_RESPONCE;
                }
                ((BaseActivity) PACAP.Companion.getINSTANCE().getBaseActivity()).showErrorDialog("Error", errorDescription.ed.desc, new GenericResponseModel(false, errorDescription.ed.desc, null, errorDescription.ed.desc));
                t.printStackTrace();
            }
        });
    }

    private <T> void sendResultGenericRazorPay(Call<T> call, final RazorPayCallback result, int rc) {
        PacapDialog dialog = null;
        if (Objects.requireNonNull(PACAP.Companion.getINSTANCE()).getBaseActivity() != null && !Objects.requireNonNull(PACAP.Companion.getINSTANCE().getBaseActivity()).isFinishing()) {
            dialog = new PacapDialog(PACAP.Companion.getINSTANCE().getBaseActivity());
        }
        if (dialog != null)
            dialog.show();
        final Dialog finalDialog = dialog;
        call.enqueue(new retrofit2.Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (finalDialog != null)
                    finalDialog.dismiss();
                if (response.code() == 200 || response.code() == 201) {
                    result.onGetOrderID((GetOrderIdResponseModel) response.body());
                } else {
                    ((BaseActivity) PACAP.Companion.getINSTANCE().getBaseActivity()).showErrorDialog("Error", response.message(), new GenericResponseModel(false, response.message(), null, response.message()));

                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                if (finalDialog != null)
                    finalDialog.dismiss();
                ErrorDescription errorDescription = ErrorDescription.GENERAL_ERROR;
                if (t instanceof java.net.UnknownHostException || t instanceof java.net.ConnectException) {
                    errorDescription = ErrorDescription.SERVER_RESPONDING;
                } else if (t instanceof JSONException) {
                    errorDescription = ErrorDescription.INVALID_RESPONCE;
                }
                ((BaseActivity) PACAP.Companion.getINSTANCE().getBaseActivity()).showErrorDialog("Error", errorDescription.ed.desc, new GenericResponseModel(false, errorDescription.ed.desc, null, errorDescription.ed.desc));
                t.printStackTrace();
            }
        });
    }


    public interface Callback {
        void onResult(boolean z, String response);
    }

    public interface CallbackGenric<T> {
        void onResult(GenericResponseModel<T> response, int requestCode);

//        void onError(String error, int requestCode);
    }

    public interface RazorPayCallback {
        void onGetOrderID(GetOrderIdResponseModel responseModel);
    }
}