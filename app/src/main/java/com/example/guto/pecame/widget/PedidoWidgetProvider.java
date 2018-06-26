package com.example.guto.pecame.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.widget.RemoteViews;

import com.example.guto.pecame.R;
import com.example.guto.pecame.ui.ListaProdutoActivity;

/**
 * Implementation of App Widget functionality.
 */
public class PedidoWidgetProvider extends AppWidgetProvider {

    public static void updateAppWidget(Context context,  ListaProdutoActivity activity) {

        String resultado = activity.receberProdutos();
        ComponentName componentName = new ComponentName(context, PedidoWidgetProvider.class);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int []appWidgetIds = appWidgetManager.getAppWidgetIds(componentName);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.pedido_widget_provider);

        views.setTextViewText(R.id.appwidget_text, resultado);

        for (int appWidgetId : appWidgetIds) {
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

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

