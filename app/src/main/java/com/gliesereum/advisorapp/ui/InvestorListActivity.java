package com.gliesereum.advisorapp.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.gliesereum.advisorapp.R;
import com.gliesereum.advisorapp.adapter.InvestorAdapter;
import com.gliesereum.advisorapp.network.APIClient;
import com.gliesereum.advisorapp.network.APIInterface;
import com.gliesereum.advisorapp.network.CustomCallback;
import com.gliesereum.advisorapp.network.json.investor.InvestorResponse;
import com.gliesereum.advisorapp.util.ErrorHandler;
import com.gliesereum.advisorapp.util.FastSave;
import com.gliesereum.advisorapp.util.Util;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static com.gliesereum.advisorapp.util.Constants.ACCESS_TOKEN_LG;

public class InvestorListActivity extends AppCompatActivity {

    private APIInterface API;
    private ErrorHandler errorHandler;
    private ConstraintLayout constraintLayout13;
    private ImageView backBtn;
    private TextView bussinesName;
    private ImageView imageView3;
    private Toolbar toolbar;
    private ConstraintLayout constraintLayout19;
    private TextView textView15;
    private TextView textView27;
    private Button addRecord;
    private CustomCallback customCallback;
    private RecyclerView recyclerView;
    //    private ClientListAdapter clientListAdapter;
    private InvestorAdapter investorAdapter;
    private List<InvestorResponse> investorList;
    private Button addNewClient;
    private EditText searchTextView;
    private Button lockBtn;
    private boolean loadingFlag = true;
    private Integer page = 0;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investor_list);
        initView();
        getAllInvestor();
    }

    private void getAllInvestor() {
        API.getAllInvestor(FastSave.getInstance().getString(ACCESS_TOKEN_LG, ""))
                .enqueue(customCallback.getResponseWithProgress(new CustomCallback.ResponseCallback<List<InvestorResponse>>() {
                    @Override
                    public void onSuccessful(Call<List<InvestorResponse>> call, Response<List<InvestorResponse>> response) {
                        investorAdapter.addItems(response.body());
                    }

                    @Override
                    public void onEmpty(Call<List<InvestorResponse>> call, Response<List<InvestorResponse>> response) {

                    }
                }));

    }

    private void initView() {
        FastSave.init(getApplicationContext());
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        new Util(this, toolbar, 2).addNavigation();
        API = APIClient.getClient().create(APIInterface.class);
        customCallback = new CustomCallback(this, this);

        constraintLayout13 = findViewById(R.id.constraintLayout13);
        recyclerView = findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                investorAdapter.removeAll();
                getAllInvestor();
            }
        });
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        investorAdapter = new InvestorAdapter();
        investorAdapter.endLessScrolled(recyclerView);
        recyclerView.setAdapter(investorAdapter);


    }

}
