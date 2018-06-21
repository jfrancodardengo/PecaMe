package com.example.guto.pecame;

import android.app.LauncherActivity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.guto.pecame.R;
import com.example.guto.pecame.modelo.ProdutoModelo;
import com.example.guto.pecame.ui.ListaProdutoActivity;

import java.util.ArrayList;
import java.util.List;

public class ListProvider implements RemoteViewsService.RemoteViewsFactory {
    private List<ProdutoModelo> listItemList;
    private Context context;
    ListaProdutoActivity listaProdutoActivity;

    public ListProvider(Context context, Intent intent) {
        this.context = context;
    }

    private void populateListItem() {
        this.listItemList = listaProdutoActivity.getProdutos();
    }

    // Initialize the data set.

    @Override
    public void onCreate() {
        // In onCreate() you set up any connections / cursors to your data source. Heavy lifting,
        // for example downloading or creating content etc, should be deferred to onDataSetChanged()
        // or getViewAt(). Taking more than 20 seconds in this call will result in an ANR.
        listItemList = new ArrayList<>();
    }

    @Override
    public void onDataSetChanged() {
        // Fetching JSON data from server and add them to records arraylist

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return listItemList.size();
    }


    // Given the position (index) of a WidgetItem in the array, use the item's text value in
    // combination with the app widget item XML file to construct a RemoteViews object.
    @Override
    public RemoteViews getViewAt(int position) {
        // position will always range from 0 to getCount() - 1.
        // Construct a RemoteViews item based on the app widget item XML file, and set the
        // text based on the position.

        RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.widget_item);
        populateListItem();

        // feed row
        String data = listaProdutoActivity.receberProdutos(listItemList);
        remoteView.setTextViewText(R.id.item,data);
        // end feed row

        // that is set on the collection view in ListViewWidgetProvider.
        Bundle extras = new Bundle();

        extras.putInt(WidgetProvider.EXTRA_ITEM, position);

        Intent fillInIntent = new Intent();
        fillInIntent.putExtra("homescreen_meeting",data);
        fillInIntent.putExtras(extras);

        // Make it possible to distinguish the individual on-click
        // action of a given item
        remoteView.setOnClickFillInIntent(R.id.item, fillInIntent);

        // Return the RemoteViews object.

        return remoteView;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
