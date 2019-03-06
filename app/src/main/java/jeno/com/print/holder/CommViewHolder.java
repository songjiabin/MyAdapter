package jeno.com.print.holder;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * author : 宋佳
 * time   : 2019/03/05
 * desc   :
 * version: 1.0.0
 */

public class CommViewHolder {


    private SparseArray<View> viewHolderArray = null;
    private int position;
    private View convertView;

    public CommViewHolder(Context context, int position, int layoutId, ViewGroup parent) {
        this.viewHolderArray = new SparseArray<>();
        this.position = position;
        //当第一次创建的时候，创建convertView
        convertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        //将此ViewHolder放入到convertView中
        convertView.setTag(this);
    }

    /**
     * 提供外界调用的静态方法
     *
     * @param context
     * @param convertView
     * @param layouId
     * @param parent
     * @param position
     * @return
     */
    public static CommViewHolder getCommViewHoler(Context context, View convertView, int layouId, ViewGroup parent, int position) {
        if (convertView == null) {
            //当convertView为空的时候，进行创建ViewHolder。
            return new CommViewHolder(context, position, layouId, parent);
        } else {
            //当不为空的时候，复用ViewHolder
            CommViewHolder viewHolder = (CommViewHolder) convertView.getTag();
            viewHolder.position = position;
            return viewHolder;
        }
    }

    /**
     * 通过 id  来获取 View
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = viewHolderArray.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            viewHolderArray.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 第一次创建的时候回创建convertView。 所以要返回这个convertView
     *
     * @return
     */
    public View getConvertView() {
        return convertView;
    }

    /**
     * 设置TextView的 方法
     *
     * @param id
     * @param text
     * @return
     */
    public CommViewHolder setText(int id, String text) {
        TextView view = getView(id);
        view.setText(text);
        return this;
    }

    /**
     * 设置 ImageView的代码
     *
     * @param id
     * @param url
     * @return
     */
    public CommViewHolder setImageUrl(int id, String url) {
        ImageView view = getView(id);
        // 这里得到 设置图片的缓冲的代码
        //如： Glide.with(context)...(imageView,url);这样的代码
        return this;
    }


}
