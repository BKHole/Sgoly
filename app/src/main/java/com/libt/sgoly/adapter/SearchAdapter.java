package com.libt.sgoly.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.libt.sgoly.R;
import com.libt.sgoly.activity.FruitDetailActivity;
import com.libt.sgoly.db.Fruit;

import java.util.List;


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{

    private Context mContext;
    private List<Fruit> mFruitList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(View view) {
            super(view);
            fruitImage = (ImageView) view.findViewById(R.id.item_search_iv_icon);
            fruitName = (TextView) view.findViewById(R.id.item_search_tv_title);
        }
    }

    public SearchAdapter(List<Fruit> fruitList) {
        mFruitList = fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_bean_list, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        //holder.cardView.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        int position = holder.getAdapterPosition();
        //        Fruit fruit = mFruitList.get(position);
        //        Intent intent = new Intent(mContext, FruitDetailActivity.class);
        //        intent.putExtra(FruitDetailActivity.FRUIT_NAME, fruit.getName());
        //        //intent.putExtra(FruitDetailActivity.FRUIT_IMAGE_ID, fruit.getImageId());
        //        intent.putExtra(FruitDetailActivity.FRUIT_IMAGE_ID, fruit.getPicture().getFileUrl());
        //        mContext.startActivity(intent);
        //    }
        //});
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitName.setText(fruit.getName());
        //Glide.with(mContext).load(fruit.getImageId()).into(holder.fruitImage);
        Glide.with(mContext).load(fruit.getPicture().getFileUrl()).into(holder.fruitImage);
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}
