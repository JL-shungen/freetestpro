package com.shungen.TestClass;

import org.junit.Test;

import java.io.IOException;
import java.net.*;

/**
 * @Description
 * @Author shungen
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2023/4/12
 */
public class TestClass {


    @Test
    public void server01() throws IOException {
        DatagramSocket socket = new DatagramSocket();

        String broadcastMessage = "Hello, world!";
        byte[] sendData = broadcastMessage.getBytes();

        InetAddress broadcastAddress = InetAddress.getByName("255.255.255.255");
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, broadcastAddress, 1234);
        socket.send(sendPacket);

        System.out.println("Broadcast message sent");

        socket.close();
    }
    @Test
    public void client01() throws IOException {
        DatagramSocket socket = new DatagramSocket(1234);

        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receivePacket);

        String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
        InetAddress senderAddress = receivePacket.getAddress();
        int senderPort = receivePacket.getPort();

        System.out.println("Received message: " + message);
        System.out.println("Sender address: " + senderAddress.getHostAddress());
        System.out.println("Sender port: " + senderPort);

        socket.close();
    }
    @Test
    public void client02(){

    }
}
