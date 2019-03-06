package jeno.com.print;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jeno.com.print.adapter.NowAdapter;
import jeno.com.print.bean.Bean;
import jeno.com.print.holder.CommViewHolder;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        listView = (ListView) findViewById(R.id.listView);
        NowAdapter adapter=new NowAdapter(this,getListData()){
            @Override
            public void convert(int position, CommViewHolder commViewHolder) {
                commViewHolder.setText(R.id.tv_textView, listBean.get(position).getText()).setText(R.id.tv_time, System.currentTimeMillis() + "");
            }

            @Override
            public int getItemlayoutId() {
                return R.layout.item_content;
            }
        };
        listView.setAdapter(adapter);

    }

    private List<Bean> getListData() {
        ArrayList<Bean> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Bean bean = new Bean();
            bean.setText(new Random().nextInt(100000) + "");
            list.add(bean);
        }
        return list;
    }
}
