package com.example.ivan.privatnews.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


import com.example.ivan.privatnews.Model.ExchangeRate;
import com.example.ivan.privatnews.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewRatesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ExchangeRate> currencyExchange = new ArrayList<>();
    private LayoutInflater inflater;
    private Context mContext;
    private int lastPosition;

    public RecyclerViewRatesAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @Override
    public Item onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.cv_exchange_rate, parent, false);
        Log.e("Created holfer", "sf");
        return new Item(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (currencyExchange.get(position).getSaleRate() != null)
            ((Item) holder).tvSellPrice.setText(currencyExchange.get(position).getSaleRate().toString());
        else
            ((Item) holder).tvSellPrice.setText(currencyExchange.get(position).getSaleRateNB().toString());
        ((Item) holder).tvCurrency.setText(currencyExchange.get(position).getCurrency());
        if (currencyExchange.get(position).getPurchaseRate() != null)
            ((Item) holder).tvBuyPrice.setText(currencyExchange.get(position).getPurchaseRate().toString());
        else
            ((Item) holder).tvBuyPrice.setText(currencyExchange.get(position).getPurchaseRateNB().toString());

        Log.e("Binded", currencyExchange.get(position).getCurrency());

        addAnimation(holder, position);
    }


    @Override
    public int getItemCount() {
        return currencyExchange.size();
    }


    public void addCurrencyExchange(ExchangeRate exchangeRate) {
        Log.e("Adapter got", exchangeRate.getCurrency());
        currencyExchange.clear();
        currencyExchange.add(exchangeRate);
        notifyDataSetChanged();
    }

    private void addAnimation(RecyclerView.ViewHolder holder, int position) {
        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;
    }

    static class Item extends RecyclerView.ViewHolder {
        @BindView(R.id.tvSellPrice)
        TextView tvSellPrice;
        @BindView(R.id.tvCurrency)
        TextView tvCurrency;
        @BindView(R.id.tvBuyPrice)
        TextView tvBuyPrice;

        public Item(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
