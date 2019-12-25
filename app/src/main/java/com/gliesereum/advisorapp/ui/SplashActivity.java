package com.gliesereum.advisorapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.gliesereum.advisorapp.R;
import com.gliesereum.advisorapp.network.APIClient;
import com.gliesereum.advisorapp.network.APIInterface;
import com.gliesereum.advisorapp.network.json.status.StatusResponse;
import com.gliesereum.advisorapp.network.json.user.TokenInfo;
import com.gliesereum.advisorapp.network.json.user.UserResponse;
import com.gliesereum.advisorapp.util.ErrorHandler;
import com.gliesereum.advisorapp.util.FastSave;
import com.gliesereum.advisorapp.util.Util;
import com.labters.lottiealertdialoglibrary.DialogTypes;
import com.labters.lottiealertdialoglibrary.LottieAlertDialog;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gliesereum.advisorapp.util.Constants.ACCESS_EXPIRATION_DATE_LG;
import static com.gliesereum.advisorapp.util.Constants.ACCESS_TOKEN_LG;
import static com.gliesereum.advisorapp.util.Constants.ACCESS_TOKEN_WITHOUT_BEARER_LG;
import static com.gliesereum.advisorapp.util.Constants.IS_LOGIN_LG;
import static com.gliesereum.advisorapp.util.Constants.REFRESH_EXPIRATION_DATE_LG;
import static com.gliesereum.advisorapp.util.Constants.REFRESH_TOKEN_LG;
import static com.gliesereum.advisorapp.util.Constants.STATUS_UP_LG;
import static com.gliesereum.advisorapp.util.Constants.USER_ID_LG;
import static com.gliesereum.advisorapp.util.Constants.USER_NAME_LG;
import static com.gliesereum.advisorapp.util.Constants.USER_SECOND_NAME_LG;

public class SplashActivity extends AppCompatActivity {

