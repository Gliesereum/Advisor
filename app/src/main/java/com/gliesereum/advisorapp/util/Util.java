package com.gliesereum.advisorapp.util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;

import com.chaos.view.PinView;
import com.gliesereum.advisorapp.BuildConfig;
import com.gliesereum.advisorapp.R;
import com.gliesereum.advisorapp.network.APIInterface;
import com.gliesereum.advisorapp.network.CustomCallback;
import com.gliesereum.advisorapp.ui.ArtBondOrdersActivity;
import com.gliesereum.advisorapp.ui.ChooseArtBondActivity;
import com.gliesereum.advisorapp.ui.InvestorListActivity;
import com.gliesereum.advisorapp.ui.LoginActivity;
import com.labters.lottiealertdialoglibrary.ClickListener;
import com.labters.lottiealertdialoglibrary.DialogTypes;
import com.labters.lottiealertdialoglibrary.LottieAlertDialog;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static com.gliesereum.advisorapp.util.Constants.ACCESS_EXPIRATION_DATE_LG;
import static com.gliesereum.advisorapp.util.Constants.ACCESS_TOKEN_LG;
import static com.gliesereum.advisorapp.util.Constants.ACCESS_TOKEN_WITHOUT_BEARER_LG;
import static com.gliesereum.advisorapp.util.Constants.IS_LOGIN_LG;
import static com.gliesereum.advisorapp.util.Constants.REFRESH_EXPIRATION_DATE_LG;
import static com.gliesereum.advisorapp.util.Constants.REFRESH_TOKEN_LG;
import static com.gliesereum.advisorapp.util.Constants.USER_AVATAR_LG;
import static com.gliesereum.advisorapp.util.Constants.USER_ID_LG;
import static com.gliesereum.advisorapp.util.Constants.USER_NAME_LG;
import static com.gliesereum.advisorapp.util.Constants.USER_SECOND_NAME_LG;


public class Util {
    private Activity activity;
    private Toolbar toolbar;
    private int identifier;
    private Drawer result;

    private PinView codeView;
    private PinView newPinCode;
    private PinView confirmPinCode;
    private String pin = "";
    private String newPin = "";
    private String confirmPin = "";
    private Button createPinBtn;

    private APIInterface API;
    private CustomCallback customCallback;
    private LottieAlertDialog alertDialog;


    public Util(Activity activity, Toolbar toolbar, int identifier) {
        this.activity = activity;
        this.toolbar = toolbar;
        this.identifier = identifier;
    }

    public Util() {
    }

//    public static boolean checkCarWashWorkTime(AllCarWashResponse carWash) {
//        String dayOfWeek = "";
//        Calendar calendar = Calendar.getInstance();
//        int day = calendar.get(Calendar.DAY_OF_WEEK);
//        switch (day) {
//            case Calendar.MONDAY:
//                dayOfWeek = "MONDAY";
//                break;
//            case Calendar.TUESDAY:
//                dayOfWeek = "TUESDAY";
//                break;
//            case Calendar.WEDNESDAY:
//                dayOfWeek = "WEDNESDAY";
//                break;
//            case Calendar.THURSDAY:
//                dayOfWeek = "THURSDAY";
//                break;
//            case Calendar.FRIDAY:
//                dayOfWeek = "FRIDAY";
//                break;
//            case Calendar.SATURDAY:
//                dayOfWeek = "SATURDAY";
//                break;
//            case Calendar.SUNDAY:
//                dayOfWeek = "SUNDAY";
//                break;
//        }
//
//        for (int i = 0; i < carWash.getWorkTimes().size(); i++) {
//            if (carWash.getWorkTimes().get(i).getDayOfWeek().equals(dayOfWeek) && carWash.getWorkTimes().get(i).isIsWork()) {
//                if (carWash.getWorkTimes().get(i).getFrom() < (System.currentTimeMillis() + (carWash.getTimeZone() * 60000)) && carWash.getWorkTimes().get(i).getTo() > (System.currentTimeMillis() + (carWash.getTimeZone() * 60000))) {
//                    Log.d("test_log", "car wash work");
//                    return true;
//                }
//            }
//        }
//        Log.d("test_log", "car wash not work: ");
//        return false;
//    }

