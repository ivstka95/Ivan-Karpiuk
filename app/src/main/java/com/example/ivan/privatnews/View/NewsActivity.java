package com.example.ivan.privatnews.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.ivan.privatnews.Model.Article;
import com.example.ivan.privatnews.Presenter.App;
import com.example.ivan.privatnews.Presenter.NewsPresenter;
import com.example.ivan.privatnews.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsActivity extends MvpAppCompatActivity implements NewsActivityView {
    @BindView(R.id.rvNews)
    RecyclerView rvNews;
    @BindView(R.id.toolbarNews)
    Toolbar mToolbar;
    private RecyclerViewNewsAdapter recyclerViewNewsAdapter;
    @InjectPresenter
    NewsPresenter newsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        App.getComponent().injectNews(this);
        mToolbar.setTitle("ESPN sport news");
        setSupportActionBar(mToolbar);
        recyclerViewNewsAdapter = new RecyclerViewNewsAdapter(this);
        rvNews.setLayoutManager(new LinearLayoutManager(this));
        rvNews.setItemAnimator(new DefaultItemAnimator());
        rvNews.setAdapter(recyclerViewNewsAdapter);

        newsPresenter.getData();
    }

    @Override
    public void showData(Object articles) {
        recyclerViewNewsAdapter.showNews((List<Article>) articles);
    }

}
