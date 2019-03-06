package jeno.com.print.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import jeno.com.print.R;
import jeno.com.print.bean.Bean;
import jeno.com.print.holder.CommViewHolder;

/**
 * author : 宋佳
 * time   : 2019/03/06
 * desc   :
 * version: 1.0.0
 */

public class NewAdapter extends BaseAdapter {


    private Context context;
    private List<Bean> beanList;

    public NewAdapter(Context context, List<Bean> beanList) {
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

        CommViewHolder holder = CommViewHolder.getCommViewHoler(context, convertView, R.layout.item_content, parent, position);
        TextView textView = (TextView) holder.getView(R.id.tv_textView);
        textView.setText(beanList.get(position).getText());


        return holder.getConvertView();
    }
}
