package com.example.ivan.privatnews.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.ivan.privatnews.Model.Article;
import com.example.ivan.privatnews.Presenter.App;
import com.example.ivan.privatnews.Presenter.NewsPresenter;
import com.example.ivan.privatnews.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsActivity extends AppCompatActivity implements NewsActivityView {
    @BindView(R.id.rvNews)
    RecyclerView rvNews;
    @BindView(R.id.toolbarNews)
    Toolbar mToolbar;
    private RecyclerViewNewsAdapter recyclerViewNewsAdapter;
    @Inject
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

        newsPresenter.bind(this);
        newsPresenter.getNews();
    }

    @Override
    protected void onDestroy() {
        newsPresenter.unSubscribe();
        super.onDestroy();
    }

    @Override
    public void showNews(List<Article> articles) {
        recyclerViewNewsAdapter.showNews(articles);
    }
}
