package jeno.com.print.adapter;

import android.content.Context;

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

public class NowAdapter extends CommAdapter<Bean> {


    public NowAdapter(Context context, List<Bean> listBean) {
        super(context, listBean);
    }

    @Override
    public void convert(int position, CommViewHolder commViewHolder) {
        commViewHolder.setText(R.id.tv_textView, listBean.get(position).getText()).setText(R.id.tv_time, System.currentTimeMillis() + "");
    }

    @Override
    public int getItemlayoutId() {
        return R.layout.item_content;
    }
}
