package com.gliesereum.advisorapp.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gliesereum.advisorapp.R;
import com.gliesereum.advisorapp.adapter.CommentAdapter;
import com.gliesereum.advisorapp.network.APIClient;
import com.gliesereum.advisorapp.network.APIInterface;
import com.gliesereum.advisorapp.network.CustomCallback;
import com.gliesereum.advisorapp.network.json.order.ArtBondOrderResponse;
import com.gliesereum.advisorapp.util.FastSave;
import com.gliesereum.advisorapp.util.Util;
import com.gohn.nativedialog.ButtonType;
import com.gohn.nativedialog.NDialog;
import com.labters.lottiealertdialoglibrary.ClickListener;
import com.labters.lottiealertdialoglibrary.DialogTypes;
import com.labters.lottiealertdialoglibrary.LottieAlertDialog;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.armcha.elasticview.ElasticView;
import retrofit2.Call;
import retrofit2.Response;

import static com.gliesereum.advisorapp.util.Constants.ACCESS_TOKEN_LG;
import static com.gliesereum.advisorapp.util.Constants.ARTBOND_ORDER;

public class SingleArtBondOrderActivity extends AppCompatActivity implements View.OnClickListener {

    private APIInterface API;
    private CustomCallback customCallback;
    private ArtBondOrderResponse artBondOrder;
    private Button progressBtn;
    private Button doneBtn;
    private Button cancelBtn;
    private Button connectBtn;
    private Button addComment;
    private EditText commentText;
    private RecyclerView recyclerView;
    private CommentAdapter commentAdapter;
    private TextView stockCount;
    private TextView firstName;
    private TextView middleName;
    private TextView investorType;
    private TextView sumInvestment;
    private TextView create;
    private TextView stateType;
    private ElasticView elasticView;
    private ImageView artBondImageView;
    private TextView amountCollected;
    private TextView artStockCount;
    private TextView artBondName;
    private TextView stockPrice;
    private ImageView statusImg;
    private LottieAlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_art_bond_order);
        initData();
        initView();
        fillActivity();
    }

    private void initData() {
        API = APIClient.getClient().create(APIInterface.class);
        customCallback = new CustomCallback(this, this);
        artBondOrder = FastSave.getInstance().getObject(ARTBOND_ORDER, ArtBondOrderResponse.class);
    }

    private void initView() {
        progressBtn = findViewById(R.id.progressBtn);
        doneBtn = findViewById(R.id.doneBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        connectBtn = findViewById(R.id.connectBtn);
        addComment = findViewById(R.id.openCommentDialog);
        progressBtn.setOnClickListener(this);
        doneBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        connectBtn.setOnClickListener(this);
        addComment.setOnClickListener(this);
        stockCount = findViewById(R.id.stockCount);
        firstName = findViewById(R.id.firstName);
        middleName = findViewById(R.id.middleName);
        investorType = findViewById(R.id.investorType);
        sumInvestment = findViewById(R.id.sumInvestment);
        create = findViewById(R.id.create);
        stateType = findViewById(R.id.stateType);
        elasticView = findViewById(R.id.elasticView);
        artBondImageView = findViewById(R.id.artBondImageView);
        amountCollected = findViewById(R.id.amountCollected);
        artStockCount = findViewById(R.id.artStockCount);
        artBondName = findViewById(R.id.artBondName);
        stockPrice = findViewById(R.id.stockPrice);
        statusImg = findViewById(R.id.statusImg);
    }

    private void fillActivity() {
        fillArtBondInfo();
        setActionButtons();
        firstName.setText(artBondOrder.getUser().getFirstName());
        middleName.setText(artBondOrder.getUser().getMiddleName());
        sumInvestment.setText(String.valueOf(artBondOrder.getSumInvestment()));
        stockCount.setText(String.valueOf(artBondOrder.getStockCount()));
        create.setText(Util.getStringDate(artBondOrder.getCreate()));

        switch (artBondOrder.getCustomer().getInvestorType()) {
            case "FUND":
                investorType.setText("Фонд");
                break;
            case "FAMILY_OFFICE":
                investorType.setText("Family Office");
                break;
            case "HNWI":
                investorType.setText("HNWI");
                break;
            case "OTHER":
                investorType.setText("Прочее");
                break;
            default:
                investorType.setText("Неизвестно");
                break;
        }

        if (artBondOrder.getStateType().equals("REFUSED")) {
            stateType.setText("Отклонена");
            statusImg.setImageResource(R.drawable.ic_outline_block_24px);
        } else {
            switch (artBondOrder.getStateType()) {
                case "REQUEST":
                    stateType.setText("В ожидании рассмотрения");
                    statusImg.setImageResource(R.drawable.ic_material_icons_timer_24px);
                    break;
                case "IN_PROCESS":
                    stateType.setText("В процессе рассмотрения");
                    statusImg.setImageResource(R.drawable.ic_outline_pause_circle_outline_24px);
                    break;
                case "COMPLETED":
                    stateType.setText("Одобрена");
                    statusImg.setImageResource(R.drawable.ic_outline_check_circle_outline_24px);
                    break;
                default:
                    stateType.setText("");
                    statusImg.setImageResource(R.drawable.ic_outline_block_24px);
                    break;
            }
        }


    }

    private void fillArtBondInfo() {
        if (!artBondOrder.getArtBond().getImages().isEmpty()) {
            Picasso.get().load(artBondOrder.getArtBond().getImages().get(0).getUrl()).centerCrop().fit().into(artBondImageView);
        } else {
            Picasso.get().load(R.drawable.lg_noimage_cover_bg).centerCrop().fit().into(artBondImageView);
        }
        if (artBondOrder.getArtBond().getName() != null) {
            artBondName.setText(artBondOrder.getArtBond().getName());
        }
        amountCollected.setText(String.valueOf(artBondOrder.getArtBond().getAmountCollected()));
        stockPrice.setText(String.valueOf(artBondOrder.getArtBond().getStockPrice()));
        artStockCount.setText(String.valueOf(artBondOrder.getArtBond().getStockCount()));
    }

    private void setActionButtons() {
        if (artBondOrder.getStateType().equals("COMPLETED") || artBondOrder.getStateType().equals("REFUSED")) {
            progressBtn.setEnabled(false);
            progressBtn.setVisibility(View.GONE);
            doneBtn.setVisibility(View.GONE);
            cancelBtn.setVisibility(View.GONE);
            doneBtn.setEnabled(false);
            cancelBtn.setEnabled(false);
        }
        if (artBondOrder.getStateType().equals("IN_PROCESS")) {
            doneBtn.setVisibility(View.VISIBLE);
            progressBtn.setVisibility(View.GONE);
            doneBtn.setEnabled(true);
            progressBtn.setEnabled(false);
        }
        if (artBondOrder.getStateType().equals("REQUEST")) {
            progressBtn.setVisibility(View.VISIBLE);
            doneBtn.setVisibility(View.GONE);
            progressBtn.setEnabled(true);
            doneBtn.setEnabled(false);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.progressBtn:
                changeArtBondOrderStatus("IN_PROCESS");
                break;
            case R.id.doneBtn:
                changeArtBondOrderStatus("COMPLETED");
                break;
            case R.id.cancelBtn:
                alertDialog = new LottieAlertDialog.Builder(SingleArtBondOrderActivity.this, DialogTypes.TYPE_QUESTION)
                        .setTitle("Отклонить?")
                        .setDescription("Вы действительно хотите отклонить заявку?")
                        .setPositiveText("Да")
                        .setNegativeText("Нет")
                        .setPositiveTextColor(getResources().getColor(R.color.white))
                        .setNegativeTextColor(getResources().getColor(R.color.white))
                        .setPositiveButtonColor(getResources().getColor(R.color.questionColor))
                        .setPositiveListener(new ClickListener() {
                            @Override
                            public void onClick(@NotNull LottieAlertDialog lottieAlertDialog) {
                                changeArtBondOrderStatus("REFUSED");
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
                break;
            case R.id.connectBtn:
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:+" + artBondOrder.getUser().getPhone()));
                startActivity(callIntent);
                break;
            case R.id.openCommentDialog:
                NDialog commentDialog = new NDialog(SingleArtBondOrderActivity.this, ButtonType.NO_BUTTON);
                commentDialog.setCustomView(R.layout.comment_dialog);
                List<View> childViews = commentDialog.getCustomViewChildren();
                for (View childView : childViews) {
                    switch (childView.getId()) {
                        case R.id.recyclerView:
                            recyclerView = childView.findViewById(R.id.recyclerView);
                            recyclerView.setLayoutManager(new GridLayoutManager(SingleArtBondOrderActivity.this, 1));
                            commentAdapter = new CommentAdapter();
                            commentAdapter.endLessScrolled(recyclerView);
                            recyclerView.setAdapter(commentAdapter);
                            commentAdapter.addItems(artBondOrder.getComments());
                            break;
                        case R.id.commentText:
                            commentText = childView.findViewById(R.id.commentText);
                            break;
                        case R.id.addComment:
                            Button addComment = childView.findViewById(R.id.addComment);
                            addComment.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if (!commentText.getText().toString().equals("")) {
                                        API.addComment(FastSave.getInstance().getString(ACCESS_TOKEN_LG, ""), commentText.getText().toString(), artBondOrder.getId())
                                                .enqueue(customCallback.getResponse(new CustomCallback.ResponseCallback<ArtBondOrderResponse>() {
                                                    @Override
                                                    public void onSuccessful(Call<ArtBondOrderResponse> call, Response<ArtBondOrderResponse> response) {
                                                        commentDialog.dismiss();
                                                        artBondOrder.setComments(response.body().getComments());
                                                        FastSave.getInstance().saveObject(ARTBOND_ORDER, artBondOrder);
                                                        startActivity(new Intent(SingleArtBondOrderActivity.this, SingleArtBondOrderActivity.class));
                                                        finish();
                                                    }

                                                    @Override
                                                    public void onEmpty(Call<ArtBondOrderResponse> call, Response<ArtBondOrderResponse> response) {

                                                    }
                                                }));
                                    } else {
                                        Toast.makeText(SingleArtBondOrderActivity.this, "Вы не ввули текст комментария", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                            break;
                    }
                }
                commentDialog.show();
                break;
        }
    }

    private void changeArtBondOrderStatus(String state) {
        API.changeArtBondOrderStatus(FastSave.getInstance().getString(ACCESS_TOKEN_LG, ""), state, artBondOrder.getId())
                .enqueue(customCallback.getResponse(new CustomCallback.ResponseCallback<ArtBondOrderResponse>() {
                    @Override
                    public void onSuccessful(Call<ArtBondOrderResponse> call, Response<ArtBondOrderResponse> response) {
                        artBondOrder.setStateType(response.body().getStateType());
                        FastSave.getInstance().saveObject(ARTBOND_ORDER, artBondOrder);

                        startActivity(new Intent(SingleArtBondOrderActivity.this, SingleArtBondOrderActivity.class));
                        finish();
                    }

                    @Override
                    public void onEmpty(Call<ArtBondOrderResponse> call, Response<ArtBondOrderResponse> response) {
                        Toast.makeText(SingleArtBondOrderActivity.this, "FAIL", Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SingleArtBondOrderActivity.this, ArtBondOrdersActivity.class));
        finish();
    }
}
