package com.shungen;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @Description
 * @Author shungen
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2023/4/12
 */
public class ServerTest {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        socket.setBroadcast(true);
        socket.connect(InetAddress.getByName("255.255.255.255"), 1234);

        byte[] sendData = "DISCOVER".getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("255.255.255.255"), 1234);
        socket.send(sendPacket);

        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receivePacket);
        String broadcastIP = receivePacket.getAddress().getHostAddress();

        System.out.println("Broadcast IP: " + broadcastIP);

        socket.close();
    }
}
