package com.example.ivan.myapplication.Presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ivan.myapplication.Model.Post;
import com.example.ivan.myapplication.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Post> newsList = new ArrayList<>();
    private LayoutInflater inflater;

    public RecyclerViewAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.card_view, parent, false);
        return new Item(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String date = "" + newsList.get(position).getDate().get(Calendar.YEAR) + "."
                + (newsList.get(position).getDate().get(Calendar.MONTH)+1) + "."
                + newsList.get(position).getDate().get(Calendar.DAY_OF_MONTH);
        ((Item) holder).tvDate.setText(date);
        ((Item) holder).ivPicture.setBackgroundResource(R.drawable.latestnews);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public void changeDataToShow(List<Post> data) {
        newsList.clear();
        newsList.addAll(data);
        notifyDataSetChanged();
    }

    static class Item extends RecyclerView.ViewHolder {

        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.ivPicture)
        ImageView ivPicture;

        Item(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
