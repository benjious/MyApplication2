package com.example.benjious.myapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.benjious.myapplication.R;
import com.example.benjious.myapplication.bean.DouBanBean.PersonBean;
import com.example.benjious.myapplication.bean.DouBanBean.SubjectBean;
import com.example.benjious.myapplication.util.ImageLoaderUtils;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

import java.util.List;
import java.util.Random;


/**
 * Created by Benjious on 2017/4/17.
 */

public class DouBanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    private List<SubjectBean> mData;
    private boolean mShowFooter = true;
    private Context mContext;
    private OnMovieItemClickListener mOnItemClickListener;

    public DouBanAdapter(Context context) {
        this.mContext = context;
    }

    /**
     * 设置数据
     **/
    public void setData(List<SubjectBean> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (!mShowFooter) {
            return TYPE_ITEM;
        }
        if (position + 1 == getItemCount()) {//到了底部
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public SubjectBean getItem(int position) {
        return mData.get(position);
    }

    //根据传递过来的值 控制是否显示加载更多布局
    public void isShowFooter(boolean b) {
        this.mShowFooter = b;
    }

    public boolean isShowFooter() {
        return this.mShowFooter;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View news = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_movie, parent, false);
            ItemViewHolder vh = new ItemViewHolder(news);
            return vh;
        } else {
            View footer = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_first_footer, null);
            footer.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(footer);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            SubjectBean dataBean = mData.get(position);
            ((ItemViewHolder) holder).mMovName.setText(dataBean.getTitle());
            ((ItemViewHolder) holder).mMovDao.setText(revertPersonName(dataBean.getDirectors()));
            ((ItemViewHolder) holder).mMovZhu.setText(revertPersonName(dataBean.getCasts()));
            ((ItemViewHolder) holder).mMovType.setText(revertType(dataBean.getGenres()));
            ((ItemViewHolder) holder).mMovRate.setText(String.valueOf(dataBean.getRating().getAverage()));
            ((ItemViewHolder) holder).lineColor.setBackgroundColor(randomColor());
            ImageLoaderUtils.display(mContext, ((ItemViewHolder) holder).mMovie_img, dataBean.getImages().getLarge());

            ViewHelper.setScaleX(holder.itemView, 0.8f);
            ViewHelper.setScaleY(holder.itemView, 0.8f);
            ViewPropertyAnimator.animate(holder.itemView).scaleX(1).setDuration(350).setInterpolator(new OvershootInterpolator()).start();
            ViewPropertyAnimator.animate(holder.itemView).scaleY(1).setDuration(350).setInterpolator(new OvershootInterpolator()).start();
        }
    }

    @Override
    public int getItemCount() {
        int begin = mShowFooter ? 1 : 0;
        if (mData == null) {
            return begin;
        } else {
            return mData.size() + begin;
        }

    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View lineColor;
        TextView mMovName;
        ImageView mMovie_img;
        TextView mMovDao;
        TextView mMovZhu;
        TextView mMovType;
        TextView mMovRate;

        public ItemViewHolder(View v) {
            super(v);
//            mTitle = (TextView) v.findViewById(R.id.tvTitle);
//            mDesc = (TextView) v.findViewById(R.id.tvDesc);
//            mNewsImg = (ImageView) v.findViewById(R.id.ivNews);
            mMovie_img = (ImageView) v.findViewById(R.id.iv_one_photo);
            mMovZhu = (TextView) v.findViewById(R.id.tv_one_casts);
            mMovDao = (TextView) v.findViewById(R.id.tv_one_directors);
            mMovType = (TextView) v.findViewById(R.id.tv_one_genres);
            mMovRate = (TextView) v.findViewById(R.id.tv_one_rating_rate);
            mMovName = (TextView) v.findViewById(R.id.tv_one_title);
            lineColor = v.findViewById(R.id.view_color);
            v.setOnClickListener(this);//给item注册点击监听 必须写
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, this.getPosition());
            }
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    //给RecyclerView注册点击回调接口
    public interface OnMovieItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnMovieItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    private String revertPersonName(List<PersonBean> date) {
        StringBuilder stringBuffer = new StringBuilder();
        if (date == null) {
            return null;
        }
        for (PersonBean person : date) {
            stringBuffer.append(person.getName()).append("/");
        }
        return stringBuffer.toString();

    }

    private String revertType(List<String> types) {
        StringBuilder sb = new StringBuilder();
        for (String type : types) {
            sb.append(type).append("/");
        }
        return sb.toString();
    }
    /**
     * 随机颜色
     */
    public static int randomColor() {
        Random random = new Random();
        int red = random.nextInt(150) + 50;//50-199
        int green = random.nextInt(150) + 50;//50-199
        int blue = random.nextInt(150) + 50;//50-199
        return Color.rgb(red, green, blue);
    }
}