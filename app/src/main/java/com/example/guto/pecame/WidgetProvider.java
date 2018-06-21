package com.example.guto.pecame;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.guto.pecame.ui.ListaProdutoActivity;
import com.example.guto.pecame.ui.MainActivity;

/**
 * Implementation of App Widget functionality.
 */
public class WidgetProvider extends AppWidgetProvider {
    public static final String UPDATE_MEETING_ACTION = "android.appwidget.action.APPWIDGET_UPDATE";
    public static final String EXTRA_ITEM = "com.example.edockh.EXTRA_ITEM";

    public void onReceive(Context context, Intent intent) {
        AppWidgetManager mgr = AppWidgetManager.getInstance(context);

        if (intent.getAction().equals(UPDATE_MEETING_ACTION)) {
            int appWidgetIds[] = mgr.getAppWidgetIds(new ComponentName(context,WidgetProvider.class));
            Log.e("received", intent.getAction());
            mgr.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.listViewWidget);
        }
        super.onReceive(context, intent);
    }


    ListaProdutoActivity listaProdutoActivity;

    public WidgetProvider() {
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_provider);
        Intent widgetIntent = new Intent(context,WidgetService.class);



        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // update each of the app widgets with the remote adapter
        for(int i = 0; i < appWidgetIds.length; ++i) {
            // Set up the intent that starts the ListViewService, which will
            // provide the views for this collection.
            Intent intent = new Intent(context,WidgetService.class);
            // Add the app widget ID to the intent extras.
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

            // Instantiate the RemoteViews object for the app widget layout.
            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_provider);
            // Set up the RemoteViews object to use a RemoteViews adapter.
            // This adapter connects
            // to a RemoteViewsService  through the specified intent.
            // This is how you populate the data.
            rv.setRemoteAdapter(appWidgetIds[i], R.id.listViewWidget, intent);

            // Trigger listview item click
            Intent startActivityIntent = new Intent(context,MainActivity.class);
            PendingIntent startActivityPendingIntent = PendingIntent.getActivity(context, 0, startActivityIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            rv.setPendingIntentTemplate(R.id.listViewWidget, startActivityPendingIntent);

            // The empty view is displayed when the collection has no items.
            // It should be in the same layout used to instantiate the RemoteViews  object above.
            rv.setEmptyView(R.id.listViewWidget, R.id.appwidget_text);
            // Do additional processing specific to this app widget...
            appWidgetManager.updateAppWidget(appWidgetIds[i], rv);

            super.onUpdate(context, appWidgetManager, appWidgetIds);

//            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

