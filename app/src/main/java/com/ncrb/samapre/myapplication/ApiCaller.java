package com.ncrb.samapre.myapplication;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;


public interface ApiCaller {



    //@Headers("Content-Type: application/json")
    //@POST("/mGetCitizenLoginDetailsConnect")
    @Headers("Connection:close")
    @POST("/mLoginVerify")
    public void mLoginVerify(@Body JSONPostParams jsonPostParams,
                             retrofit.Callback<WSPLoginConnect> callback);

    @Headers("Connection:close")
    @POST("/mVehicleTypeConnect")
    public void mVehicleTypeConnect(@Body JSONPostParams jsonPostParam,retrofit.Callback<WSPLoginConnect> callback);

    @Headers("Connection:close")
    @POST("/mPropertySearch")
    public void mPropertySearch(@Body JSONPostParams jsonPostParam,retrofit.Callback<WSPLoginConnect> callback);

    @Headers("Connection:close")
    @POST("/mManufacturerConnect")
    public void mManufacturerConnect(@Body JSONPostParams jsonPostParam,retrofit.Callback<WSPLoginConnect> callback);


    @Headers("Connection:close")
    @POST("/mRelationTypeConnect")
    public void mRelationTypeConnect(@Body JSONPostParams jsonPostParam,retrofit.Callback<WSPLoginConnect> callback);

    @Headers("Connection:close")
    @POST("/mReligionConnect")
    public void mReligionConnect(@Body JSONPostParams jsonPostParam,retrofit.Callback<WSPReligionConnect> callback);

    @Headers("Connection:close")
    @POST("/mMVColorConnect")
    public void mMVColorConnect(@Body JSONPostParams jsonPostParam,retrofit.Callback<WSPLoginConnect> callback);

    @Headers("Connection:close")
    @POST("/mMVModelConnect")
    public void mMVModelConnect(@Body JSONPostParams jsonPostParam,retrofit.Callback<WSPLoginConnect> callback);

    @Headers("Connection:close")
    @POST("/mPersonSearch")
    public void mPersonSearch(@Body JSONPostParams jsonPostParam,retrofit.Callback<WSPLoginConnect> callback);

    @Headers("Connection:close")
    @POST("/mDistrictConnect")
    public void mDistrictConnect(@Body JSONPostParams jsonPostParam,retrofit.Callback<WSPLoginConnect> callback);

    @Headers("Connection:close")
    @POST("/mPoliceStationConnect")
    public void mPoliceStationConnect(@Body JSONPostParams jsonPostParam,retrofit.Callback<WSPLoginConnect> callback);

    @Headers("Connection:close")
    @POST("/mPersonMoreDetails")
    public void mPersonMoreDetails(@Body JSONPostParams jsonPostParam,retrofit.Callback<WSPLoginConnect> callback);

    @Headers("Connection:close")
    @POST("/mPersonMoreDetailsConvict")
    public void mPersonMoreDetailsConvict(@Body JSONPostParams jsonPostParam,retrofit.Callback<WSPLoginConnect> callback);

    @Headers("Connection:close")
    @POST("/mPersonMoreDetailsChargesheet")
    public void mPersonMoreDetailsChargesheet(@Body JSONPostParams jsonPostParam,retrofit.Callback<WSPLoginConnect> callback);


    @Headers("Connection:close")
    @POST("/mPersonMoreDetailsPHOffender")
    public void mPersonMoreDetailsPHOffender(@Body JSONPostParams jsonPostParam,retrofit.Callback<WSPLoginConnect> callback);


    @Headers("Connection:close")
    @POST("/mFIRReportConnect")
    public void mFIRReportConnect(@Body JSONPostParams jsonPostParam,retrofit.Callback<WSPGetFIR> callback);

    @Headers("Connection:close")
    @POST("/mWebService")
    public void mGetImagesBlob(@Body JSONPostParams jsonPostParam,retrofit.Callback<WSPGetBlobImageConnect> callback);

    @Headers("Connection:close")
    @POST("/mPersonDetailDisplay")
    public void mPersonDetailDisplay(@Body JSONPostParams jsonPostParam,retrofit.Callback<WSPLoginConnect> callback);

    @Headers("Connection:close")
    @POST("/mPropertyDetailDisplay")
    public void mPropertyDetailDisplay(@Body JSONPostParams jsonPostParam,retrofit.Callback<WSPLoginConnect> callback);
    @Headers("Connection:close")
    //@Headers("Content-Type: application/json")
    @POST("/mReqSedition")
    public void mReqSedition(@Body JSONPostParams jsonPostParams,
                             retrofit.Callback<WSPReqSedition> callback);






}// end api caller
