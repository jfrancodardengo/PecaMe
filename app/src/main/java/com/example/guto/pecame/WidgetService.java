package com.example.guto.pecame;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class WidgetService extends RemoteViewsService{
    public WidgetService() {
    }

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
//        int appWidgetId = intent.getIntExtra("appWidgetId", 0);
        return new ListProvider(this.getApplicationContext(), intent);
    }
}
