package com.example.ivan.privatnews.View.Activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.ivan.privatnews.Model.ExchangeRate;
import com.example.ivan.privatnews.Presenter.App;
import com.example.ivan.privatnews.Presenter.CurrencyExchangePresenter;
import com.example.ivan.privatnews.R;
import com.example.ivan.privatnews.View.Views.CurrencyExchangeView;
import com.example.ivan.privatnews.View.Adapters.RecyclerViewRatesAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by karthik on 1/1/17.
 */

public class CurrencyExchangeActivity extends MvpAppCompatActivity implements CurrencyExchangeView {
    @BindView(R.id.rvExchangeRates)
    RecyclerView rvExchangeRates;
    @BindView(R.id.toolbarExchangeRates)
    Toolbar toolbar;
    @InjectPresenter
    CurrencyExchangePresenter currencyExchangePresenter;

    private RecyclerViewRatesAdapter recyclerViewRatesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar_withtabs_stays);
        ButterKnife.bind(this);
        App.getComponent().injectCurrencyExchange(this);
        recyclerViewRatesAdapter = new RecyclerViewRatesAdapter(this);
        rvExchangeRates.setLayoutManager(new LinearLayoutManager(this));
        rvExchangeRates.setItemAnimator(new DefaultItemAnimator());
        rvExchangeRates.setAdapter(recyclerViewRatesAdapter);

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
                                        currencyExchangePresenter.getData();
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
    public void showData(Object exchangeRate) {
        recyclerViewRatesAdapter.addCurrencyExchange((ExchangeRate) exchangeRate);
    }

    @Override
    public void setToolBarTitle(String toolBarTitle) {
        toolbar.setTitle(toolBarTitle);
    }
}