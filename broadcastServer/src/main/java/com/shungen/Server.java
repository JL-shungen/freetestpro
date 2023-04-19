package com.shungen;

import java.io.IOException;
import java.net.*;

/**
 * @Description
 * @Author shungen
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2023/4/12
 */
public class Server {

    public static void main(String[] args) throws IOException {
        //1.创建发送端对象:发送端自带默认的端口号.
        DatagramSocket socket = new DatagramSocket();

        //2.创建一个数据包对象封装数据(韭菜盘子)
        String data = "接收端你好!!";
        byte[] byteData = data.getBytes();
        DatagramPacket packet = new DatagramPacket(byteData,byteData.length, InetAddress.getLocalHost(),8888);

        //3.发送数据出去
        socket.send(packet);

        //4.资源的关闭
        socket.close();
    }
}
