package com.example.benjious.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.benjious.myapplication.R;
import com.example.benjious.myapplication.bean.DouBanBean.PersonBean;
import com.example.benjious.myapplication.util.ImageLoaderUtils;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

import java.util.List;


/**
 * Created by Benjious on 2017/4/21.
 */

public class MovieDetailAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<PersonBean> mData;
    private Context mContext;
    private OnMovieItemClickListener mOnItemClickListener;

    public MovieDetailAdapter(Context context) {
        this.mContext = context;
    }

    /**
     * 设置数据
     **/
    public void setData(List<PersonBean> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }



    public PersonBean getItem(int position) {
        return mData.get(position);
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View news = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_detail_person, parent, false);
            ItemViewHolder vh = new ItemViewHolder(news);
            return vh;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {

            ImageLoaderUtils.display(mContext, ((ItemViewHolder) holder).mPersonImg, mData.get(position).getAvatars().getMedium());
            ((ItemViewHolder) holder).mPersonName.setText(mData.get(position).getName());
            ((ItemViewHolder) holder).mPersonType.setText(mData.get(position).getType());
            ViewHelper.setScaleX(holder.itemView, 0.8f);
            ViewHelper.setScaleY(holder.itemView, 0.8f);
            ViewPropertyAnimator.animate(holder.itemView).scaleX(1).setDuration(350).setInterpolator(new OvershootInterpolator()).start();
            ViewPropertyAnimator.animate(holder.itemView).scaleY(1).setDuration(350).setInterpolator(new OvershootInterpolator()).start();
        }
    }

    @Override
    public int getItemCount() {

            return mData.size() ;

    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mPersonName;
        TextView mPersonType;
       ImageView mPersonImg;

        public ItemViewHolder(View v) {
            super(v);
            mPersonImg = (ImageView) v.findViewById(R.id.iv_avatar);
            mPersonName = (TextView) v.findViewById(R.id.person_name);
            mPersonType = (TextView) v.findViewById(R.id.person_type);
            v.setOnClickListener(this);//给item注册点击监听 必须写
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, this.getPosition());
            }
        }
    }


    //给RecyclerView注册点击回调接口
    public interface OnMovieItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnMovieItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

}
