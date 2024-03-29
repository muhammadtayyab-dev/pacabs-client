package com.techlogix.pacaps.network;

import android.app.Dialog;

import com.techlogix.hirecabs.models.bookingsModels.TempBookingRequestModel;
import com.techlogix.hirecabs.models.bookingsModels.TempBookingResponseModel;
import com.techlogix.pacaps.PACAP;
import com.techlogix.pacaps.activities.BaseActivity;
import com.techlogix.pacaps.dialogs.PacapDialog;
import com.techlogix.pacaps.enumirations.ErrorDescription;
import com.techlogix.pacaps.models.GenericResponseModel;
import com.techlogix.pacaps.models.NearestVehiclesModels.GetNearAvailableVehiclesRequestModel;
import com.techlogix.pacaps.models.NearestVehiclesModels.GetNearestAvailbleVehiclesResponseModel;
import com.techlogix.pacaps.models.cabAndDriverInformationModels.CabsAndTheirFareResponseModel;
import com.techlogix.pacaps.models.ceateUserModel.CreateUserRequestModel;
import com.techlogix.pacaps.models.ceateUserModel.CreateUserResponseModel;
import com.techlogix.pacaps.models.ceateUserModel.VerifyUserOtp;
import com.techlogix.pacaps.models.ceateUserModel.VerifyUserWithMobileAndPasswoadRequest;
import com.techlogix.pacaps.models.cityModel.GetCityFromLatLongResponseModel;
import com.techlogix.pacaps.models.drviersModels.GetAllDriversResponseModel;
import com.techlogix.pacaps.models.favoritesModels.CreateFavLoctionsRequestModel;
import com.techlogix.pacaps.models.favoritesModels.DeleteMyFavLocRequestModel;
import com.techlogix.pacaps.models.favoritesModels.MyFavoritesResponseModel;
import com.techlogix.pacaps.models.orderApiModels.GetOrderIdRequestModel;
import com.techlogix.pacaps.models.orderApiModels.GetOrderIdResponseModel;
import com.techlogix.pacaps.models.settingsModels.GetSettingsResponseModels;
import com.techlogix.pacaps.models.tripsModels.TripEstimateRequestModel;
import com.techlogix.pacaps.models.tripsModels.TripEstimationsResponseModel;
import com.techlogix.pacaps.utility.Utility;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;


public class APIManager {
    public static APIManager instance = new APIManager();
    public static Retrofit retrofit, orderRetrofit;
    public static boolean showDialog=true;
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


    public void deleteMyFavLov(int favID, DeleteMyFavLocRequestModel requestModel, CallbackGenric callback) {
        GetDataService service = retrofit.create(GetDataService.class);
        Call<GenericResponseModel> result = service.deleteMyFavLov(favID,requestModel);
        sendResultGeneric(result, callback, 2);
    }


    public void getDefaultSettings(CallbackGenric callback) {
        GetDataService service = retrofit.create(GetDataService.class);
        Call<GenericResponseModel<ArrayList<GetSettingsResponseModels>>> result = service.getDefaultSettings();
        sendResultGeneric(result, callback, Utility.Companion.getSETTINGS());
    }


    public void getTripPricForVehical(CallbackGenric callback, TripEstimateRequestModel requestModel) {
        GetDataService service = retrofit.create(GetDataService.class);
        Call<GenericResponseModel<TripEstimationsResponseModel>> result = service.getTripPricForVehical(requestModel);
        sendResultGeneric(result, callback, Utility.Companion.getEST_PRICE_FOR_VEHICLE());
    }


    public void getCityByLatLong(CallbackGenric callback, double userLat,double userLong) {
        GetDataService service = retrofit.create(GetDataService.class);
        Call<GenericResponseModel<GetCityFromLatLongResponseModel>> result = service.getCityByLatLong(userLat,userLong);
        sendResultGeneric(result, callback, Utility.Companion.getGET_CITIES());
    }


    public void getNearestAvailableVehicles(CallbackGenric callback, GetNearAvailableVehiclesRequestModel requestModel) {
        GetDataService service = retrofit.create(GetDataService.class);
        Call<GenericResponseModel<ArrayList<GetNearestAvailbleVehiclesResponseModel>>> result = service.getNearestAvailableVehicles(requestModel);
        sendResultGeneric(result, callback, Utility.Companion.getGET_VEHICLES());
    }

    public void getCabsAndTheirFare(CallbackGenric callback, String cityName, String tripType) {
        GetDataService service = retrofit.create(GetDataService.class);
        Call<GenericResponseModel<ArrayList<CabsAndTheirFareResponseModel>>> result = service.getCabsAndTheirFare(cityName, tripType);
        sendResultGeneric(result, callback, Utility.Companion.getSHOW_AVAILABLE_CAB());
    }
    public void tempBooking(CallbackGenric callback, TempBookingRequestModel requestModel) {
        GetDataService service = retrofit.create(GetDataService.class);
        Call<GenericResponseModel<TempBookingResponseModel>> result = service.tempBooking(requestModel);
        sendResultGeneric(result, callback, Utility.Companion.getTEMPBOOKING());
    }
    private <T> void sendResultGeneric(Call<T> call, final CallbackGenric result, int rc) {
        PacapDialog dialog = null;
        if (Objects.requireNonNull(PACAP.Companion.getINSTANCE()).getBaseActivity() != null && !Objects.requireNonNull(PACAP.Companion.getINSTANCE().getBaseActivity()).isFinishing()) {
            dialog = new PacapDialog(PACAP.Companion.getINSTANCE().getBaseActivity());
        }
        if (dialog != null && showDialog)
            dialog.show();
        final Dialog finalDialog = dialog;
        call.enqueue(new retrofit2.Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (finalDialog != null && finalDialog.isShowing())
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
                if (finalDialog != null && finalDialog.isShowing())
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


    public interface CallbackGenric<T> {
        void onResult(GenericResponseModel<T> response, int requestCode);

//        void onError(String error, int requestCode);
    }

    public interface RazorPayCallback {
        void onGetOrderID(GetOrderIdResponseModel responseModel);
    }
}