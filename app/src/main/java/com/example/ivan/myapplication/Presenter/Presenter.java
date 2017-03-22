package com.example.ivan.myapplication.Presenter;

import com.example.ivan.myapplication.Model.Data;
import com.example.ivan.myapplication.Model.Post;
import com.example.ivan.myapplication.View.View;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import rx.Observable;

/**
 * Created by Ivan on 22.03.2017.
 */

public class Presenter {
    View view;
    Data data;

    public Presenter(View view) {
        this.view = view;
        this.data = new Data();
    }

    public void showData() {
        view.changeView(getDataToShow());
    }

    public List<Post> getDataToShow() {
        List<Post> dataToShow = new ArrayList<Post>();
        Calendar currentDate = new GregorianCalendar();
        if (data.getNews() == null)
            fillModelWithData();
        Observable.from(data.getNews())
                .filter(post -> (currentDate.get(Calendar.DAY_OF_YEAR) - post.getDate().get(Calendar.DAY_OF_YEAR)) < 8
                        && (currentDate.get(Calendar.DAY_OF_YEAR) - post.getDate().get(Calendar.DAY_OF_YEAR)) > 0)
                .take(10)
                .subscribe(post -> dataToShow.add(post));
        return dataToShow;
    }

    public void fillModelWithData() {
        data = new Data();
        ArrayList<Post> news = new ArrayList<Post>();
        for (int i = 0; i < 1000; i++) {
            news.add(new Post());
        }
        data.setNews(news);
    }
}
