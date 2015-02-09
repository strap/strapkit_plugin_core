package com.straphq.strapkit.core.ui.page.activity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;
import com.straphq.strapkit.framework.StrapKitApplication;
import com.straphq.teststrapkit.mysampleapp.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StrapKitBaseActivity extends Activity  {

    private static final String TAG = StrapKitBaseActivity.class.getSimpleName();

    public static final String ARGS_VIEW_DEFINITIONS = "args_view_definitions";
    public static final String ARGS_BACKGROUND_COLOR = "args_background_color";
    public static final String ARGS_ID = "args_id";

    public Integer pageId;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strapkit_base);

        Serializable serializable = getIntent().getSerializableExtra(ARGS_VIEW_DEFINITIONS);
        if (serializable == null) {
            //finish();
        } else {
            /*ArrayList<StrapKitBaseView> views = (ArrayList<StrapKitBaseView>) serializable;
            for (StrapKitBaseView view : views) {
                view.initialize(this);
            }*/
        }

        String backgroundColor = getIntent().getStringExtra(ARGS_BACKGROUND_COLOR);
        if (backgroundColor != null) {
            View view = findViewById(R.id.base_view);
            view.setBackgroundColor(Color.parseColor(backgroundColor));
        }

        pageId = getIntent().getIntExtra(ARGS_ID, -1);

        PageBroker pageBroker = PageBroker.getInstance();

        pageBroker.storeActivity(this);

        //((StrapKitApplication) getApplication()).storeActivityInCache(this);
    }
}