    public void addNavigation() {
        new DrawerBuilder().withActivity(activity).build();
        PrimaryDrawerItem orders = new PrimaryDrawerItem().withName("Заявки").withIdentifier(1).withTag("orders").withIconTintingEnabled(true);
        SecondaryDrawerItem clients = new SecondaryDrawerItem().withName("Интестора").withIdentifier(2).withTag("clients").withSelectable(false).withIconTintingEnabled(true);
        SecondaryDrawerItem chooseBusiness = new SecondaryDrawerItem().withName("Выбор АртБонда").withIdentifier(4).withTag("chooseBusiness").withSelectable(false).withIconTintingEnabled(true);
        SecondaryDrawerItem logoutItem = new SecondaryDrawerItem().withName("Выйти").withIdentifier(3).withSelectable(false).withTag("logout").withSelectable(false).withIconTintingEnabled(true);
        SecondaryDrawerItem versionItem = new SecondaryDrawerItem().withName("v" + BuildConfig.VERSION_NAME + " beta").withIdentifier(5).withSelectable(false).withTag("version").withSelectable(false);

        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                Picasso.get().load(uri).placeholder(placeholder).transform(new CircleTransform()).into(imageView);
            }

            @Override
            public void cancel(ImageView imageView) {
                Picasso.get().cancelRequest(imageView);
            }
        });

        ProfileDrawerItem profileDrawerItem = new ProfileDrawerItem();
        profileDrawerItem.withName(FastSave.getInstance().getString(USER_NAME_LG, "") + " " + FastSave.getInstance().getString(USER_SECOND_NAME_LG, ""));

        if (FastSave.getInstance().getString(USER_AVATAR_LG, "").equals("")) {
            profileDrawerItem.withIcon(activity.getResources().getDrawable(R.drawable.ic_logo_r_v4));
        } else {
            profileDrawerItem.withIcon(FastSave.getInstance().getString(USER_AVATAR_LG, ""));
        }

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(activity)
                .withTextColorRes(R.color.white)
                .withHeaderBackground(R.drawable.lg_noimage_cover_bg)
                .withSelectionListEnabledForSingleProfile(false)
                .withCompactStyle(true)
                .addProfiles(profileDrawerItem)
                .build();

        DrawerBuilder drawerBuilder = new DrawerBuilder();
        drawerBuilder.withAccountHeader(headerResult);
        drawerBuilder.withActivity(activity);
        drawerBuilder.withToolbar(toolbar);
        drawerBuilder.withActionBarDrawerToggle(true);
        drawerBuilder.withSelectedItem(identifier);
        drawerBuilder.addDrawerItems(
                orders,
                clients,
                chooseBusiness,
                new DividerDrawerItem(),
                logoutItem
        );
        result = drawerBuilder.build();
        drawerBuilder.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                switch (drawerItem.getTag().toString()) {
                    case "chooseBusiness":
                        activity.startActivity(new Intent(activity.getApplicationContext(), ChooseArtBondActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        result.closeDrawer();
                        break;
                    case "orders":
                        activity.startActivity(new Intent(activity.getApplicationContext(), ArtBondOrdersActivity.class));
                        result.closeDrawer();
                        break;
                    case "clients":
//                        FastSave.getInstance().saveBoolean(ONLY_CLIENT, true);
                        activity.startActivity(new Intent(activity.getApplicationContext(), InvestorListActivity.class));
                        result.closeDrawer();
                        break;
                    case "logout":
                        if (result.isDrawerOpen()) {
                            result.closeDrawer();
                        }
                        alertDialog = new LottieAlertDialog.Builder(activity, DialogTypes.TYPE_QUESTION)
                                .setTitle("Выход")
                                .setDescription("Вы действительно хотите выйти из Coupler Worker?")
                                .setPositiveText("Да")
                                .setNegativeText("Нет")
                                .setPositiveTextColor(activity.getResources().getColor(R.color.white))
                                .setNegativeTextColor(activity.getResources().getColor(R.color.white))
                                .setPositiveButtonColor(activity.getResources().getColor(R.color.questionColor))
                                .setPositiveListener(new ClickListener() {
                                    @Override
                                    public void onClick(@NotNull LottieAlertDialog lottieAlertDialog) {
//                                        deleteRegistrationToken();
                                        FastSave.getInstance().deleteValue(IS_LOGIN_LG);
                                        FastSave.getInstance().deleteValue(USER_ID_LG);
                                        FastSave.getInstance().deleteValue(ACCESS_TOKEN_LG);
                                        FastSave.getInstance().deleteValue(ACCESS_TOKEN_WITHOUT_BEARER_LG);
                                        FastSave.getInstance().deleteValue(REFRESH_TOKEN_LG);
                                        FastSave.getInstance().deleteValue(ACCESS_EXPIRATION_DATE_LG);
                                        FastSave.getInstance().deleteValue(REFRESH_EXPIRATION_DATE_LG);
                                        activity.startActivity(new Intent(activity.getApplicationContext(), LoginActivity.class));
                                        activity.finish();
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
                }

                return true;
            }
        });


//        result.addItem(new DividerDrawerItem());
//        if (FastSave.getInstance().getBoolean(IS_LOGIN_LG, false)) {
//        result.addItem(logoutItem);
//        } else {
//            result.addItem(loginItem);
//        }
        result.addItem(versionItem);
    }

//    private void deleteRegistrationToken() {
//        API = APIClient.getClient().create(APIInterface.class);
//        customCallback = new CustomCallback(activity.getApplicationContext(), activity);
//        API.deleteRegistrationToken(FastSave.getInstance().getString(ACCESS_TOKEN_LG, ""), FastSave.getInstance().getString(FIREBASE_TOKEN, ""))
//                .enqueue(customCallback.getResponse(new CustomCallback.ResponseCallback<RegistrationTokenDeleteResponse>() {
//                    @Override
//                    public void onSuccessful(Call<RegistrationTokenDeleteResponse> call, Response<RegistrationTokenDeleteResponse> response) {
//
//                    }
//
//                    @Override
//                    public void onEmpty(Call<RegistrationTokenDeleteResponse> call, Response<RegistrationTokenDeleteResponse> response) {
//
//                    }
//                }));
//
//    }

    public static boolean checkExpirationToken(Long localDateTime) {
        if (localDateTime > System.currentTimeMillis()) {
            return true;
        } else {
            return false;
        }
    }

    public static String getStringTimeTrue(Long millisecond) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(new Date(millisecond));

    }

    public static String getStringTime(Long millisecond) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        return format.format(new Date(millisecond));

    }

    public static String getStringDateTrue(Long millisecond) {
//        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
//        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
//        calendar.setTimeInMillis(millisecond);
//        return format.format(calendar.getTime());
        SimpleDateFormat format = new SimpleDateFormat("dd:MM");
        return format.format(new Date(millisecond));

    }

    public static String getStringFullDateTrue(Long millisecond) {
//        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
//        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
//        calendar.setTimeInMillis(millisecond);
//        return format.format(calendar.getTime());
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        return format.format(new Date(millisecond));

    }

    public static String getStringDate(Long millisecond) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.YYYY");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        return format.format(new Date(millisecond));
    }

    public static long startOfDay(Long time) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.setTimeInMillis(time);
        cal.set(Calendar.HOUR_OF_DAY, 0); //set hours to zero
        cal.set(Calendar.MINUTE, 0); // set minutes to zero
        cal.set(Calendar.SECOND, 0); //set seconds to zero
        return cal.getTimeInMillis();
    }

    public static long endOfDay(Long time) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.setTimeInMillis(time);
        cal.set(Calendar.HOUR_OF_DAY, 23); //set hours to zero
        cal.set(Calendar.MINUTE, 59); // set minutes to zero
        cal.set(Calendar.SECOND, 59); //set seconds to zero
        return cal.getTimeInMillis();
    }

}
