package terracotta.jeno.com.print.wifi;

import android.annotation.SuppressLint;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * author : 宋佳
 * time   : 2018/05/07
 * desc   :
 * version: 1.0.0
 */

public class Pos {
    //定义编码方式
    private static String encoding = null;

    private Socket sock = null;
    // 通过socket流进行读写
    private OutputStream socketOut = null;
    private OutputStreamWriter writer = null;
    private static int printerSize = 0;
    /**
     * 打印纸一行最大的字节 58mm
     */
    private static final int LINE_BYTE_SIZE = 32;

    /**
     * 打印纸一行最大的字节 80mm
     */
    private static final int LINE_BYTE_SIZE_80 = 48;


    /**
     * 打印三列时，第一列汉字最多显示几个文字
     */
    private static final int LEFT_TEXT_MAX_LENGTH = 7;


    /**
     * 打印三列时，中间一列的中心线距离打印纸左侧的距离
     */
    private static final int LEFT_LENGTH = 18;

    /**
     * 打印三列时，中间一列的中心线距离打印纸右侧的距离
     */
    private static final int RIGHT_LENGTH = 18;

    /**
     * 打印三列时，中间一列的中心线距离打印纸右侧的距离
     */
    private static final int RIGHT_LENGTH_80 = 20;



    /**
     * 初始化Pos实例
     *
     * @param ip       打印机IP
     * @param port     打印机端口号
     * @param encoding 编码
     * @throws IOException
     */
    public Pos(String ip, int port, String encoding) throws IOException {
        sock = new Socket(ip, port);
        if (sock.isConnected()) {
            Log.d("MainActivity", "连接成功"); //连接成功
        }
        socketOut = new DataOutputStream(sock.getOutputStream());
        this.encoding = encoding;
        writer = new OutputStreamWriter(socketOut, encoding);
    }

    /**
     * 关闭IO流和Socket
     *
     * @throws IOException
     */
    public void closeIOAndSocket() throws IOException {
        writer.close();
        socketOut.close();
        sock.close();
    }

    /**
     * 打印二维码
     *
     * @param qrData 二维码的内容
     * @throws IOException
     */
    public void qrCode(String qrData) throws IOException {
        int moduleSize = 8;
        int length = qrData.getBytes(encoding).length;

        //打印二维码矩阵
        writer.write(0x1D);// init
        writer.write("(k");// adjust height of barcode
        writer.write(length + 3); // pl
        writer.write(0); // ph
        writer.write(49); // cn
        writer.write(80); // fn
        writer.write(48); //
        writer.write(qrData);

        writer.write(0x1D);
        writer.write("(k");
        writer.write(3);
        writer.write(0);
        writer.write(49);
        writer.write(69);
        writer.write(48);

        writer.write(0x1D);
        writer.write("(k");
        writer.write(3);
        writer.write(0);
        writer.write(49);
        writer.write(67);
        writer.write(moduleSize);

        writer.write(0x1D);
        writer.write("(k");
        writer.write(3); // pl
        writer.write(0); // ph
        writer.write(49); // cn
        writer.write(81); // fn
        writer.write(48); // m

        writer.flush();

    }

    /**
     * 进纸并全部切割
     *
     * @return
     * @throws IOException
     */
    public void feedAndCut() throws IOException {
        writer.write(0x1D);
        writer.write(86);
        writer.write(65);
        //    writer.write(0);
        //切纸前走纸多少
        writer.write(100);
        writer.flush();

        //另外一种切纸的方式
        //    byte[] bytes = {29, 86, 0};
        //    socketOut.write(bytes);
    }

    /**
     * 打印换行
     *
     * @return length 需要打印的空行数
     * @throws IOException
     */
    public void printLine(int lineNum) throws IOException {
        for (int i = 0; i < lineNum; i++) {
            writer.write("\n");
        }
        writer.flush();
    }

    /**
     * 打印换行(只换一行)
     *
     * @throws IOException
     */
    public void printLine() throws IOException {
        writer.write("\n");
        writer.flush();
    }

    /**
     * 打印空白(一个Tab的位置，约4个汉字)
     *
     * @param length 需要打印空白的长度,
     * @throws IOException
     */
    public void printTabSpace(int length) throws IOException {
        for (int i = 0; i < length; i++) {
            writer.write("\t");
        }
        writer.flush();
    }

    /**
     * 打印空白（一个汉字的位置）
     *
     * @param length 需要打印空白的长度,
     * @throws IOException
     */
    public void printWordSpace(int length) throws IOException {
        for (int i = 0; i < length; i++) {
            writer.write(" ");
        }
        writer.flush();
    }

    /**
     * 打印位置调整
     *
     * @param position 打印位置 0：居左(默认) 1：居中 2：居右
     * @throws IOException
     */
    public void printLocation(int position) throws IOException {
        writer.write(0x1B);
        writer.write(97);
        writer.write(position);
        writer.flush();
    }

