package com.gliesereum.advisorapp.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.gliesereum.advisorapp.R;
import com.gliesereum.advisorapp.network.APIClient;
import com.gliesereum.advisorapp.network.APIInterface;
import com.gliesereum.advisorapp.network.json.user.TokenInfo;
import com.gliesereum.advisorapp.network.json.user.UserResponse;
import com.gliesereum.advisorapp.ui.LoginActivity;
import com.gliesereum.advisorapp.ui.SplashActivity;
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
import static com.gliesereum.advisorapp.util.Constants.USER_ID_LG;
import static com.gliesereum.advisorapp.util.Constants.USER_NAME_LG;
import static com.gliesereum.advisorapp.util.Constants.USER_SECOND_NAME_LG;


public class ErrorHandler {

    private Context context;
    private Activity activity;

    public ErrorHandler(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public void showError(Integer errorCode) {
        switch (errorCode) {
            case 1104:
                refreshToken(FastSave.getInstance().getString(REFRESH_TOKEN_LG, ""));
                break;
            case 1105:
                FastSave.getInstance().saveBoolean(IS_LOGIN_LG, false);
                activity.startActivity(new Intent(context, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                activity.finish();
                break;
            case 1102:
                FastSave.getInstance().saveBoolean(IS_LOGIN_LG, false);
                activity.startActivity(new Intent(context, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                activity.finish();
                break;
            default:
                try {
                    new LottieAlertDialog.Builder(context, DialogTypes.TYPE_WARNING)
                            .setTitle("Предупреждение")
                            .setDescription(ErrorList.init(context).getErrorMessage(errorCode))
                            .build()
                            .show();
                } catch (Exception e) {
                    new LottieAlertDialog.Builder(context, DialogTypes.TYPE_ERROR)
                            .setTitle(context.getResources().getString(R.string.error))
                            .setDescription("[" + errorCode + "] " + context.getResources().getString(R.string.unknown_error))
                            .build()
                            .show();
                }
                break;
        }
    }

    public void showCustomError(String message) {
        try {
            activity.startActivity(new Intent(context, SplashActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
            activity.finish();
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void refreshToken(String refreshToken) {
        APIInterface API = APIClient.getClient().create(APIInterface.class);
        Call<UserResponse> call = API.refreshAccessToken(refreshToken);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.code() == 200) {
                    FastSave.getInstance().saveBoolean(IS_LOGIN_LG, true);
                    Toast.makeText(context, "Refresh!", Toast.LENGTH_SHORT).show();
                    setTokenInfo(response.body().getTokenInfo());
                    if (FastSave.getInstance().getString(USER_NAME_LG, "").equals("") || FastSave.getInstance().getString(USER_SECOND_NAME_LG, "").equals("")) {
//                        activity.startActivity(new Intent(context, ChooseBussines.class));
                        activity.finish();
                    } else {
                        activity.startActivity(new Intent(context, LoginActivity.class));
                        activity.finish();
                    }
                } else {
                    try {
                        FastSave.getInstance().saveBoolean(IS_LOGIN_LG, false);
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        showError(jObjError.getInt("code"));
                        activity.startActivity(new Intent(context, LoginActivity.class));
                        activity.finish();
                    } catch (Exception e) {
                        FastSave.getInstance().saveBoolean(IS_LOGIN_LG, false);
                        showCustomError(e.getMessage());
                        activity.startActivity(new Intent(context, LoginActivity.class));
                        activity.finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
//                closeProgressDialog();
                showCustomError(t.getMessage());
                activity.startActivity(new Intent(context, LoginActivity.class));
                activity.finish();
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
}
