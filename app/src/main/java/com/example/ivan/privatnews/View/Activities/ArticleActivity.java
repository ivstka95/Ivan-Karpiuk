package com.example.ivan.privatnews.View.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ivan.privatnews.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by karthik on 1/1/17.
 */

public class ArticleActivity extends AppCompatActivity {
    @BindView(R.id.ivImage)
    ImageView ivImage;
    @BindView(R.id.tvDescription)
    TextView tvDescription;
    @BindView(R.id.tvDate)
    TextView tvDate;
    @BindView(R.id.tvAuthor)
    TextView tvAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ButterKnife.bind(this);
        Intent data = getIntent();
        Picasso.with(this)
                .load(data.getStringExtra("Image url"))
                .fit()
                .into(ivImage);
        tvDescription.setText(data.getStringExtra("Description"));
        tvAuthor.setText(data.getStringExtra("Author"));
        tvDate.setText(data.getStringExtra("Date"));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(data.getStringExtra("Title"));
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }
}