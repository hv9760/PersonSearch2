package com.ncrb.samapre.myapplication.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.print.PrintManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ncrb.samapre.myapplication.ApiCaller;
import com.ncrb.samapre.myapplication.Constants;
import com.ncrb.samapre.myapplication.JSONPostParams;
import com.ncrb.samapre.myapplication.MCoCoRy;
import com.ncrb.samapre.myapplication.R;
import com.ncrb.samapre.myapplication.ViewPrintAdapter;
import com.ncrb.samapre.myapplication.WSPLoginConnect;
import com.squareup.okhttp.OkHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;
import retrofit.mime.TypedInput;

public class PersonMoreDetails extends AppCompatActivity {
    ImageButton btn_md_back;
    MCoCoRy mCoCoRy = new  MCoCoRy();


    public ProgressDialog mProgressDialog;

    String FIR_REG_NUM,ACCUSED_SRNO;
    String ARREST_SURRENDER_DT,STATE_ENG_Arr,DISTRICT_Arr,PS_Arr,occupation_md,ARREST_ACTION_md;
    String IS_CHRGSHEETED_md,CHARGESHEET_NUM_md,CHARGESHEET_DT_md, CHARGESHEET_ACTSEC_md;
    String ArrestedResult, SURREND_MAGISTRATE, SURRENDERED_COURT;
    String IsConvicted, PunishmentType, JudgementDt, PunishmentPeriod;
    String isProclaimedOffender, ProclaimedOffenderCourtName, ProclaimedOffenderCourtType, ProclaimedOffenderCourtLocation, ProclaimedOffenderOrderNum, ProclaimedOffenderOrderDt, isHabitualOffender;
    String section;


