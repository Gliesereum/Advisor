package com.gliesereum.advisorapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.gliesereum.advisorapp.R;
import com.gliesereum.advisorapp.adapter.ChooseAtrBondAdapter;
import com.gliesereum.advisorapp.adapter.customadapterrecycleview.listener.OnClickItemListener;
import com.gliesereum.advisorapp.network.APIClient;
import com.gliesereum.advisorapp.network.APIInterface;
import com.gliesereum.advisorapp.network.CustomCallback;
import com.gliesereum.advisorapp.network.json.artbond.ArtBondResponse;
import com.gliesereum.advisorapp.network.json.notificatoin.UserSubscribe;
import com.gliesereum.advisorapp.util.FastSave;
import com.labters.lottiealertdialoglibrary.ClickListener;
import com.labters.lottiealertdialoglibrary.DialogTypes;
import com.labters.lottiealertdialoglibrary.LottieAlertDialog;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static com.gliesereum.advisorapp.util.Constants.ACCESS_EXPIRATION_DATE_LG;
import static com.gliesereum.advisorapp.util.Constants.ACCESS_TOKEN_LG;
import static com.gliesereum.advisorapp.util.Constants.ACCESS_TOKEN_WITHOUT_BEARER_LG;
import static com.gliesereum.advisorapp.util.Constants.ARTBOND_ID;
import static com.gliesereum.advisorapp.util.Constants.ARTBOND_NAME;
import static com.gliesereum.advisorapp.util.Constants.IS_LOGIN_LG;
import static com.gliesereum.advisorapp.util.Constants.RECORD_LIST_ACTIVITY;
import static com.gliesereum.advisorapp.util.Constants.REFRESH_EXPIRATION_DATE_LG;
import static com.gliesereum.advisorapp.util.Constants.REFRESH_TOKEN_LG;
import static com.gliesereum.advisorapp.util.Constants.USER_ID_LG;

public class ChooseArtBondActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private APIInterface API;
    private CustomCallback customCallback;
    private List<ArtBondResponse> artBondList;
    private ChooseAtrBondAdapter chooseAtrBondAdapter;
    private Button logoutBtn;
    private ImageView imageView3;
    private LottieAlertDialog alertDialog;
    private List<UserSubscribe> subscribeList;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_art_bond);
        FastSave.init(getApplicationContext());
        initView();
        getAllCarWash();


    }

    private void initView() {
        API = APIClient.getClient().create(APIInterface.class);
        customCallback = new CustomCallback(this, this);
        logoutBtn = findViewById(R.id.backBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        recyclerView = findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                FastSave.getInstance().saveBoolean(RECORD_LIST_ACTIVITY, true);
                chooseAtrBondAdapter.removeAll();
                getAllCarWash();
            }
        });
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        chooseAtrBondAdapter = new ChooseAtrBondAdapter();
        chooseAtrBondAdapter.endLessScrolled(recyclerView);
        recyclerView.setAdapter(chooseAtrBondAdapter);

        chooseAtrBondAdapter.setOnClickItemListener(recyclerView, new OnClickItemListener<ArtBondResponse>() {
            @Override
            public void onClickItem(int position, ArtBondResponse element) {
                if (element != null) {
                    FastSave.getInstance().saveString(ARTBOND_ID, element.getId());
                    FastSave.getInstance().saveString(ARTBOND_NAME, element.getName());
                    startActivity(new Intent(ChooseArtBondActivity.this, ArtBondOrdersActivity.class));
                    finish();
                }
            }

            @Override
            public void onLongClickItem(int position, ArtBondResponse element) {

            }
        });
    }

    private void getAllCarWash() {
        API.getAllArtBonds(FastSave.getInstance().getString(ACCESS_TOKEN_LG, ""))
                .enqueue(customCallback.getResponseWithProgress(new CustomCallback.ResponseCallback<List<ArtBondResponse>>() {
                    @Override
                    public void onSuccessful(Call<List<ArtBondResponse>> call, Response<List<ArtBondResponse>> response) {
                        artBondList = response.body();
                        if (artBondList != null && artBondList.size() > 0) {
                            chooseAtrBondAdapter.addItems(artBondList);
                        }
                    }

                    @Override
                    public void onEmpty(Call<List<ArtBondResponse>> call, Response<List<ArtBondResponse>> response) {
                        Toast.makeText(ChooseArtBondActivity.this, "204", Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    private void logout() {
        alertDialog = new LottieAlertDialog.Builder(this, DialogTypes.TYPE_QUESTION)
                .setTitle("Выход")
                .setDescription("Вы действительно хотите выйти со своего профиля?")
                .setPositiveText("Да")
                .setNegativeText("Нет")
                .setPositiveButtonColor(getResources().getColor(R.color.md_red_A200))
                .setPositiveListener(new ClickListener() {
                    @Override
                    public void onClick(@NotNull LottieAlertDialog lottieAlertDialog) {
//                        deleteRegistrationToken();
                        FastSave.getInstance().deleteValue(IS_LOGIN_LG);
                        FastSave.getInstance().deleteValue(USER_ID_LG);
                        FastSave.getInstance().deleteValue(ACCESS_TOKEN_LG);
                        FastSave.getInstance().deleteValue(ACCESS_TOKEN_WITHOUT_BEARER_LG);
                        FastSave.getInstance().deleteValue(REFRESH_TOKEN_LG);
                        FastSave.getInstance().deleteValue(ACCESS_EXPIRATION_DATE_LG);
                        FastSave.getInstance().deleteValue(REFRESH_EXPIRATION_DATE_LG);
                        startActivity(new Intent(ChooseArtBondActivity.this, LoginActivity.class));
                        finish();
                    }
                })
                .setNegativeListener(new ClickListener() {
                    @Override
                    public void onClick(@NotNull LottieAlertDialog lottieAlertDialog) {
                        alertDialog.dismiss();
                    }
                })
                .build();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }


}
