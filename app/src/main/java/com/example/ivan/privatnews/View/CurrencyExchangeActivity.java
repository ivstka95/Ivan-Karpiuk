package com.example.ivan.privatnews.View;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;

import com.example.ivan.privatnews.Model.ExchangeRate;
import com.example.ivan.privatnews.Presenter.CurrencyExchangePresenter;
import com.example.ivan.privatnews.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by karthik on 1/1/17.
 */

public class CurrencyExchangeActivity extends AppCompatActivity implements CurrencyExchangeView {

    private RecyclerView rvExchangeRates;
    private CurrencyExchangePresenter currencyExchangePresenter;
    private Toolbar toolbar;
    private RecyclerViewRatesAdapter recyclerViewRatesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar_withtabs_stays);
        recyclerViewRatesAdapter = new RecyclerViewRatesAdapter(this);
        rvExchangeRates = (RecyclerView) findViewById(R.id.rvExchangeRates);
        rvExchangeRates.setLayoutManager(new LinearLayoutManager(this));
        rvExchangeRates.setItemAnimator(new DefaultItemAnimator());
        rvExchangeRates.setAdapter(recyclerViewRatesAdapter);

        currencyExchangePresenter = new CurrencyExchangePresenter(this);

        toolbar = (Toolbar) findViewById(R.id.toolbarExchangeRates);
        toolbar.setTitle("Currency exchange");
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(CurrencyExchangeActivity.this);
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                Date currentDate = new Date();
                currencyExchangePresenter.setDate(dateFormat.format(currentDate));
                CalendarView cvDate = new CalendarView(CurrencyExchangeActivity.this);
                cvDate.setMaxDate(currentDate.getTime());
                cvDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                        currencyExchangePresenter.setDate("" + dayOfMonth + "." + (month + 1) + "." + year);
                    }
                });
                builder.setView(cvDate)
                        .setCancelable(false)
                        .setPositiveButton("Get", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        currencyExchangePresenter.getExchangeRates();
                                    }
                                }
                        );
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        currencyExchangePresenter.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showExchangeRate(ExchangeRate exchangeRate) {
        Log.e("View got", exchangeRate.getCurrency());
        recyclerViewRatesAdapter.addCurrencyExchange(exchangeRate);
    }

    @Override
    public void setToolBarTitle(String toolBarTitle) {
        toolbar.setTitle(toolBarTitle);
    }
}