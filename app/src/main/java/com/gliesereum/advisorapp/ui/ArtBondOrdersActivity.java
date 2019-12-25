package com.gliesereum.advisorapp.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.gliesereum.advisorapp.R;
import com.gliesereum.advisorapp.adapter.ArtBondOrderAdapter;
import com.gliesereum.advisorapp.adapter.customadapterrecycleview.listener.OnClickItemListener;
import com.gliesereum.advisorapp.network.APIClient;
import com.gliesereum.advisorapp.network.APIInterface;
import com.gliesereum.advisorapp.network.CustomCallback;
import com.gliesereum.advisorapp.network.json.order.ArtBondOrderResponse;
import com.gliesereum.advisorapp.network.json.order.ArtBondOrdersSearchBody;
import com.gliesereum.advisorapp.util.ErrorHandler;
import com.gliesereum.advisorapp.util.FastSave;
import com.gliesereum.advisorapp.util.Util;
import com.gohn.nativedialog.NDialog;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static com.gliesereum.advisorapp.util.Constants.ACCESS_TOKEN_LG;
import static com.gliesereum.advisorapp.util.Constants.ARTBOND_ID;
import static com.gliesereum.advisorapp.util.Constants.ARTBOND_NAME;
import static com.gliesereum.advisorapp.util.Constants.ARTBOND_ORDER;
import static com.gliesereum.advisorapp.util.Constants.RECORD_LIST_ACTIVITY;

public class ArtBondOrdersActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArtBondOrderAdapter artBondOrderAdapter;
    private List<ArtBondOrderResponse> orderssList = new ArrayList<>();
    //    private Map<String, String> carWashNameMap = new HashMap<>();
    private APIInterface API;
    private ErrorHandler errorHandler;
    private TextView splashTextView;
    private ProgressDialog progressDialog;
    private NDialog nDialog;
    private CustomCallback customCallback;
    private Button addRecord;
    private Button filterBtn;
    private Calendar date;
    private Long begin = 0L;
    private ArtBondOrdersSearchBody recordsSearchBody;
    private TextView fromDateLabel;
    private TextView toDateLabel;
    private ChipGroup chipGroup;
    //    private TextView bussinesName;
//    private ImageView backBtn;
    private TextView moneyCount;
    private String TAG = "activityTest";
    private CheckBox checkWaiting;
    private CheckBox checkExpired;
    private CheckBox checkInProcess;
    private CheckBox checkCompleted;
    private CheckBox checkCanceled;
    private ImageView fromBtn;
    private ImageView toBtn;
    private TextView fromLabel1;
    private TextView fromLabel2;
    private TextView toLabel1;
    private TextView toLabel2;
    private Toolbar toolbar;
    private Integer page = 0;
    private boolean loadingFlag = true;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String intentBusinessId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art_bond_orders);
        initView();
        getAllArtBodOrders();
    }

    private void initView() {
        FastSave.init(getApplicationContext());
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(FastSave.getInstance().getString(ARTBOND_NAME, ""));
        setSupportActionBar(toolbar);
        new Util(this, toolbar, 1).addNavigation();
        API = APIClient.getClient().create(APIInterface.class);
        customCallback = new CustomCallback(this, this);
        errorHandler = new ErrorHandler(this, this);
        recyclerView = findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                FastSave.getInstance().saveBoolean(RECORD_LIST_ACTIVITY, true);
                artBondOrderAdapter.removeAll();
                page = 0;
                getAllArtBodOrders();
//                finish();
            }
        });
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        artBondOrderAdapter = new ArtBondOrderAdapter();
        artBondOrderAdapter.endLessScrolled(recyclerView);
        recyclerView.setAdapter(artBondOrderAdapter);

        artBondOrderAdapter.setOnClickItemListener(recyclerView, new OnClickItemListener<ArtBondOrderResponse>() {
            @Override
            public void onClickItem(int position, ArtBondOrderResponse element) {
                if (element != null) {
                    FastSave.getInstance().saveObject(ARTBOND_ORDER, element);
                    startActivity(new Intent(ArtBondOrdersActivity.this, SingleArtBondOrderActivity.class));
                    finish();
                }
            }

            @Override
            public void onLongClickItem(int position, ArtBondOrderResponse element) {

            }
        });

//        artBondOrderAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore() {
//                loadingFlag = false;
//                artBondOrderAdapter.showLoading();
//                ++page;
//                getAllRecord();
//            }
//        });


//        addRecord = findViewById(R.id.addRecord);
//        addRecord.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(RecordListActivity.this, OrderActivity.class));
//            }
//        });
//        filterBtn = findViewById(R.id.filterBtn);
//        filterBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openFilterDialog();
////                startActivity(new Intent(RecordListActivity.this, OrderNewActivity.class));
////                openFilterDialog();
//            }
//        });
//        moneyCount = findViewById(R.id.moneyCount);
    }

    private void getAllArtBodOrders() {
        recordsSearchBody = new ArtBondOrdersSearchBody();
//        recordsSearchBody.setFrom(FastSave.getInstance().getLong(FROM_DATE, 0));
//        recordsSearchBody.setTo(FastSave.getInstance().getLong(TO_DATE, 0));
//        recordsSearchBody.setBusinessIds(Arrays.asList(FastSave.getInstance().getString(ARTBOND_ID, "")));
        recordsSearchBody.setArtBondId(FastSave.getInstance().getString(ARTBOND_ID, ""));
//        recordsSearchBody.setProcesses(FastSave.getInstance().getObjectsList(STATUS_FILTER, String.class));
//        recordsSearchBody.setSize(20);
//        recordsSearchBody.setPage(page);

        API.getAllArbondOrders(FastSave.getInstance().getString(ACCESS_TOKEN_LG, ""), recordsSearchBody)
                .enqueue(customCallback.getResponseWithProgress(new CustomCallback.ResponseCallback<List<ArtBondOrderResponse>>() {
                    @Override
                    public void onSuccessful(Call<List<ArtBondOrderResponse>> call, Response<List<ArtBondOrderResponse>> response) {
                        orderssList = response.body();
                        if (orderssList != null && orderssList.size() > 0) {
//                                bussinesName.setText(FastSave.getInstance().getString(BUSSINES_NAME, ""));
                            artBondOrderAdapter.addItems(orderssList);
                            loadingFlag = true;
                        }
                        Log.d(TAG, "onSuccessful: ");
                        FastSave.getInstance().saveBoolean(RECORD_LIST_ACTIVITY, true);
                    }

                    @Override
                    public void onEmpty(Call<List<ArtBondOrderResponse>> call, Response<List<ArtBondOrderResponse>> response) {
                        Toast.makeText(ArtBondOrdersActivity.this, "Список заявок пуст", Toast.LENGTH_SHORT).show();
//                        newRecordListAdapter.hiddenLoading();
                        FastSave.getInstance().saveBoolean(RECORD_LIST_ACTIVITY, true);
                    }
                }));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ArtBondOrdersActivity.this, ChooseArtBondActivity.class));
        finish();
    }


}
