package com.techlogix.pacaps.network;


import com.google.android.gms.location.Geofence;
import com.techlogix.pacaps.models.GenericResponseModel;
import com.techlogix.pacaps.models.NearestVehiclesModels.GetNearAvailableVehiclesRequestModel;
import com.techlogix.pacaps.models.NearestVehiclesModels.GetNearestAvailbleVehiclesResponseModel;
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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GetDataService {

    @POST("user")
    Call<GenericResponseModel<CreateUserResponseModel>> createUser(@Body CreateUserRequestModel requestModel);

    @POST("user/verifyOtp")
    Call<GenericResponseModel<CreateUserResponseModel>> verfiOtp(@Body VerifyUserOtp verifyUserOtp);

    @POST("user/verifyuser")
    Call<GenericResponseModel<CreateUserResponseModel>> verifyWithNumberPassowwrd(@Body VerifyUserWithMobileAndPasswoadRequest request);

    @POST("v1/orders")
    Call<GetOrderIdResponseModel> getOrderId(@Body GetOrderIdRequestModel request);

    @POST("favourite")
    Call<GenericResponseModel<MyFavoritesResponseModel>> createFavLov(@Body CreateFavLoctionsRequestModel request);

    @GET("favourite/all/{customer_id}")
    Call<GenericResponseModel<ArrayList<MyFavoritesResponseModel>>> getAllFavLoc(@Path(value = "customer_id") int customerId);

    @HTTP(method = "DELETE", path = "favourite/{fav_id}", hasBody = true)
    Call<GenericResponseModel> deleteMyFavLov(@Path(value = "fav_id") int favId, @Body DeleteMyFavLocRequestModel requestModel);

    //Get some default settings from Admin
    @GET("settings")
    Call<GenericResponseModel<ArrayList<GetSettingsResponseModels>>> getDefaultSettings();

    //Estimate the Price for Vehiclewise
    @GET("trip")
    Call<GenericResponseModel<TripEstimationsResponseModel>> getTripPricForVehical(@Body TripEstimateRequestModel requestModel);

    // Get City By UserLat Long
    @GET("city/id/{user_lat}/{user_long}")
    Call<GenericResponseModel<GetCityFromLatLongResponseModel>> getCityByLatLong(@Path(value = "user_lat") double userLat,
                                                                                 @Path(value = "user_long") double userLong);

    //to get 4 kms range vehicles availble on the map. with all vehicle types.
    @POST("trip/nearestVehicleEstimate")
    Call<GenericResponseModel<ArrayList<GetNearestAvailbleVehiclesResponseModel>>> getNearestAvailableVehicles(@Body GetNearAvailableVehiclesRequestModel requestModel);
}