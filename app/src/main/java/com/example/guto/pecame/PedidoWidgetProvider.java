package com.example.guto.pecame;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.guto.pecame.modelo.ProdutoModelo;
import com.example.guto.pecame.ui.ListaProdutoActivity;
import com.example.guto.pecame.ui.MainActivity;

import java.util.List;

/**
 * Implementation of App Widget functionality.
 */
public class PedidoWidgetProvider extends AppWidgetProvider {

        ListaProdutoActivity listaProdutoActivity = new ListaProdutoActivity();

//    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
//                                int appWidgetId,List<ProdutoModelo> selecionados) {
//
//        List<ProdutoModelo> produtos = selecionados;
//
//        String resultado = this.receberProdutos(produtos);
//
//        context.getString(R.string.appwidget_text);
//        // Construct the RemoteViews object
//        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.pedido_widget_provider);
//
//        Intent intent = new Intent(context, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
//
//        views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);
//
//        // Instruct the widget manager to update the widget
//        appWidgetManager.updateAppWidget(appWidgetId, views);
//    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
//            updateAppWidget(context, appWidgetManager, appWidgetId,mSelecionados);

            String resultado = listaProdutoActivity.receberProdutos();
            // Construct the RemoteViews object
            Toast.makeText(context,"RESULTADO: "+ resultado, Toast.LENGTH_SHORT).show();

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.pedido_widget_provider);
            views.setTextViewText(R.id.appwidget_text,resultado);

            Intent intent = new Intent(context, PedidoWidgetProvider.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
//            intent.putExtra("PRODUTOS",resultado);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,0);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
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

    private String receberProdutos(List<ProdutoModelo> mSelecionados){
        String descricao;
        String preco;
        String resultado = "";
        for(ProdutoModelo produto : mSelecionados) {
            descricao = produto.getmDescProduto();
            preco = produto.getmPreco();
            resultado += descricao + preco + "\n";
        }
        return resultado;
    }
}

