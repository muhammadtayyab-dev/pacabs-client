package com.techlogix.pacaps.network;


import com.techlogix.pacaps.models.GenericResponseModel;
import com.techlogix.pacaps.models.ceateUserModel.CreateUserRequestModel;
import com.techlogix.pacaps.models.ceateUserModel.CreateUserResponseModel;
import com.techlogix.pacaps.models.ceateUserModel.VerifyUserOtp;
import com.techlogix.pacaps.models.ceateUserModel.VerifyUserWithMobileAndPasswoadRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GetDataService {

    @POST("user")
    Call<GenericResponseModel<CreateUserResponseModel>> createUser(@Body CreateUserRequestModel requestModel);

    @POST("user/verifyOtp")
    Call<GenericResponseModel<CreateUserResponseModel>> verfiOtp(@Body VerifyUserOtp verifyUserOtp);

    @POST("user/verifyuser")
    Call<GenericResponseModel<CreateUserResponseModel>> verifyWithNumberPassowwrd(@Body VerifyUserWithMobileAndPasswoadRequest request);

}