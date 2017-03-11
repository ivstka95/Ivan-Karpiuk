package com.example.ivan.ivankarpiuk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rvTestString)
    RecyclerView rvTestString;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        recyclerViewAdapter = new RecyclerViewAdapter(this);
        rvTestString.setLayoutManager(new LinearLayoutManager(this));
        rvTestString.setItemAnimator(new DefaultItemAnimator());
        rvTestString.setAdapter(recyclerViewAdapter);
        int type =1;
        for (int i = 0; i<100; i++){
            recyclerViewAdapter.addCurrencyEntity(type);
            type*=-1;
        }
    }
}
