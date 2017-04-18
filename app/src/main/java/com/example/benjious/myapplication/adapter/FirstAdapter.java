package com.example.benjious.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.benjious.myapplication.R;
import com.example.benjious.myapplication.bean.DataBean;
import com.example.benjious.myapplication.util.ImageLoaderUtils;

import java.util.List;


/**
 * adapter 里面放着一个List<Datebean>,存储着元数据,就是bean
 * Created by Benjious on 2017/1/17.
 */

public class FirstAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private List<DataBean> mDataBeen;
    //是否到达底部的标志位
    private boolean mShowFooter = true;
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;


    public FirstAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<DataBean> dataBeen) {
        this.mDataBeen = dataBeen;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        //没到底部
        if (!mShowFooter) {
            return TYPE_ITEM;
        }

        if (position + 1 == getItemCount()) {
            //到了底部
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public DataBean getItem(int position) {
        return mDataBeen.get(position);
    }

    //根据传递过来的值,控制是否显示加载更多布局
    public void isShowFooter(boolean b) {
        this.mShowFooter = b;
    }

    public boolean isShowFooter() {

        return this.mShowFooter;
    }

    public void setShowFooter(boolean showFooter) {
        mShowFooter = showFooter;
    }

    //返回一个ViewHolder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_first_news, parent, false);
            ItemViewHolder itemViewHolder = new ItemViewHolder(view);
            return itemViewHolder;
        } else {
            View footer = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_first_footer, parent, false);
            footer.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(footer);
        }
    }

    //当绑定一个ViewHolder后的操作
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            DataBean dataBean = mDataBeen.get(position);
            ((ItemViewHolder) holder).mTitle.setText(dataBean.getTitle());
            ((ItemViewHolder) holder).mDesc.setText(dataBean.getDigest());
            ImageLoaderUtils.display(mContext, ((ItemViewHolder) holder).mImageView, dataBean.getImgsrc());
        }
    }


    /**
     * 为什么这里还要 +1
     * 这里加1是因为 :
     * if (newState == RecyclerView.SCROLL_STATE_IDLE && mLastVisibleItemPostion + 1 == mFirstAdapter.getItemCount() && mFirstAdapter.isShowFooter()) {
     * mFirstPresenter.loadData(type, pageIndex + Urls.PAZE_SIZE);
     * }
     * 这段判断加载的代码，要是没到底部，那么adapter里的数就是list.size()的数，当到了底部就+1，不让他继续加载
     * 同时，当list的值为null(没数据),根据判断，getItemCount（）要返回 1
     *
     * @return
     */
    @Override
    public int getItemCount() {
        //true（到达了底部 或是初始化第一次的时候）: 1           false(加载失败时) : 0
        int begin = mShowFooter ? 1 : 0;
        if (mDataBeen == null) {
            return begin;
        } else {
            return mDataBeen.size() + begin;
        }
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitle;
        private TextView mDesc;
        private ImageView mImageView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.item_head_title);
            mDesc = (TextView) itemView.findViewById(R.id.item_content_desc);
            mImageView = (ImageView) itemView.findViewById(R.id.item_image);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view, this.getPosition());
            }
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    //给RecycleView注册回掉接口
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

}
