package terracotta.jeno.com.print;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import terracotta.jeno.com.print.bean.PrinterLibBean;
import terracotta.jeno.com.print.bluetooth.ui.PrinterBlueToothSettingActivity;

/**
 * author : 宋佳
 * time   : 2018/05/09
 * desc   : 打印设置界面
 * version: 1.0.0
 */

public class PrintSettingActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btn_ip_print;
    private Button btn_blue_tooth_print;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printer_setting);
        initView();
        initListener();
    }

    private void initView() {
        btn_ip_print = (Button) findViewById(R.id.btn_ip_print);
        btn_blue_tooth_print = (Button) findViewById(R.id.btn_blue_tooth_print);
    }

    private void initListener() {
        btn_ip_print.setOnClickListener(this);
        btn_blue_tooth_print.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_blue_tooth_print) {
            Intent intent = new Intent(this, PrinterBlueToothSettingActivity.class);
            PrinterLibBean printerLibBean = (PrinterLibBean) getIntent().getSerializableExtra(PrinterBlueToothSettingActivity.KEY_PRINT_BEAN);
            intent.putExtra(PrinterBlueToothSettingActivity.KEY_PRINT_BEAN, printerLibBean);
            startActivity(intent);
            this.finish();
        }

    }
}
