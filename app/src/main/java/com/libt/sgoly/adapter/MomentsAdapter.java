package com.libt.sgoly.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.libt.sgoly.AppConstant;
import com.libt.sgoly.R;
import com.libt.sgoly.db.Moments;

import java.util.List;
import java.util.Random;

import static org.litepal.LitePalApplication.getContext;

/**
 * 发现界面动态适配器
 * Created by Administrator on 2017/4/14 0014.
 */

public class MomentsAdapter extends RecyclerView.Adapter<MomentsAdapter.ViewHolder>{
    private Context mContext;

    private List<Moments> moments;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView momentsAvatar;
        TextView momentsNickname;
        TextView momentsContent;
        //TableLayout imageLayout;
        TextView momentsDate;
        ImageView momentsStatus;
        ImageView momentsImage;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            momentsAvatar = (ImageView) view.findViewById(R.id.moments_avatar);
            momentsNickname = (TextView) view.findViewById(R.id.moments_nickname);
            momentsContent = (TextView) view.findViewById(R.id.moments_content);
            //imageLayout= (TableLayout) view.findViewById(R.id.tab_layout);
            momentsDate= (TextView) view.findViewById(R.id.moments_date);
            momentsStatus = (ImageView) view.findViewById(R.id.moments_status);
            momentsImage = (ImageView) view.findViewById(R.id.moments_image);
        }
    }

    public MomentsAdapter(List<Moments> momentsList) {
        moments = momentsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.moments_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.momentsStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=holder.getAdapterPosition();
                Moments moment=moments.get(position);
                if (moment.getStatus()== AppConstant.COMMENTS_STATUS_0){
                    holder.momentsStatus.setSelected(true);
                    moment.setStatus(AppConstant.COMMENTS_STATUS_1);
                }else{
                    holder.momentsStatus.setSelected(false);
                    moment.setStatus(AppConstant.COMMENTS_STATUS_0);
                }

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Moments moment = moments.get(position);
        holder.momentsNickname.setText(moment.getNickname());
        holder.momentsContent.setText(moment.getContent());
        holder.momentsDate.setText(moment.getDate());
        holder.momentsAvatar.setImageResource(moment.getAvatarId());
        holder.momentsImage.setImageResource(moment.getImageId());
        if (moment.getStatus()== AppConstant.COMMENTS_STATUS_0){
            holder.momentsStatus.setSelected(false);
        }else{
            holder.momentsStatus.setSelected(true);
        }
        //int total = new Random().nextInt(moments.size());
        //
        //if (total == 0) {
        //    holder.imageLayout.setVisibility(View.GONE);
        //} else {
        //    holder.imageLayout.removeAllViewsInLayout();
        //    holder.imageLayout.setVisibility(View.VISIBLE);
        //
        //    int ROW = 0;
        //    int mod = total % 3;
        //    if (mod == 0)
        //        ROW = total / 3;
        //    else
        //        ROW = total / 3 + 1;
        //
        //    int k = 0;
        //    for (int i = 0; i < ROW; i++) {
        //        TableRow tableRow = new TableRow(getContext());
        //
        //        for (int j = 0; j < 3; j++) {
        //            if (k < total) {
        //                ImageView iv = new ImageView(mContext);
        //                iv.setImageResource(R.mipmap.ic_launcher);
        //                tableRow.addView(iv);
        //
        //                k++;
        //            }
        //        }
        //
        //        holder.imageLayout.addView(tableRow, new TableLayout.LayoutParams(
        //                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //    }
            //Glide.with(mContext).load(moment.getImageId()).into(holder.fruitImage);
        //}
    }

    @Override
    public int getItemCount() {
        return moments.size();
    }
}
