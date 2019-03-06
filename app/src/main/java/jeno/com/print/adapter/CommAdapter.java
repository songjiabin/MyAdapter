package jeno.com.print.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import jeno.com.print.holder.CommViewHolder;

/**
 * author : 宋佳
 * time   : 2019/03/06
 * desc   :
 * version: 1.0.0
 */

public abstract class CommAdapter<T> extends BaseAdapter {

    protected List<T> listBean;
    protected Context context;


    public CommAdapter(Context context, List<T> listBean) {
        this.listBean = listBean;
        this.context = context;

    }

    @Override
    public int getCount() {
        return listBean.size();
    }

    @Override
    public Object getItem(int position) {
        return listBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //固定代码  找到指定的ViewHolder
        CommViewHolder commViewHolder = CommViewHolder.getCommViewHoler(context, convertView, getItemlayoutId(), parent, position);

        //逻辑代码 让 自类去实现
        convert(position, commViewHolder);

        //并返回comvertView
        return commViewHolder.getConvertView();
    }


    public abstract void convert(int position, CommViewHolder commViewHolder);


    //继承的时候只需要提供 布局id
    public abstract int getItemlayoutId();


}