    private APIInterface API;
    private ErrorHandler errorHandler;
    private ConstraintLayout errorBlock;
    private Button refreshBtn;
    private String TAG = "test_log";
    private LottieAlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initData();
        initView();
        checkStatus();

    }

    private void initData() {
        FastSave.init(getApplicationContext());
        errorHandler = new ErrorHandler(this, this);
        API = APIClient.getClient().create(APIInterface.class);
    }

    private void initView() {
        errorBlock = findViewById(R.id.errorBlock);
        refreshBtn = findViewById(R.id.refreshBtn);
        refreshBtn.setOnClickListener(v -> checkStatus());
    }

    public void checkStatus() {
        errorBlock.setVisibility(View.GONE);
        showProgressDialog();
        Call<StatusResponse> call = API.checkStatus();
        call.enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                if (response.code() == 200) {
                    if (response.body().getAccountService() != null && response.body().getAccountService().equals(STATUS_UP_LG)
                            && response.body().getMailService() != null && response.body().getMailService().equals(STATUS_UP_LG)
                            && response.body().getLendingGalleryService() != null && response.body().getLendingGalleryService().equals(STATUS_UP_LG)) {
                        checkAccessToken();
                    } else {
                        errorBlock.setVisibility(View.VISIBLE);
                    }
                } else {
                    errorBlock.setVisibility(View.VISIBLE);
                }
                closeProgressDialog();
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {
                errorBlock.setVisibility(View.VISIBLE);
                closeProgressDialog();
            }
        });
    }

    public void checkAccessToken() {
        Log.d(TAG, "checkAccessToken: ");
        Call<UserResponse> call = API.checkAccessToken(FastSave.getInstance().getString(ACCESS_TOKEN_WITHOUT_BEARER_LG, ""));
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.code() == 200) {
                    if (FastSave.getInstance().getBoolean(IS_LOGIN_LG, false)) {
                        checkToken();
                    } else {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                        finish();
                    }
                } else {
                    checkToken();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                errorBlock.setVisibility(View.VISIBLE);
                closeProgressDialog();
            }
        });
    }

    public void checkToken() {
        Log.d(TAG, "checkToken: ");
        Long accessExpirationDate = FastSave.getInstance().getLong(ACCESS_EXPIRATION_DATE_LG, 0);
        Long refreshExpirationDate = FastSave.getInstance().getLong(REFRESH_EXPIRATION_DATE_LG, 0);

        if (accessExpirationDate != 0) {
            if (Util.checkExpirationToken(accessExpirationDate)) {
                FastSave.getInstance().saveBoolean(IS_LOGIN_LG, true);
                if (FastSave.getInstance().getString(USER_NAME_LG, "").equals("") || FastSave.getInstance().getString(USER_SECOND_NAME_LG, "").equals("")) {
                    startActivity(new Intent(SplashActivity.this, ChooseArtBondActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashActivity.this, ChooseArtBondActivity.class));
                    finish();
                }
            } else {
                if (Util.checkExpirationToken(refreshExpirationDate)) {
                    refreshToken(FastSave.getInstance().getString(REFRESH_TOKEN_LG, ""));
                } else {
                    FastSave.getInstance().saveBoolean(IS_LOGIN_LG, false);
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
            }
        } else {
            FastSave.getInstance().saveBoolean(IS_LOGIN_LG, false);
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }
    }

    public void refreshToken(String refreshToken) {
        Log.d(TAG, "refreshToken: ");
        API = APIClient.getClient().create(APIInterface.class);
        Call<UserResponse> call = API.refreshAccessToken(refreshToken);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.code() == 200) {
                    FastSave.getInstance().saveBoolean(IS_LOGIN_LG, true);
                    Toast.makeText(SplashActivity.this, "Refresh!", Toast.LENGTH_SHORT).show();
                    setTokenInfo(response.body().getTokenInfo());
                    if (FastSave.getInstance().getString(USER_NAME_LG, "").equals("") || FastSave.getInstance().getString(USER_SECOND_NAME_LG, "").equals("")) {
                        startActivity(new Intent(SplashActivity.this, ChooseArtBondActivity.class));
                        finish();
                    } else {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                        finish();
                    }
                } else {
                    try {
                        FastSave.getInstance().saveBoolean(IS_LOGIN_LG, false);
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        errorHandler.showError(jObjError.getInt("code"));
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                        finish();
                    } catch (Exception e) {
                        FastSave.getInstance().saveBoolean(IS_LOGIN_LG, false);
                        errorHandler.showCustomError(e.getMessage());
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                errorHandler.showCustomError(t.getMessage());
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    private void setTokenInfo(TokenInfo response) {
        FastSave.getInstance().saveBoolean(IS_LOGIN_LG, true);
        FastSave.getInstance().saveString(ACCESS_TOKEN_LG, "Bearer " + response.getAccessToken());
        FastSave.getInstance().saveString(ACCESS_TOKEN_WITHOUT_BEARER_LG, response.getAccessToken());
        FastSave.getInstance().saveString(REFRESH_TOKEN_LG, response.getRefreshToken());
        FastSave.getInstance().saveString(USER_ID_LG, response.getUserId());
        FastSave.getInstance().saveLong(ACCESS_EXPIRATION_DATE_LG, response.getAccessExpirationDate());
        FastSave.getInstance().saveLong(REFRESH_EXPIRATION_DATE_LG, response.getRefreshExpirationDate());
    }

    public void showProgressDialog() {
        if (alertDialog == null || !alertDialog.isShowing()) {
            alertDialog = new LottieAlertDialog.Builder(this, DialogTypes.TYPE_LOADING)
                    .setTitle("Загрузка")
                    .setDescription("Происходит загрузка, подождите")
                    .build();
            alertDialog.setCancelable(false);
            alertDialog.show();
        }


    }

    public void closeProgressDialog() {
        try {
            if (alertDialog != null && alertDialog.isShowing()) {
                alertDialog.dismiss();
            }
        } catch (Exception e) {

        }
    }
}
