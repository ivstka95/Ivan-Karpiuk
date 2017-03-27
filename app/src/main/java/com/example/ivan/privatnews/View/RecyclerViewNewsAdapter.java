package com.example.ivan.privatnews.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ivan.privatnews.Model.Article;
import com.example.ivan.privatnews.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Article> articleList = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;
    private int lastPosition;

    public RecyclerViewNewsAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.cv_article, parent, false);
        Log.e("a", "created");
        return new Post(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.e("a", "binded" + position);
        ((Post) holder).tvTitle.setText(articleList.get(position).getTitle());
        ((Post) holder).tvDescription.setText(articleList.get(position).getDescription());
        ((Post) holder).tvDate.setText(articleList.get(position).getPublishedAt());
        Picasso.with(context)
                .load(articleList.get(position).getUrlToImage())
                .fit()
                .into(((Post) holder).ivPicture);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent articleIntent = new Intent(context, ArticleActivity.class);
                articleIntent.putExtra("Title", articleList.get(position).getTitle());
                articleIntent.putExtra("Description", articleList.get(position).getDescription());
                articleIntent.putExtra("Author", articleList.get(position).getAuthor());
                articleIntent.putExtra("Date", articleList.get(position).getPublishedAt());
                articleIntent.putExtra("Image url", articleList.get(position).getUrlToImage());
                context.startActivity(articleIntent);
            }
        });
        addAnimation(holder, position);
    }


    @Override
    public int getItemCount() {
        return articleList.size();
    }


    private void addAnimation(RecyclerView.ViewHolder holder, int position) {
        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;
    }

    public void showNews(List<Article> articles) {
        articleList.clear();
        articleList.addAll(articles);
        notifyItemInserted(getItemCount());
    }

    static class Post extends RecyclerView.ViewHolder {
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvDescription)
        TextView tvDescription;
        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.ivPicture)
        ImageView ivPicture;

        public Post(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
