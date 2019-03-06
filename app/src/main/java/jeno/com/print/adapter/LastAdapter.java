package jeno.com.print.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import jeno.com.print.R;
import jeno.com.print.bean.Bean;

/**
 * author : 宋佳
 * time   : 2019/03/05
 * desc   :
 * version: 1.0.0
 */

public class LastAdapter extends BaseAdapter {

    private Context context;
    private List<Bean> beanList;

    public LastAdapter(Context context, List<Bean> beanList) {
        this.context = context;
        this.beanList = beanList;
    }

    @Override
    public int getCount() {
        return beanList.size();
    }

    @Override
    public Object getItem(int position) {
        return beanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_content, parent,false);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_imageView);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_textView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(beanList.get(position).getText());


        return convertView;
    }


    private class ViewHolder {
        private TextView textView;
        private ImageView imageView;
    }


}
