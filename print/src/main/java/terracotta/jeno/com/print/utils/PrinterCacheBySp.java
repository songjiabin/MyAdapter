package terracotta.jeno.com.print.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * author : 宋佳
 * time   : 2018/02/28
 * desc   : 保存打印机设置信息
 * version: 1.0.0
 */

public class PrinterCacheBySp {

    private static final String PRINTER_CACHE_KEY_ADDRESS = "PRINTER_CACHE_KEY_ADDRESS";//连接打印机的地址
    private static final String PRINTER_CACHE_KEY_NAME = "PRINTER_CACHE_KEY_NAME"; //连接打印机的名字
    private static final String PRINTER_CACHE_KEY_PAGE_SIZE = "PRINTER_CACHE_KEY_PAGE_SIZE"; //一次打印的纸的数量

    private static final String FILE_NAME = "PRINT_INFO";
    private SharedPreferences sp;


    public PrinterCacheBySp(Context context) {
        sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }


    public void setPrinterAddress(String address) {
        sp.edit().putString(PRINTER_CACHE_KEY_ADDRESS, address).apply();
    }

    public String getPrinterAddress() {
        return sp.getString(PRINTER_CACHE_KEY_ADDRESS, "");
    }


    public void setPrinterName(String name) {
        sp.edit().putString(PRINTER_CACHE_KEY_NAME, name).apply();
    }

    public String getPrinterName() {
        return sp.getString(PRINTER_CACHE_KEY_NAME, "");
    }


    public void setPrinterPageSize(int size) {
        sp.edit().putInt(PRINTER_CACHE_KEY_PAGE_SIZE, size).apply();

    }

    public int getPrinterPageSize() {
        return sp.getInt(PRINTER_CACHE_KEY_PAGE_SIZE, 1);
    }


}