    /**
     * 绝对打印位置
     *
     * @throws IOException
     */
    public void printLocation(int light, int weight) throws IOException {
        writer.write(0x1B);
        writer.write(0x24);
        writer.write(light);
        writer.write(weight);
        writer.flush();
    }

    /**
     * 打印文字
     *
     * @param text
     * @throws IOException
     */
    public void printText(String text) throws IOException {
        String s = text;
        byte[] content = s.getBytes("gbk");
        socketOut.write(content);
        socketOut.flush();
    }

    /**
     * 新起一行，打印文字
     *
     * @param text
     * @throws IOException
     */
    public void printTextNewLine(String text) throws IOException {
        //换行
        writer.write("\n");
        writer.flush();

        String s = text;
        byte[] content = s.getBytes("gbk");
        socketOut.write(content);
        socketOut.flush();
    }

    /**
     * 初始化打印机
     *
     * @throws IOException
     */
    public void initPos() throws IOException {
        writer.write(0x1B);
        writer.write(0x40);
        writer.flush();
    }

    /**
     * 加粗
     *
     * @param flag false为不加粗
     * @return
     * @throws IOException
     */
    public void bold(boolean flag) throws IOException {
        if (flag) {
            //常规粗细
            writer.write(0x1B);
            writer.write(69);
            writer.write(0xF);
            writer.flush();
        } else {
            //加粗
            writer.write(0x1B);
            writer.write(69);
            writer.write(0);
            writer.flush();
        }
    }


    /**
     * 打印两列
     *
     * @param leftText  左侧文字
     * @param rightText 右侧文字
     * @return
     */
    @SuppressLint("NewApi")
    public String printTwoData(String leftText, String rightText) {
        StringBuilder sb = new StringBuilder();
        int leftTextLength = getBytesLength(leftText);
        int rightTextLength = getBytesLength(rightText);
        sb.append(leftText);
        int marginBetweenMiddleAndRight;
        // 计算两侧文字中间的空格
        if (printerSize == 0) {
            marginBetweenMiddleAndRight = LINE_BYTE_SIZE - leftTextLength - rightTextLength;
        } else {
            marginBetweenMiddleAndRight = LINE_BYTE_SIZE_80 - leftTextLength - rightTextLength;
        }
        for (int i = 0; i < marginBetweenMiddleAndRight; i++) {
            sb.append(" ");
        }
        sb.append(rightText);
        return sb.toString();
    }


    /**
     * 打印三列
     *
     * @param leftText   左侧文字
     * @param middleText 中间文字
     * @param rightText  右侧文字
     * @return
     */
    @SuppressLint("NewApi")
    public String printThreeData(String leftText, String middleText, String rightText) {
        StringBuilder sb = new StringBuilder();
        // 左边最多显示 LEFT_TEXT_MAX_LENGTH 个汉字 + 两个点
        if (leftText.length() > LEFT_TEXT_MAX_LENGTH) {
            leftText = leftText.substring(0, LEFT_TEXT_MAX_LENGTH) + "..";
        }
        int leftTextLength = getBytesLength(leftText);
        int middleTextLength = getBytesLength(middleText);
        int rightTextLength = getBytesLength(rightText);

        sb.append(leftText);
        int marginBetweenLeftAndMiddle;
        // 计算两侧文字中间的空格
        if (printerSize == 0) {
            marginBetweenLeftAndMiddle = LEFT_LENGTH - leftTextLength - middleTextLength / 2;
        } else {
            marginBetweenLeftAndMiddle = LEFT_LENGTH - leftTextLength - middleTextLength / 2;
        }
        for (int i = 0; i < marginBetweenLeftAndMiddle; i++) {
            sb.append(" ");
        }
        sb.append(middleText);
        Log.e("", "printThreeData sb left " + sb.toString());
        int marginBetweenMiddleAndRight;
        if (printerSize == 0) {
            // 计算右侧文字和中间文字的空格长度
            marginBetweenMiddleAndRight = (RIGHT_LENGTH - middleTextLength / 2 - rightTextLength) + 1;
        } else {
            marginBetweenMiddleAndRight = RIGHT_LENGTH_80 - middleTextLength / 2 - rightTextLength;
        }
        for (int i = 0; i < marginBetweenMiddleAndRight; i++) {
            sb.append(" ");
        }
        Log.e("", "printThreeData sb right " + sb.toString());
        // 打印的时候发现，最右边的文字总是偏右一个字符，所以需要删除一个空格
        sb.delete(sb.length() - 1, sb.length()).append(rightText);
        Log.e("", "printThreeData sb right =" + sb.toString());
        return sb.toString();
    }


    @SuppressLint("NewApi")
    private static int getBytesLength(String msg) {
        return msg.getBytes(Charset.forName("GB2312")).length;
    }


}
