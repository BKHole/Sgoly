package com.libt.sgoly.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.libt.sgoly.R;
import com.libt.sgoly.db.Moments;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 发现界面动态适配器
 * Created by Administrator on 2017/4/14 0014.
 */

public class MomentsAdapter extends RecyclerView.Adapter<MomentsAdapter.ViewHolder> {
    private Context mContext;
    private List<Moments> moments;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView avatar; // 头像
        TextView author; // 作者
        ImageView sex;//性别
        TextView location;//地理位置
        ImageView img; // 图片
        TextView content; // 内容
        TextView createdTime; // 创建时间

        public ViewHolder(View view) {
            super(view);
            avatar = (CircleImageView) view.findViewById(R.id.iv_avatar);
            author = (TextView) view.findViewById(R.id.tv_author);
            sex= (ImageView) view.findViewById(R.id.img_sex);
            location= (TextView) view.findViewById(R.id.label_address);
            img = (ImageView) view.findViewById(R.id.iv_img);
            content = (TextView) view.findViewById(R.id.tv_content);
            createdTime = (TextView) view.findViewById(R.id.tv_created_time);
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
        //holder.momentsStatus.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        int position=holder.getAdapterPosition();
        //        Moments moment=moments.get(position);
        //        if (moment.getStatus()== AppConstant.COMMENTS_STATUS_0){
        //            holder.momentsStatus.setSelected(true);
        //            moment.setStatus(AppConstant.COMMENTS_STATUS_1);
        //        }else{
        //            holder.momentsStatus.setSelected(false);
        //            moment.setStatus(AppConstant.COMMENTS_STATUS_0);
        //        }
        //
        //    }
        //});
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Moments moment = moments.get(position);
        if (moment.getAuthor().getAvatar() != null) {
            Glide.with(mContext).load(moment.getAuthor().getAvatar().getFileUrl()).into(holder.avatar);
        } else {
            Glide.with(mContext).load(R.drawable.default_avatar).into(holder.avatar);
        }
        if (moment.getAuthor().getNickname() != null) {
            holder.author.setText(moment.getAuthor().getNickname());
        } else {
            holder.author.setText(moment.getAuthor().getUsername());
        }
        //if (moment.getAuthor().getSex()){
        //    holder.sex.setImageResource(R.drawable.sex_boy);
        //}else{
        //    holder.sex.setImageResource(R.drawable.sex_girl);
        //}
        if (moment.getImg() != null) {
            holder.img.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(moment.getImg().getFileUrl()).into(holder.img);
        } else {
            holder.img.setVisibility(View.GONE);
        }
        holder.location.setText(moment.getLocation());
        holder.content.setText(moment.getContent());
        holder.createdTime.setText(moment.getCreatedAt());

        //if (moment.getStatus()== AppConstant.COMMENTS_STATUS_0){
        //    holder.momentsStatus.setSelected(false);
        //}else{
        //    holder.momentsStatus.setSelected(true);
        //}
    }

    @Override
    public int getItemCount() {
        return moments.size();
    }
}