    TextView textView_person_md_ArrSurrDt,textView_person_md_StateArr, textView_person_md_DistrictArr,textView_person_md_PSArr,textView_person_md_SectionCdArr;
    TextView textView_person_md_OccArr, textView_person_md_ArrAction, textView_person_md_IsChrg, textView_person_md_ChrgNum, textView_person_md_ChrgDt;
    TextView textView_person_md_ChrgActSec;
    TextView textView_person_md_ArrestedResult, textView_person_md_SURREND_MAGISTRATE, textView_person_md_SURRENDERED_COURT;
    TextView textView_person_md_IsConvicted, textView_person_md_PunishmentType, textView_person_md_JudgementDt, textView_person_md_PunishmentPeriod;
    TextView textView_person_md_isProclaimedOffender,textView_person_md_ProclaimedOffenderCourtName,textView_person_md_ProclaimedOffenderCourtType, textView_person_md_ProclaimedOffenderCourtLocation;
    TextView textView_person_md_ProclaimedOffenderOrderNum, textView_person_md_ProclaimedOffenderOrderDt, textView_person_md_isHabitualOffender;
    Button btn_pdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_more_details);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Please wait...");
        btn_md_back=(ImageButton)findViewById(R.id.btn_md_Back);
        btn_pdf=(Button)findViewById(R.id.btn_pdf);

        textView_person_md_ArrSurrDt=(TextView)findViewById(R.id.txt_person_md_ArrSurrDt);
        textView_person_md_StateArr=(TextView)findViewById(R.id.txt_person_md_StateArr);
        textView_person_md_DistrictArr=(TextView)findViewById(R.id.txt_person_md_DistrictArr);
        textView_person_md_PSArr=(TextView)findViewById(R.id.txt_person_md_PSArr);
        textView_person_md_SectionCdArr=(TextView)findViewById(R.id.txt_person_md_SectionCdArr);
        textView_person_md_OccArr=(TextView)findViewById(R.id.txt_person_md_OccArr);
        textView_person_md_ArrAction=(TextView)findViewById(R.id.txt_person_md_ArrAction);
        textView_person_md_ArrestedResult=(TextView)findViewById(R.id.txt_person_md_ArrestedResult);
        textView_person_md_SURREND_MAGISTRATE=(TextView)findViewById(R.id.txt_person_md_SURREND_MAGISTRATE);
        textView_person_md_SURRENDERED_COURT=(TextView)findViewById(R.id.txt_person_md_SURRENDERED_COURT);
        textView_person_md_IsChrg=(TextView)findViewById(R.id.txt_person_md_IsChrg);
        textView_person_md_ChrgNum=(TextView)findViewById(R.id.txt_person_md_ChrgNum);
        textView_person_md_ChrgDt=(TextView)findViewById(R.id.txt_person_md_ChrgDt);
        textView_person_md_ChrgActSec=(TextView)findViewById(R.id.txt_person_md_ChrgActSec);

        textView_person_md_IsConvicted=(TextView)findViewById(R.id.txt_person_md_IsConvicted);
        textView_person_md_PunishmentType=(TextView)findViewById(R.id.txt_person_md_PunishmentType);
        textView_person_md_JudgementDt=(TextView)findViewById(R.id.txt_person_md_JudgementDt);
        textView_person_md_PunishmentPeriod=(TextView)findViewById(R.id.txt_person_md_PunishmentPeriod);

        textView_person_md_isProclaimedOffender=(TextView)findViewById(R.id.txt_person_md_isProclaimedOffender);
        textView_person_md_ProclaimedOffenderCourtName=(TextView)findViewById(R.id.txt_person_md_ProclaimedOffenderCourtName);
        textView_person_md_ProclaimedOffenderCourtType=(TextView)findViewById(R.id.txt_person_md_ProclaimedOffenderCourtType);
        textView_person_md_ProclaimedOffenderCourtLocation=(TextView)findViewById(R.id.txt_person_md_ProclaimedOffenderCourtLocation);
        textView_person_md_ProclaimedOffenderOrderNum=(TextView)findViewById(R.id.txt_person_md_ProclaimedOffenderOrderNum);
        textView_person_md_ProclaimedOffenderOrderDt=(TextView)findViewById(R.id.txt_person_md_ProclaimedOffenderOrderDt);
        textView_person_md_isHabitualOffender=(TextView)findViewById(R.id.txt_person_md_isHabitualOffender);


        Bundle data= getIntent().getExtras();

        FIR_REG_NUM=data.getString("REGISTRATION_NUM");
        ACCUSED_SRNO=data.getString("AccusedSrno");

        try {
            GetArrestDetailWebService();
            GetChargesheetDetailWebService();
            GetConvictDetailWebService();
            GetOffenderDetailWebService();

        } catch (Exception e) {
            e.printStackTrace();
        }

        btn_md_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();
            }
        });

        btn_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                printPDF(view);
            }
        });



    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        return;
    }


    public void printPDF(View view) {
        try {
            PrintManager printManager = (PrintManager) getSystemService(PRINT_SERVICE);
            printManager.print("print_any_view_job_name", new ViewPrintAdapter(this, findViewById(R.id.pdfLayout)), null);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RestAdapter providesRestAdapter() {
        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(480, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(480, TimeUnit.SECONDS);

        return new RestAdapter.Builder()
                .setEndpoint(Constants.API_BASE_URL)
                .setClient(new OkClient(okHttpClient))
                .setLog(new RestAdapter.Log() {
                    @Override
                    public void log(String msg) {
                        Log.i("Res Complaint -", msg);
                    }}).setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
    }

    public void GetArrestDetailWebService() throws Exception {

          this.mProgressDialog.show();
        String coco_seed = ""; String coco_seed_encd = "";
        try {
            Map postParams = new HashMap();
        postParams.put("FIR_REG_NUM",FIR_REG_NUM.toString());
        postParams.put("ACCUSED_SRNO",ACCUSED_SRNO.toString());
        Gson gsonObj = new Gson();
        coco_seed = gsonObj.toJson(postParams);
        coco_seed_encd  = mCoCoRy.ThreadToSecureDetail(getApplicationContext(), coco_seed, "ENCODE");
    } catch (Exception e) {
        e.printStackTrace();
    }
    Map postParams = new HashMap();

        postParams.put("seed", coco_seed_encd);

        JSONPostParams jsonPostParams = new JSONPostParams("mPersonMoreDetails", postParams);

        RestAdapter restAdapter =providesRestAdapter();

        ApiCaller apiCaller = restAdapter.create(ApiCaller.class);
        apiCaller.mPersonMoreDetails(jsonPostParams, new Callback<WSPLoginConnect>() {

                    @Override
                    public void failure(RetrofitError arg0) {
                        if (mProgressDialog != null && mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Can not connect to server.", Toast.LENGTH_LONG).show();


                    }// end failure

                    @Override
                    public void success(WSPLoginConnect result2, Response response) {

                        String jsonString = "";

                        if(!result2.seed.equals("")) {

                            jsonString = mCoCoRy.ThreadToSecureDetail(getApplicationContext(), result2.seed, "DECODE");

                            if(jsonString.equals("")) {
                                Toast.makeText(getApplicationContext(), "System error, please contact administrator.", Toast.LENGTH_LONG).show();
                                return;
                            }

                        }

                        Gson gson = new Gson();


                        WSPLoginConnect result = gson.fromJson(jsonString, WSPLoginConnect.class);
                        if (result.STATUS_CODE.toString().equals("200")) {
                            if (mProgressDialog != null && mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                            TypedInput body = response.getBody();

                            try {
                                JSONObject reader = new JSONObject(jsonString);
                                JSONArray states = reader.getJSONArray("PersonMoreDetailsList");

                                for (int i = 0; i < states.length(); i++) {
                                    JSONObject jsonObj2 = states.getJSONObject(i);
                                     ARREST_SURRENDER_DT=states.getJSONObject(0).getString("ARREST_SURRENDER_DT");
                                    STATE_ENG_Arr=states.getJSONObject(0).getString("STATE_ENG");
                                    DISTRICT_Arr=states.getJSONObject(0).getString("DISTRICT");
                                    PS_Arr=states.getJSONObject(0).getString("PS");

                                    occupation_md=states.getJSONObject(0).getString("occupation");
                                    ARREST_ACTION_md=states.getJSONObject(0).getString("ARREST_ACTION");

                                    ArrestedResult=states.getJSONObject(0).getString("ArrestedResult");
                                    SURREND_MAGISTRATE=states.getJSONObject(0).getString("SURREND_MAGISTRATE");
                                    SURRENDERED_COURT=states.getJSONObject(0).getString("SURRENDERED_COURT");
                                    section=states.getJSONObject(0).getString("section");

                                } //end forloop


                                textView_person_md_ArrSurrDt.setText(ARREST_SURRENDER_DT);
                                textView_person_md_StateArr.setText(STATE_ENG_Arr);
                                textView_person_md_DistrictArr.setText(DISTRICT_Arr);
                                textView_person_md_PSArr.setText(PS_Arr);
                                textView_person_md_SectionCdArr.setText(section);
                                textView_person_md_OccArr.setText(occupation_md);
                                textView_person_md_ArrAction.setText(ARREST_ACTION_md);

                                textView_person_md_ArrestedResult.setText(ArrestedResult);
                                textView_person_md_SURREND_MAGISTRATE.setText(SURREND_MAGISTRATE);
                                textView_person_md_SURRENDERED_COURT.setText(SURRENDERED_COURT);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        } else {
                            if (mProgressDialog != null && mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_LONG).show();

                        }
                    }

                }

        );

    }
    public void GetChargesheetDetailWebService() throws Exception {
          this.mProgressDialog.show();
        String coco_seed = ""; String coco_seed_encd = "";


        try {

            Map postParams = new HashMap();

        postParams.put("FIR_REG_NUM_CS",FIR_REG_NUM.toString());
        postParams.put("ACCUSED_SRNO_CS",ACCUSED_SRNO.toString());

            Gson gsonObj = new Gson();
            coco_seed = gsonObj.toJson(postParams);




            coco_seed_encd  = mCoCoRy.ThreadToSecureDetail(getApplicationContext(), coco_seed, "ENCODE");

        } catch (Exception e) {
            e.printStackTrace();
        }

        Map postParams = new HashMap();

        postParams.put("seed", coco_seed_encd);

        JSONPostParams jsonPostParams = new JSONPostParams("mPersonMoreDetailsChargesheet", postParams);

        RestAdapter restAdapter =providesRestAdapter();

        ApiCaller apiCaller = restAdapter.create(ApiCaller.class);

        int cnt;
        apiCaller.mPersonMoreDetailsChargesheet(jsonPostParams, new Callback<WSPLoginConnect>() {

                    @Override
                    public void failure(RetrofitError arg0) {
                        if (mProgressDialog != null && mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Can not connect to server.", Toast.LENGTH_LONG).show();


                    }// end failure

                    @Override
                    public void success(WSPLoginConnect result2, Response response) {


                        String jsonString = "";

                        if(!result2.seed.equals("")) {

                            jsonString = mCoCoRy.ThreadToSecureDetail(getApplicationContext(), result2.seed, "DECODE");

                            if(jsonString.equals("")) {
                                Toast.makeText(getApplicationContext(), "System error, please contact administrator.", Toast.LENGTH_LONG).show();
                                return;
                            }

                        }

                        Gson gson = new Gson();


                        WSPLoginConnect result = gson.fromJson(jsonString, WSPLoginConnect.class);
                        if (result.STATUS_CODE.toString().equals("200")) {
                            if (mProgressDialog != null && mProgressDialog.isShowing())
                                mProgressDialog.dismiss();

                            try {
                                JSONObject reader = new JSONObject(jsonString);
                                JSONArray states = reader.getJSONArray("PersonMoreDetailsListChargesheet");

                                for (int i = 0; i < states.length(); i++) {
                                    JSONObject jsonObj2 = states.getJSONObject(i);



                                    IS_CHRGSHEETED_md=states.getJSONObject(0).getString("IS_CHRGSHEETED");
                                    CHARGESHEET_NUM_md=states.getJSONObject(0).getString("CHARGESHEET_NUM");
                                    CHARGESHEET_DT_md=states.getJSONObject(0).getString("CHARGESHEET_DT");
                                    CHARGESHEET_ACTSEC_md=states.getJSONObject(0).getString("Section");


                                } //end forloop



                                textView_person_md_IsChrg.setText(IS_CHRGSHEETED_md);
                                textView_person_md_ChrgNum.setText(CHARGESHEET_NUM_md);
                                textView_person_md_ChrgDt.setText(CHARGESHEET_DT_md);
                                textView_person_md_ChrgActSec.setText(CHARGESHEET_ACTSEC_md);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        } else {
                            if (mProgressDialog != null && mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_LONG).show();


                        }
                    }// end success

                }

        );




    }

    public void GetConvictDetailWebService() throws Exception {
          this.mProgressDialog.show();
        String coco_seed = ""; String coco_seed_encd = "";


        try {

            Map postParams = new HashMap();

        postParams.put("FIR_REG_NUM_con",FIR_REG_NUM.toString());
        postParams.put("ACCUSED_SRNO_con",ACCUSED_SRNO.toString());
        Gson gsonObj = new Gson();
        coco_seed = gsonObj.toJson(postParams);
        coco_seed_encd  = mCoCoRy.ThreadToSecureDetail(getApplicationContext(), coco_seed, "ENCODE");

    } catch (Exception e) {
        e.printStackTrace();
    }

    Map postParams = new HashMap();

        postParams.put("seed", coco_seed_encd);


        JSONPostParams jsonPostParams = new JSONPostParams("mPersonMoreDetailsConvict", postParams);


        // -----------------------------------------------------------------

        RestAdapter restAdapter =providesRestAdapter();

        ApiCaller apiCaller = restAdapter.create(ApiCaller.class);

        int cnt;
        apiCaller.mPersonMoreDetailsConvict(jsonPostParams, new Callback<WSPLoginConnect>() {

                    @Override
                    public void failure(RetrofitError arg0) {
                        if (mProgressDialog != null && mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Can not connect to server.", Toast.LENGTH_LONG).show();


                    }// end failure

                    @Override
                    public void success(WSPLoginConnect result2, Response response) {

                        String jsonString = "";

                        if(!result2.seed.equals("")) {

                            jsonString = mCoCoRy.ThreadToSecureDetail(getApplicationContext(), result2.seed, "DECODE");

                            if(jsonString.equals("")) {
                                Toast.makeText(getApplicationContext(), "System error, please contact administrator.", Toast.LENGTH_LONG).show();
                                return;
                            }

                        }

                        Gson gson = new Gson();

                        WSPLoginConnect result = gson.fromJson(jsonString, WSPLoginConnect.class);
                        if (result.STATUS_CODE.toString().equals("200")) {
                            if (mProgressDialog != null && mProgressDialog.isShowing())
                                mProgressDialog.dismiss();

                            try {
                                JSONObject reader = new JSONObject(jsonString);
                                JSONArray states = reader.getJSONArray("PersonMoreDetailsConvictList");

                                for (int i = 0; i < states.length(); i++) {
                                    JSONObject jsonObj2 = states.getJSONObject(i);

                                    System.out.println("IsConvicted:"+IsConvicted);


                                    IsConvicted=states.getJSONObject(0).getString("IsConvicted");
                                    PunishmentType=states.getJSONObject(0).getString("PUNISHMENT_TYPE");
                                    JudgementDt=states.getJSONObject(0).getString("judgement_date");
                                    PunishmentPeriod = states.getJSONObject(0).getString("PUNISH_YRS") + " Yrs " + states.getJSONObject(0).getString("PUNISH_MNTH") + " Months " + states.getJSONObject(0).getString("PUNISH_DAYS") + " Days";

                                } //end forloop

                                System.out.println("IsConvicted"+IsConvicted);

                                textView_person_md_IsConvicted.setText(IsConvicted);
                                textView_person_md_PunishmentType.setText(PunishmentType);
                                textView_person_md_JudgementDt.setText(JudgementDt);
                                textView_person_md_PunishmentPeriod.setText(PunishmentPeriod);



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        } else {
                            if (mProgressDialog != null && mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_LONG).show();


                        }
                    }// end success

                }

        );

    }

    public void GetOffenderDetailWebService() throws Exception {
          this.mProgressDialog.show();
        String coco_seed = ""; String coco_seed_encd = "";

        try {

            Map postParams = new HashMap();

        postParams.put("FIR_REG_NUM_off",FIR_REG_NUM.toString());
        postParams.put("ACCUSED_SRNO_off",ACCUSED_SRNO.toString());

            Gson gsonObj = new Gson();
            coco_seed = gsonObj.toJson(postParams);

            coco_seed_encd  = mCoCoRy.ThreadToSecureDetail(getApplicationContext(), coco_seed, "ENCODE");

        } catch (Exception e) {
            e.printStackTrace();
        }

        Map postParams = new HashMap();

        postParams.put("seed", coco_seed_encd);

        JSONPostParams jsonPostParams = new JSONPostParams("mPersonMoreDetailsPHOffender", postParams);

        // -----------------------------------------------------------------

        RestAdapter restAdapter =providesRestAdapter();

        ApiCaller apiCaller = restAdapter.create(ApiCaller.class);

        int cnt;
        apiCaller.mPersonMoreDetailsPHOffender(jsonPostParams, new Callback<WSPLoginConnect>() {

                    @Override
                    public void failure(RetrofitError arg0) {
                        if (mProgressDialog != null && mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Can not connect to server.", Toast.LENGTH_LONG).show();


                    }// end failure

                    @Override
                    public void success(WSPLoginConnect result2, Response response) {


                        String jsonString = "";

                        if(!result2.seed.equals("")) {

                            jsonString = mCoCoRy.ThreadToSecureDetail(getApplicationContext(), result2.seed, "DECODE");

                            if(jsonString.equals("")) {
                                Toast.makeText(getApplicationContext(), "System error, please contact administrator.", Toast.LENGTH_LONG).show();
                                return;
                            }

                        }

                        Gson gson = new Gson();

                        WSPLoginConnect result = gson.fromJson(jsonString, WSPLoginConnect.class);
                        if (result.STATUS_CODE.toString().equals("200")) {
                            if (mProgressDialog != null && mProgressDialog.isShowing())
                                mProgressDialog.dismiss();

                            try {
                                JSONObject reader = new JSONObject(jsonString);
                                JSONArray states = reader.getJSONArray("PersonMoreDetailsPHOffenderList");

                                for (int i = 0; i < states.length(); i++) {
                                    JSONObject jsonObj2 = states.getJSONObject(i);

                                    isProclaimedOffender=states.getJSONObject(0).getString("is_proclaimed_offender");
                                    ProclaimedOffenderCourtName=states.getJSONObject(0).getString("PROCL_OFFENDER_COURT_NAME");
                                    ProclaimedOffenderCourtType=states.getJSONObject(0).getString("COURT_TYPE");
                                    ProclaimedOffenderCourtLocation=states.getJSONObject(0).getString("PROCL_OFFENDER_COURT_LOCATION");
                                    ProclaimedOffenderOrderNum=states.getJSONObject(0).getString("PROCL_OFFENDER_ORDER_NUM");
                                    ProclaimedOffenderOrderDt=states.getJSONObject(0).getString("PROCL_OFFENDER_ORDER_DT");
                                    isHabitualOffender=states.getJSONObject(0).getString("HabitualOffender");

                                } //end forloop

                                textView_person_md_isProclaimedOffender.setText(isProclaimedOffender);
                                textView_person_md_ProclaimedOffenderCourtName.setText(ProclaimedOffenderCourtName);
                                textView_person_md_ProclaimedOffenderCourtType.setText(ProclaimedOffenderCourtType);
                                textView_person_md_ProclaimedOffenderCourtLocation.setText(ProclaimedOffenderCourtLocation);
                                textView_person_md_ProclaimedOffenderOrderNum.setText(ProclaimedOffenderOrderNum);
                                textView_person_md_ProclaimedOffenderOrderDt.setText(ProclaimedOffenderOrderDt);
                                textView_person_md_isHabitualOffender.setText(isHabitualOffender);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        } else {
                            if (mProgressDialog != null && mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_LONG).show();


                        }
                    }// end success

                }

        );

    }


}
