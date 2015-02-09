package com.straphq.strapkit.core.ui;

import android.content.Intent;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import com.straphq.strapkit.framework.util.Callback;
import com.straphq.strapkit.framework.util.StrapKitPlugin;

import com.straphq.strapkit.core.ui.page.activity.StrapKitBaseActivity;
import com.straphq.strapkit.core.ui.page.util.PageBroker;

/**
 * Created by martinza on 2/2/15.
 */
public class Page extends StrapKitPlugin {

    private static final String TAG = Page.class.getSimpleName();

    private static final String SHOW = "show";
    private static final String HIDE = "hide";

    public boolean execute(String action, String rawArgs, Callback callback) throws JSONException {
        Log.d("Page", "action: " + action);

        JSONObject jsonObject = null;
        switch (action) {
            case SHOW:
                Log.d(TAG, "Show page: " + rawArgs);
                jsonObject = new JSONObject(rawArgs);
                String backgroundColor = jsonObject.getString("backgroundColor");
                if (backgroundColor.equals("null")) {
                    backgroundColor = null;
                }
                Integer pageId = jsonObject.getInt("id");
                Intent intent = new Intent(getContext(), StrapKitBaseActivity.class);
                intent.putExtra(StrapKitBaseActivity.ARGS_BACKGROUND_COLOR, backgroundColor);
                intent.putExtra(StrapKitBaseActivity.ARGS_ID, pageId);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);
                JSONObject response = new JSONObject();
                response.put("message", "Page: " + jsonObject.get("id") + ", has been shown");
                callback.success(response.toString());
                break;
            case HIDE:
                Log.d(TAG, "Hide page: " + rawArgs);
                jsonObject = new JSONObject(rawArgs);
                PageBroker.getInstance().hideActivity(jsonObject.getInt("id"));
                callback.success("Page: " + jsonObject.get("id") + ", has been hidden");
                break;
            default:
                callback.error("No action is available for: " + action);
                break;
        }
        return true;
        //this.
        // Launch Activity
        // context.startActivity(new Intent(context, StrapKitBaseActivity.class));
    }

}
