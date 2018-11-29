/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.RSSF.sensor.connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author karolyne
 */

public class MulticastPublisher {
    private DatagramSocket socket;
    private InetAddress group;
    private byte[] buf;
    private String ip, porta;

    public MulticastPublisher(String ip, String porta) {
        this.ip = ip;
        this.porta = porta;
    }
    
    public void mandaMensagem(String multicastMessage) throws IOException {
        socket = new DatagramSocket();
        group = InetAddress.getByName(ip);
        buf = multicastMessage.getBytes();
 
        DatagramPacket packet = new DatagramPacket(buf, buf.length, group, Integer.parseInt(porta));
        System.out.println("Enviando tal coisa:" + multicastMessage);
        socket.send(packet);
        socket.close();
    }
}
