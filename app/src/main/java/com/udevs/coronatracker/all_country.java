package com.udevs.coronatracker;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

public class all_country extends AppCompatActivity {

    public static ListView list;
    public static countryAdapter adapter;
    public static ArrayList<singleRow> items;
    public static ArrayList<singleRow> array_sort;
    public static SwipeRefreshLayout reloadData;
    Toolbar tb;
    int textlength = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_country);
        list = (ListView) findViewById(R.id.list);
        tb = (Toolbar) findViewById(R.id.toolbar);
        reloadData = (SwipeRefreshLayout) findViewById(R.id.reloadData);
        setSupportActionBar(tb);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        reloadData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                DataCountry dataCountry = new DataCountry(all_country.this);
                dataCountry.execute();
            }
        });


        DataCountry dataCountry = new DataCountry(all_country.this);
        dataCountry.execute();

        items = new ArrayList<>();
        array_sort = new ArrayList<>();

        adapter = new countryAdapter(all_country.this, items);

        list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (list.getChildAt(0) != null) {
                    reloadData.setEnabled(list.getFirstVisiblePosition() == 0 && list.getChildAt(0).getTop() == 0);
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tools, menu);

        MenuItem mSearch = menu.findItem(R.id.action_search);

        final SearchView searchCountry = (SearchView) mSearch.getActionView();
        searchCountry.setQueryHint("Search country");

        searchCountry.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                textlength = searchCountry.getQuery().length();
                array_sort.clear();
                for (int i = 0; i < items.size(); i++) {
                    if (textlength <= items.get(i).getCountry().length()) {
                        Log.d("ertyyy", items.get(i).getCountry().toLowerCase().trim());
                        if (items.get(i).getCountry().toLowerCase().trim().contains(
                                searchCountry.getQuery().toString().toLowerCase().trim())) {
                            array_sort.add(items.get(i));
                        }
                    }
                }
                adapter = new countryAdapter(all_country.this, array_sort);
                list.setAdapter(adapter);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
