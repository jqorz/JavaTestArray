package multicast;


import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * 多播接收
 * Created by datedu on 2017/5/26.
 */

public class MulticastThread {

    private static final String TAG = "MulticastThread";
    private final static int RECEIVE_LENGTH = 1024;
    private final static String HOST_ID = "233.0.0.1";  //多播ip
    private final static int PORT = 6166;    //多播端口

    private final static int MONITOR_INTEVAL = 5000;
    private InetAddress mInetAddress;
    private MulticastSocket receiveMulticast;
    private DatagramPacket mDatagramPacket;

    public MulticastThread() {
        initUdpMulticast();
    }

    /**
     * 初始化多播
     */
    private void initUdpMulticast() {
        byte[] message = new byte[RECEIVE_LENGTH];
        try {
            if (receiveMulticast == null) {

                // 先初始化数据包
                mDatagramPacket = new DatagramPacket(message, RECEIVE_LENGTH);

                mInetAddress = InetAddress.getByName(HOST_ID);
                if (!mInetAddress.isMulticastAddress()) {
                    System.out.println("IP地址不合法");
                }

                receiveMulticast = new MulticastSocket(PORT);
                receiveMulticast.setSoTimeout(MONITOR_INTEVAL);
                receiveMulticast.joinGroup(mInetAddress);
                System.out.println("初始化多播成功");
            }
        } catch (Exception e) {
            System.out.println("初始化多播失败 exception: " + e);
        }
    }

    public String parseDatagramPacket() {
        try {
            // 取出多播报文
            receiveMulticast.receive(mDatagramPacket);

            // 将报文转成gbk格式字符串
            String ws = new String(mDatagramPacket.getData()
                    , 0
                    , mDatagramPacket.getLength()
                    , "UTF-8");
            System.out.println("解析报文 " + ws);

            return ws;
        } catch (Exception e) {
            System.out.println("解析报文出错 parseDatagramPacket: " + e.getMessage());
        }

        return null;
    }

    public void close() {
        if (null != receiveMulticast) {
            receiveMulticast.disconnect();
            receiveMulticast.close();
            receiveMulticast = null;
            mDatagramPacket = null;
        }
    }
}

