package com.example.ivan.ivankarpiuk;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Post> mExchangeEntityArrayList = new ArrayList<>();
    private LayoutInflater inflater;
    private Context mContext;
    private int lastPosition;

    public RecyclerViewAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if (viewType > 0) {
            v = inflater.inflate(R.layout.card_view_type1, parent, false);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "Item clecked", Toast.LENGTH_SHORT).show();
                }
            });
            return new ViewHolder1(v);
        } else {
            v = inflater.inflate(R.layout.card_view_type2, parent, false);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "Item clecked", Toast.LENGTH_SHORT).show();
                }
            });
            return new ViewHolder2(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewHolder1) {
            ((ViewHolder1) holder).tvContent.setText("someText " + position);
            ((ViewHolder1) holder).ivPicture.setBackgroundResource(R.drawable.sport);
        } else {
            ((ViewHolder2) holder).tvContent.setText("someText " + position);
            ((ViewHolder2) holder).tvDate.setText("someDate " + position);
            ((ViewHolder2) holder).ivPicture.setBackgroundResource(R.drawable.sport);
        }

        addAnimation(holder, position);
    }

    @Override
    public int getItemViewType(int position) {
        return mExchangeEntityArrayList.get(position).type;
    }


    @Override
    public int getItemCount() {
        return mExchangeEntityArrayList.size();
    }


    private void addAnimation(RecyclerView.ViewHolder holder, int position) {
        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;
    }

    public void addCurrencyEntity(int viewType) {
        mExchangeEntityArrayList.add(new Post(viewType));
        notifyItemInserted(getItemCount());
    }

    static class ViewHolder2 extends RecyclerView.ViewHolder {
        @BindView(R.id.tvContent)
        TextView tvContent;
        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.ivPicture)
        ImageView ivPicture;

        ViewHolder2(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder1 extends RecyclerView.ViewHolder {
        @BindView(R.id.tvContent)
        TextView tvContent;
        @BindView(R.id.ivPicture)
        ImageView ivPicture;

        public ViewHolder1(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
