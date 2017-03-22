package com.example.ivan.myapplication.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Button;
import android.support.v7.widget.RecyclerView;


import com.example.ivan.myapplication.Model.Post;
import com.example.ivan.myapplication.Presenter.Presenter;
import com.example.ivan.myapplication.Presenter.RecyclerViewAdapter;
import com.example.ivan.myapplication.R;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View {
    private Button bShowData;
    private RecyclerView rvData;
    private Presenter presenter;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new Presenter(this);
        recyclerViewAdapter = new RecyclerViewAdapter(this);
        bShowData = (Button) findViewById(R.id.bShowData);
        bShowData.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                presenter.showData();
            }
        });
        rvData = (RecyclerView) findViewById(R.id.rvData);
        rvData.setLayoutManager(new LinearLayoutManager(this));
        rvData.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void changeView(List<Post> data) {
        recyclerViewAdapter.changeDataToShow(data);
    }
}
