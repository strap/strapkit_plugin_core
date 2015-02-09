package com.straphq.strapkit.core.ui.page.util;

import java.util.HashMap;

/**
 * Created by martinza on 2/3/15.
 */
public class PageBroker {
    private static PageBroker ourInstance = new PageBroker();

    public static PageBroker getInstance() {
        return ourInstance;
    }

    private HashMap<Integer, StrapKitBaseActivity> baseActivities = new HashMap<>();

    private PageBroker() {

    }

    public void storeActivity(StrapKitBaseActivity activity) {
        baseActivities.put(activity.pageId, activity);
    }

    public boolean hideActivity(Integer pageId) {
        if (baseActivities.containsKey(pageId)) {
            baseActivities.get(pageId).finish();
            baseActivities.remove(pageId);
            return true;
        } else {
            return false;
        }
    }
}
