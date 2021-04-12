package com.jcnetwork.android.jctestapp1.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        // Return remote view factory here
        return new WidgetDataProvider(this, intent);
    }
}
